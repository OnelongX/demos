package com.ways2u.jstom;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

/**
 * Hello world!
 * 
 */
public class HelloWorldBolt extends BaseRichBolt {
    private static final long serialVersionUID = 1L;
	private int myCount = 0;

	/*
	 * prepare() => on create
	 */
	@Override
	public void prepare(@SuppressWarnings("rawtypes") Map map, TopologyContext topologyContext,
			OutputCollector outputCollector) {
	}

	/*
	 * execute() => most important method in the bolt is execute(Tuple input),
	 * which is called once per tuple received the bolt may emit several tuples
	 * for each tuple received
	 */
	@Override
	public void execute(Tuple tuple) {
		String test = tuple.getStringByField("sentence");
		if (test == "Hello World") {
			myCount++;
			System.out.println("Found a Hello World! My Count is now: " + Integer.toString(myCount));
		}
	}

	/*
	 * declareOutputFields => This bolt emits nothing hence no body for
	 * declareOutputFields()
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
	}
}
