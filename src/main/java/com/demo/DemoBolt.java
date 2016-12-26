package com.demo;

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
		int value = arg0.getIntegerByField("rand");
		System.err.println(String.format("%s******** rand:%s", msg,value));
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		arg0.declare(new Fields("word"));		
	}
}
