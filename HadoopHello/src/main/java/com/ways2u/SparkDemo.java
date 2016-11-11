package com.ways2u;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.RawComparator;
import org.apache.log4j.net.SyslogAppender;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.*;

import org.apache.spark.mllib.tree.configuration.Strategy;
import scala.Tuple2;


public class SparkDemo {
 
    private static final String USER = "huanglong";



    static class MyComparator implements Comparator<Tuple2<String, Integer>>,Serializable
    {
            @Override
            public int compare(Tuple2<String, Integer> o1, Tuple2<String, Integer> o2) {
                return o1._2.compareTo(o2._2);
            }

    }

    static class MyComparator1 implements Comparator<String>,Serializable
    {
        @Override
        public int compare(String o1, String o2) {

            return Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
        }

    }
 
    public static void main(String[] args) throws Exception {

        System.setProperty("user.name", USER); // 设置访问Spark使用的用户名
        System.setProperty("HADOOP_USER_NAME", USER); // 设置访问Hadoop使用的用户名
        Map<String, String> envs = new HashMap<String, String>();
        envs.put("HADOOP_USER_NAME", USER); // 为Spark环境中服务于本App的各个Executor程序设置访问Hadoop使用的用户名
        System.setProperty("spark.executor.memory", "512m"); // 为Spark环境中服务于本App的各个Executor程序设置使用内存量的上限

        // 以下构造sc对象的构造方法各参数意义依次为：
        //   Spark Master的地址；
        //   App的名称；
        //   Spark Worker的部署位置；
        //   需要提供给本App的各个Executor程序下载的jar包的路径列表，这些jar包将出现在Executor程序的类路径中；
        //   传递给本App的各个Executor程序的环境信息。
        SparkConf conf =new SparkConf().setAppName("Spark Log").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf); //其底层就是scala的sparkcontext


        //JavaSparkContext sc = new JavaSparkContext("local[4]", "Spark Log", "/user/huanglong/spark", new String[0], envs);

        long start = System.currentTimeMillis();
        String file = "file:///Users/huanglong/Desktop/HadoopHello/src/main/java/com/ways2u/access_05_30.log";
        JavaRDD<String> data = sc.textFile(file, 4);

        //日志格式
        //String log = "27.19.74.143 - - [30/May/2013:17:38:20 +0800] \"GET /static/image/common/faq.gif HTTP/1.1\" 200 1127";
        final  Pattern pattern = Pattern.compile("^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(\\S+) (\\S+) (\\S+)\" (\\d{3}) (\\S+)");
       /*
        Matcher m = pattern.matcher(log);

        if (m.matches()){
            System.out.println(m.group(1));//ip
            System.out.println(m.group(2));//-
            System.out.println(m.group(3));//-
            System.out.println(m.group(4));//time
            System.out.println(m.group(5));//methom
            System.out.println(m.group(6));//url
            System.out.println(m.group(7));//version
            System.out.println(m.group(8));//state
            System.out.println(m.group(9));//size
        }
*/
         //"^(\S+) (\S+) (\S+) \[([\w:/]+\s[+\-]\d{4})\] \"(\S+) (\S+) (\S+)\" (\d{3}) (\d+)";

        JavaRDD<String> filter =  data.filter(new org.apache.spark.api.java.function.Function<String, Boolean>() {
            @Override
            public Boolean call(String s) throws Exception {
                //过滤合法和非静态的资源的日志
                return (s.contains("POST")||s.contains("GET"))&& !s.contains("/static/");
            }
        });

