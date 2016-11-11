package com.ways2u;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;


import java.io.IOException;
import java.util.Iterator;

public class MyReducer implements Reducer<Text,IntWritable,Text,IntWritable> {
        private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterator<IntWritable> vv, OutputCollector<Text, IntWritable> out, Reporter var4) throws IOException
    {
        int sum = 0;
        while (vv.hasNext()) {
            sum += vv.next().get();
        }
        result.set(sum);
        out.collect(key, result);
    }


    public void close() throws IOException {

    }

    public void configure(JobConf jobConf) {

    }
}