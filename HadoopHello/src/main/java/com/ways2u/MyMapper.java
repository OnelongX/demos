package com.ways2u;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;



import java.io.IOException;

public class MyMapper implements Mapper<Object, Text, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);
        private Text event = new Text();
 


    public void map(Object o, Text text, OutputCollector<Text, IntWritable> outputCollector, Reporter reporter) throws IOException {
        int idx = text.toString().indexOf(" ");
        if (idx > 0) {
            String e = text.toString().substring(0, idx);
            event.set(e);
            outputCollector.collect(event, one);

        }
    }

    public void close() throws IOException {

    }

    public void configure(JobConf jobConf) {

    }
}