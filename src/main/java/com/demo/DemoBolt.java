package com.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	Logger logger = LoggerFactory.getLogger(DemoBolt.class);
	@Override
	public void execute(Tuple arg0, BasicOutputCollector arg1) {
		String msg = arg0.getString(0);
		int value = arg0.getIntegerByField("rand");
		logger.info("content:{},rand:{}",msg,value);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		arg0.declare(new Fields("word"));		
	}
}
