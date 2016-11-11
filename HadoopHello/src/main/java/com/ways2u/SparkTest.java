package com.ways2u;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.*;
import scala.Function1;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huanglong on 2016/11/8.
 */
public class SparkTest {
    public static void main(String[] args)
    {
        String warehouseLocation = System.getProperty("user.dir") + "spark-warehouse";//用户的当前工作目录
        SparkConf conf = new SparkConf().setAppName("spark sql test")
                .set("spark.sql.warehouse.dir", warehouseLocation)
                .setMaster("local");

        //JavaSparkContext ctx = new JavaSparkContext(conf);
        SparkSession spark = SparkSession
                .builder()
                .config(conf)
                .getOrCreate();

        Dataset<String> dataset =  spark.read().textFile("file:///Users/huanglong/Desktop/HadoopHello/src/main/java/com/ways2u/access_05_30.log");
        System.out.println("dataset:"+dataset.count());

        Dataset<String> filter = dataset.filter(new FilterFunction<String>() {
            @Override
            public boolean call(String s) throws Exception {
                  //过滤合法和非静态的资源的日志
                return (s.contains("POST")||s.contains("GET"))&& !s.contains("/static/");
            }
        });

        System.out.println("filter:"+filter.count());
        //filter.show();
        final Pattern pattern = Pattern.compile("^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(\\S+) (\\S+) (\\S+)\" (\\d{3}) (\\S+)");
        final SimpleDateFormat tf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z",
                Locale.ENGLISH);
        //只有一天的记录，不需要年月日了,时间精度到分钟
        final SimpleDateFormat tf1 = new SimpleDateFormat("HH:mm");




    }
}
