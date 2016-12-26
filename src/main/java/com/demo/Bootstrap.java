package com.demo;

import java.io.Serializable;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;

public class Bootstrap implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -813109633056593133L;

	public static void main(String[] args) throws Exception {
		SpringContext.load("/config/spring.xml");
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("spout", new DemoSpout(), 5);
		builder.setBolt("split", new DemoBolt(), 8).shuffleGrouping("spout");
		Config conf = new Config();
		conf.setDebug(false);
		if (args != null && args.length > 0) {
			conf.setNumWorkers(3);
			StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
		} else {
			LocalCluster cluster = new LocalCluster();
			conf.put(Config.TOPOLOGY_MAX_TASK_PARALLELISM, 1);
			cluster.submitTopology("word-count", conf, builder.createTopology());
			Thread.sleep(60000);
			cluster.killTopology("word-count");
			cluster.shutdown();
		}
	}

}
