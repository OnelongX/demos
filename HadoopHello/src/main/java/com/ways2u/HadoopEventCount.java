package com.ways2u;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class HadoopEventCount {

 
    public static void main(String[] args) throws Exception {
        /*
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length < 2) {
            System.err.println("Usage: HadoopEventCount <in> <out>");
            System.exit(2);
        }
        Job job = Job.getInstance(conf, "event count");
*/
        String input = "hdfs://localhost:9000/user/huanglong/myword.txt";
        String output = "hdfs://localhost:9000/user/huanglong/result";
        JobConf conf = new JobConf(HadoopEventCount.class);
        conf.setJobName("WordCount");

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        conf.setMapperClass(MyMapper.class);
        conf.setCombinerClass(MyReducer.class);
        conf.setReducerClass(MyReducer.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf,new Path(input));
        FileOutputFormat.setOutputPath(conf,new Path(output));

        JobClient.runJob(conf);
        System.exit(0);

        //System.exit(conf.waitForCompletion(true) ? 0 : 1);
    }
}