package com.ways2u;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;
import java.util.List;

/**
 * Created by huanglong on 2016/11/5.
 */
public class SparkSQLDemo {
    public static void main(String[] args) {

        String warehouseLocation = System.getProperty("user.dir") + "spark-warehouse";//用户的当前工作目录
        SparkConf conf = new SparkConf().setAppName("spark sql test")
                .set("spark.sql.warehouse.dir", warehouseLocation)
                .setMaster("local[3]");

        JavaSparkContext ctx = new JavaSparkContext(conf);
        SparkSession spark = SparkSession
                .builder()
                .config(conf)
                .getOrCreate();



        //数据导入方式
        Dataset df = spark.read().json("file:///Users/huanglong/Desktop/HadoopHello/src/main/java/com/ways2u/people.json");
        //查看表
        df.show();
        //查看表结构
        df.printSchema();
        //查看某一列 类似于MySQL： select name from people
        df.select("name").show();
        //查看多列并作计算 类似于MySQL: select name ,age+1 from people
        df.select(df.col("name"), df.col("age").plus(1)).show();
        //设置过滤条件 类似于MySQL:select * from people where age>21
        df.filter(df.col("age").gt(21)).show();
        //做聚合操作 类似于MySQL:select age,count(*) from people group by age
        df.groupBy("age").count().show();
        //上述多个条件进行组合 select ta.age,count(*) from (select name,age+1 as "age" from people) as ta where ta.age>21 group by ta.age
        df.select(df.col("name"), df.col("age").plus(1).alias("age")).filter(df.col("age").gt(21)).groupBy("age").count().show();

        //直接使用spark SQL进行查询
        //先注册为临时表
        df.createOrReplaceTempView("people");
        Dataset sqlDF = spark.sql("SELECT * FROM people");
        sqlDF.show();


        List<String> jsonData = Arrays.asList(
                "{\"name\":\"Yin\",\"address\":{\"city\":\"Columbus\",\"state\":\"Ohio\"}}");

        JavaRDD<String> anotherPeopleRDD = ctx.parallelize(jsonData);
        Dataset anotherPeople = spark.read().json(anotherPeopleRDD);

        anotherPeople.show();
    }
}
