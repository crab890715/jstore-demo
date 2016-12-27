package com.demo;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.demo.model.LoginOut;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class DemoSpout extends BaseRichSpout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7364856519864163561L;
	SpoutOutputCollector _collector;
	Random _rand = new Random();

	@Override
	public void nextTuple() {
		MongoTemplate mongoTemplate = (MongoTemplate) SpringContext.getBean("mongoTemplate1");

		Criteria criteria = new Criteria();
		criteria.andOperator(Criteria.where("time").lt(DateTime.parse("2016-12-02").toDate()),
				Criteria.where("time").gt(DateTime.parse("2016-12-01").toDate()));
		Query query = Query.query(criteria);
		List<LoginOut> data = mongoTemplate.find(query, LoginOut.class);
		String[] sentences = new String[] { "the cow jumped over the moon", "an apple a day keeps the doctor away",
				"four score and seven years ago", "snow white and the seven dwarfs", "i am at two with nature" };
		int r = _rand.nextInt(sentences.length);
		String sentence = sentences[r];
		_collector.emit(new Values(sentence, r));

	}

	public static void main(String[] args) {
		System.err.println(DateTime.parse("2016-12-01").toString("yyyy-MM-dd"));
	}

	@Override
	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector arg2) {
		_collector = arg2;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		arg0.declare(new Fields("word", "rand"));
	}

}
