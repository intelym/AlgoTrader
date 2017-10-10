package com.intelym.quickengine.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class OrderInformation {
	
	private final static Logger LOG = LoggerFactory.getLogger(OrderInformation.class);
	
	public abstract void addOrder();
    public abstract void cancelOrder();

}