        final  SimpleDateFormat tf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z",
                Locale.ENGLISH);
        //只有一天的记录，不需要年月日了,时间精度到分钟
        final SimpleDateFormat tf1 = new SimpleDateFormat("HH:mm");

        JavaRDD<Map<String,String>> lines = filter.flatMap(new FlatMapFunction<String,Map<String,String>>(){

            @Override
            public Iterator<Map<String,String>> call(String s) throws Exception {
                List<Map<String,String>> list = new ArrayList<Map<String,String>>(1);
                Map<String,String> map = new HashMap<String, String>();

                Matcher m = pattern.matcher(s);
                if (m.matches()){
                    list.add(map);
                    map.put("ip",m.group(1));
                    //System.out.println(m.group(1));//ip
                    //System.out.println(m.group(2));//-
                    //System.out.println(m.group(3));//-
                    Date date =  tf.parse(m.group(4));
                    map.put("time",tf1.format(date));
                    //System.out.println(m.group(4));//time
                    //System.out.println(m.group(5));//methom
                    String url = m.group(6);
                    if(url!=null&&url.indexOf("?")!=-1)
                    {
                        map.put("url",url.substring(0,url.indexOf("?")));
                    }else {
                        map.put("url",url);
                    }

                    //System.out.println(m.group(6));//url
                    map.put("version",m.group(7));


                    //System.out.println(m.group(7));//version
                    map.put("state", m.group(8));
                    //System.out.println(m.group(8));//state
                    //System.out.println(m.group(9));//size
                }else {
                    //System.out.println(s);
                }

//非正则表达式方式，正则相对快一点点
/*
                String ip = s.substring(0,s.indexOf(" - - "));
                map.put("ip",ip);

                String t = s.substring(s.indexOf("[")+1,s.indexOf("]"));
                Date date =  tf.parse(t);
                map.put("time",tf1.format(date));

                String http = s.substring(s.indexOf("\"")+1,s.indexOf("\" "));
                String[] h= http.split(" ");

                if(h.length>1){
                    if(h[1]!=null&&h[1].indexOf("?")!=-1)
                    {
                        map.put("url",h[1].substring(0,h[1].indexOf("?")));
                    }else {
                        map.put("url",h[1]);
                    }
                }

                if(h.length>2)
                {
                    map.put("version",h[2]);
                }

                if(map.get("version")!=null&&map.get("version").contains("1.1"))
                {
                    String state = s.substring(s.indexOf("1.1\" ")+5,s.length());
                    String[] strings = state.split(" ");
                    if(strings.length>0) {
                        map.put("state", strings[0]);
                    }
                }else if(map.get("version")!=null&&map.get("version").contains("1.0"))
                {
                    String state = s.substring(s.indexOf("1.0\" ")+5,s.length());
                    String[] strings = state.split(" ");
                    if(strings.length>0) {
                        map.put("state", strings[0]);
                    }
                }else {
                    //System.out.println(s);
                }

*/
                return list.iterator();
            }
        }).cache();
        /**
         *cache ,如果某个RDD反复的被使用，可以考虑将其进行cache
         */
        System.out.println("总记录数:"+lines.count());
        // 统计ip
            ipsHandle(lines);
        System.out.println("--------------------------------------------------");
        // 统计分时页面访问量
            timeHandle(lines);
        System.out.println("--------------------------------------------------");
        // 统计分时ip访问量
            timeHandle3(lines);
        System.out.println("--------------------------------------------------");
        // 统计状态 400，300 200 各有多少,计算失败率
            stateHandle(lines);
        System.out.println("--------------------------------------------------");
        // 统计http版本 1.1、1.0
            versionHandle(lines);
        // 页面热度
        System.out.println("--------------------------------------------------");
          //  pageHandle(lines);

        System.out.println("--------------------------------------------------");
        System.out.println(System.currentTimeMillis() - start);
        System.out.println("--------------------------------------------------");

       sc.close();

    }


    private static void pageHandle(JavaRDD<Map<String, String>> lines) {

        JavaRDD<Map<String, String>> filterData =  lines.filter(new Function<Map<String, String>, Boolean>() {
            @Override
            public Boolean call(Map<String, String> map) throws Exception {
                String url = map.get("url");
                return "200".equals(map.get("state"))&&url.endsWith(".php");
            }
        });


        JavaPairRDD<String,Integer> versionPairs = filterData.mapToPair(new PairFunction<Map<String,String>, String,Integer>() {
            @Override
            public Tuple2<String,Integer> call(Map<String, String> stringStringMap) throws Exception {
                return  new Tuple2<String, Integer>(stringStringMap.get("url"),1);
            }
        });

        JavaPairRDD<String,Integer> versionCount = versionPairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1+v2;
            }
        });


        versionCount.foreach(new VoidFunction<Tuple2<String,Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> pairs) throws Exception {
                System.out.println("URL->PV："+pairs._1+" : " +pairs._2);
            }
        });
    }


    private static void versionHandle(JavaRDD<Map<String, String>> lines) {
        JavaPairRDD<String,Integer> versionPairs = lines.mapToPair(new PairFunction<Map<String,String>, String,Integer>() {
            @Override
            public Tuple2<String,Integer> call(Map<String, String> stringStringMap) throws Exception {
                return  new Tuple2<String, Integer>(stringStringMap.get("version"),1);
            }
        });

        JavaPairRDD<String,Integer> versionCount = versionPairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1+v2;
            }
        });




        versionCount.foreach(new VoidFunction<Tuple2<String,Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> pairs) throws Exception {
                System.out.println("VERSION->PV："+pairs._1+" : " +pairs._2);
            }
        });
    }

    private static void stateHandle(JavaRDD<Map<String, String>> lines) {
        JavaPairRDD<String,Integer> statePairs = lines.mapToPair(new PairFunction<Map<String,String>, String,Integer>() {
            @Override
            public Tuple2<String,Integer> call(Map<String, String> stringStringMap) throws Exception {
                return  new Tuple2<String, Integer>(stringStringMap.get("state"),1);
            }
        });

        JavaPairRDD<String,Integer> stateCount = statePairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1+v2;
            }
            //
        }).sortByKey();
