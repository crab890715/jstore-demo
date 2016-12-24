package com.demo;

import java.util.Map;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

public class DemoBolt extends BaseBasicBolt{


	/**
	 * 
	 */
	private static final long serialVersionUID = 2046633838564394830L;

	@Override
	public void execute(Tuple arg0, BasicOutputCollector arg1) {
		String msg = arg0.getString(0);
		System.err.println(msg);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		arg0.declare(new Fields("word"));		
	}
}
