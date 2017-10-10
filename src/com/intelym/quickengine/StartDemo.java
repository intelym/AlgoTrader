package com.intelym.quickengine;

import com.intelym.quick.java.client.common.StartQuickClient;
import com.intelym.quickengine.util.FeedReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intelym.quickengine.util.RandomPriceEventGenerator;



public class StartDemo {

    /** Logger */
	private final static Logger log = LoggerFactory.getLogger(StartDemo.class);

    public static void main(String[] args) throws Exception {

       log.debug("Starting...");
       FeedReceiver feedReceiver = new FeedReceiver();

    }

}