/*

        JavaPairRDD<SecondarySortKey,Integer> srdd= stateCount.mapToPair(new PairFunction<Tuple2<String,Integer>, SecondarySortKey, Integer>() {
            @Override
            public Tuple2<SecondarySortKey, Integer> call(Tuple2<String, Integer> t) throws Exception {
                SecondarySortKey s = new SecondarySortKey();
                s.key = t._1;
                s.count = t._2;
                return new Tuple2<SecondarySortKey, Integer>(s,t._2);
            }
        }).sortByKey(false);

*/
/*
        List<Tuple2<String, Integer>> srdd = stateCount.top(10,new MyComparator());
        for (Tuple2<String, Integer> pairs:srdd)
        {
            System.out.println("STATE->PV："+pairs._1+" : " +pairs._2);
        }

*/
        stateCount.foreach(new VoidFunction<Tuple2<String,Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> pairs) throws Exception {
                System.out.println("STATE->PV："+pairs._1+" : " +pairs._2);

            }
        });

    }

    private static void ipsHandle(JavaRDD<Map<String, String>> lines) {
        JavaPairRDD<String,Integer> ipPairs = lines.mapToPair(new PairFunction<Map<String,String>, String,Integer>() {
            @Override
            public Tuple2<String,Integer> call(Map<String, String> stringStringMap) throws Exception {
                return  new Tuple2<String, Integer>(stringStringMap.get("ip"),1);
            }
        });

        JavaPairRDD<String,Integer> ipCount = ipPairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1+v2;
            }
        });

        System.out.println("总ip数:"+ipCount.count());
        /*

        ipCount.foreach(new VoidFunction<Tuple2<String,Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> pairs) throws Exception {
                System.out.println("IP->PV："+pairs._1+" : " +pairs._2);
            }
        });
          */


        List<Tuple2<String, Integer>> top10 = ipCount.top(10, new MyComparator());
        for (Tuple2<String, Integer> pairs:top10)
        {
            System.out.println("IP->PV："+pairs._1+" : " +pairs._2);
        }

    }


    private static void timeHandle3(JavaRDD<Map<String, String>> lines) {

        JavaPairRDD<String,String> timePairs = lines.mapToPair(new PairFunction<Map<String,String>, String,String>() {
            @Override
            public Tuple2<String,String> call(Map<String, String> stringStringMap) throws Exception {
                return new Tuple2<String, String>(stringStringMap.get("time"),stringStringMap.get("ip"));
            }
        });

        JavaPairRDD<String, Integer> pairs = timePairs.groupBy(new Function<Tuple2<String,String>, String>() {
            @Override
            public String call(Tuple2<String, String> t) throws Exception {
                return t._1;
            }
        }).mapToPair(new PairFunction<Tuple2<String,Iterable<Tuple2<String,String>>>, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(Tuple2<String, Iterable<Tuple2<String, String>>> t) throws Exception {
                Collection c = (Collection)t._2;
                return new Tuple2<String, Integer>(t._1,c.size());
            }
        });


        List<Tuple2<String, Integer>> top10 = pairs.top(10, new MyComparator());
        for (Tuple2<String, Integer> p:top10)
        {
            System.out.println("TIME->IP："+p._1+" : " +p._2);
        }
