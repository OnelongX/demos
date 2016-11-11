package com.ways2u.jstom;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;

/**
 * Hello world!
 * 
 */
public class HelloWorldTopology {
    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("randomHelloWorld", new HelloWorldSpout(), 10);
		builder.setBolt("HelloWorldBolt", new HelloWorldBolt(), 1).shuffleGrouping("randomHelloWorld");
		Config conf = new Config();
		conf.setDebug(true);



		if (args != null && args.length > 0)
		{// 如果在JStrom集群中运行
			//conf.setNumWorkers(3);
			// JStorm 安装完后，默认的NIMBUS端口配置为8627
			conf.put(Config.NIMBUS_THRIFT_PORT, 8627);
			StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
		}else {
			//conf.setNumWorkers(3);
			// JStorm 安装完后，默认的NIMBUS端口配置为8627
			conf.put(Config.NIMBUS_THRIFT_PORT, 8627);
			StormSubmitter.submitTopology("HelloWorld", conf, builder.createTopology());
		}
	}
}