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

import java.util.*;

/**
 * Created by huanglong on 2016/11/8.
 */
public class Test {
    public static void main(String[] args)
    {
        SparkConf conf =new SparkConf().setAppName("Spark Log").setMaster("local");
        JavaSparkContext javaSparkContext = new JavaSparkContext(conf);
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
        System.out.println("------1>"+sortByKeyRDD.collect());
        /*
        sortByKeyRDD.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> t) throws Exception {
                System.out.println(t._1+" : "+t._2);
            }
        });
*/

        JavaPairRDD<String,Integer> RepartitionAndSortWithPartitionsRDD = javaPairRDD.repartitionAndSortWithinPartitions(new Partitioner() {
            @Override
            public int numPartitions() {   return 2;    }
            @Override
            public int getPartition(Object key) { return key.toString().hashCode() % numPartitions();
            }
        });
        System.out.println("------2>"+RepartitionAndSortWithPartitionsRDD.collect());


        List<Integer> data1 = Arrays.asList(1, 9, 4, 3, 5, 6, 7,4);
        //RDD有两个分区
        JavaRDD<Integer> javaRDD1 = javaSparkContext.parallelize(data1,2);
        //计算每个分区的合计
        JavaRDD<Integer> mapPartitionsRDD = javaRDD1.mapPartitions(new FlatMapFunction<Iterator<Integer>, Integer>() {
            @Override
            public Iterator<Integer> call(Iterator<Integer> integerIterator) throws Exception {
                int isum = 0;
                while(integerIterator.hasNext())
                    isum += integerIterator.next();
                LinkedList<Integer> linkedList = new LinkedList<Integer>();
                linkedList.add(isum);
                return linkedList.iterator();
            }
        });
        System.out.println("mapPartitionsRDD~~~~~~~~~~~~~~~~~~~~~~" + mapPartitionsRDD.collect());


        //RDD有两个分区
        JavaRDD<Integer> javaRDD2 = javaSparkContext.parallelize(data1,3);
        //分区index、元素值、元素编号输出
        JavaRDD<String> mapPartitionsWithIndexRDD = javaRDD2.mapPartitionsWithIndex(new Function2<Integer, Iterator<Integer>, Iterator<String>>() {
            @Override
            public Iterator<String> call(Integer index, Iterator<Integer> v2) throws Exception {
                LinkedList<String> linkedList = new LinkedList<String>();
                int i = 0;
                while (v2.hasNext())
                    linkedList.add(Integer.toString(index) + "|" + v2.next().toString() + Integer.toString(i++));
                return linkedList.iterator();
            }
        },false);

        System.out.println("mapPartitionsWithIndexRDD~~~~~~~~~~~~~~~~~~~~~~" + mapPartitionsWithIndexRDD.collect());
    }
}