/*
        pairs.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> pairs) throws Exception {

                System.out.println("TIME->IP："+pairs._1+" : " +pairs._2);

            }
        });
*/

}

    private static void timeHandle2(JavaRDD<Map<String, String>> lines) {

        JavaPairRDD<String,Map<String,Boolean>> timePairs = lines.mapToPair(new PairFunction<Map<String,String>, String,Map<String,Boolean>>() {
            @Override
            public Tuple2<String,Map<String,Boolean>> call(Map<String, String> stringStringMap) throws Exception {
                Map<String,Boolean> m = new HashMap<String, Boolean>();
                m.put(stringStringMap.get("ip"),Boolean.TRUE);
                return new Tuple2<String, Map<String,Boolean>>(stringStringMap.get("time"),m);
            }
        });

        JavaPairRDD<String,Map<String,Boolean>> timeCount = timePairs.reduceByKey(new Function2<Map<String,Boolean>, Map<String,Boolean>, Map<String,Boolean>>() {
            @Override
            public Map<String,Boolean> call(Map<String,Boolean> v1, Map<String,Boolean> v2) throws Exception {
                HashMap<String, Boolean> m = new HashMap<String, Boolean>();
                m.putAll(v1);
                m.putAll(v2);
                return m;
            }
        });


        timeCount.foreach(new VoidFunction<Tuple2<String, Map<String,Boolean>>>() {
            @Override
            public void call(Tuple2<String, Map<String,Boolean>> pairs) throws Exception {
                System.out.println("TIME->IP："+pairs._1+" : " +pairs._2.size());
            }
        });

    }

    private static void timeHandle(JavaRDD<Map<String, String>> lines) {
        JavaPairRDD<String,Integer> timePairs = lines.mapToPair(new PairFunction<Map<String,String>, String,Integer>() {
            @Override
            public Tuple2<String,Integer> call(Map<String, String> stringStringMap) throws Exception {
                return  new Tuple2<String, Integer>(stringStringMap.get("time"),1);
            }
        });

        JavaPairRDD<String,Integer> timeCount = timePairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1+v2;
            }
        });

        List<Tuple2<String, Integer>> top10 = timeCount.top(10, new MyComparator());
        for (Tuple2<String, Integer> p:top10)
        {
            System.out.println("TIME->PV："+p._1+" : " +p._2);
        }
/*
        timeCount.foreach(new VoidFunction<Tuple2<String,Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> pairs) throws Exception {
                System.out.println("TIME->PV："+pairs._1+" : " +pairs._2);

            }
        });
*/
    }

}