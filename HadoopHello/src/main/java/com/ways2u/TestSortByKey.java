package com.ways2u;

import org.apache.spark.Partitioner;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huanglong on 2016/11/8.
 */
public class TestSortByKey {

    public static void main(String[] args)
    {

        SparkConf conf =new SparkConf().setAppName("Spark Log").setMaster("local[*]");
        JavaSparkContext javaSparkContext = new JavaSparkContext(conf);

        long start = System.currentTimeMillis();
        String file = "file:///Users/huanglong/Desktop/HadoopHello/src/main/java/com/ways2u/access_05_30.log";
        JavaRDD<String> data = javaSparkContext.textFile(file, 4);

        //日志格式
        final Pattern pattern = Pattern.compile("^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(\\S+) (\\S+) (\\S+)\" (\\d{3}) (\\S+)");


        JavaRDD<String> filter =  data.filter(new org.apache.spark.api.java.function.Function<String, Boolean>() {
            @Override
            public Boolean call(String s) throws Exception {
                //过滤合法和非静态的资源的日志
                return (s.contains("POST")||s.contains("GET"))&& !s.contains("/static/");
            }
        });

        final SimpleDateFormat tf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z",
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
                return list.iterator();
            }
        }).cache();


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
        });

/*
        JavaPairRDD<String,Integer> sortByKeyRDD = stateCount.repartitionAndSortWithinPartitions(new Partitioner() {
            @Override
            public int numPartitions() {   return 2;    }
            @Override
            public int getPartition(Object key) { return key.toString().hashCode() % numPartitions();
            }
        });
*/
        //JavaPairRDD<String,Integer> sortByKeyRDD = stateCount.sortByKey(true);

        stateCount.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> t) throws Exception {
                System.out.println(t._1+" : "+t._2);
            }
        });

        /*
        List<String> data = Arrays.asList("200", "401", "400", "500", "300", "304", "301");
        JavaRDD<String> javaRDD = javaSparkContext.parallelize(data);

        final Random random = new Random(100);
        JavaPairRDD<String,Integer> javaPairRDD = javaRDD.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String integer) throws Exception {
                return new Tuple2<String, Integer>(integer,random.nextInt(100));
            }
        });

        JavaPairRDD<String,Integer> sortByKeyRDD = javaPairRDD.sortByKey(true);
        //System.out.println(sortByKeyRDD.collect());
        sortByKeyRDD.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> t) throws Exception {
                System.out.println(t._1+" : "+t._2);
            }
        });
        */

        System.out.println(System.currentTimeMillis() - start);
    }
}
