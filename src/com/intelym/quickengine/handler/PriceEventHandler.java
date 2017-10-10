package com.intelym.quickengine.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.intelym.quickengine.event.PriceEvent;
import com.intelym.quickengine.subscriber.StatementSubscriber;

//@Component
//@Scope(value = "singleton")
public class PriceEventHandler {

    private final static Logger LOG = LoggerFactory.getLogger(PriceEventHandler.class);

    private EPServiceProvider epService;
    private EPStatement orderGTEventStatement;
    private EPStatement twapEventStatement;

   
   
    private StatementSubscriber orderEventGTPrice;
    
    
    private StatementSubscriber twapOrder;
  
    public void initService() {
        LOG.debug("Initializing Servcie ..");
        Configuration config = new Configuration();
        config.addEventTypeAutoName("com.intelym.quickengine.event");
        epService = EPServiceProviderManager.getDefaultProvider(config);

        createOrderGTFeedCheckExpression();
        createVwapCheckExpression();
        
    }

    //EPL to check for  events over the threshold value in our case value is 400 price - if matched, will alert listener.
    private void createOrderGTFeedCheckExpression() {
        LOG.debug("createOrderGTFeedCheckExpression Check Expression");
        orderGTEventStatement = epService.getEPAdministrator().createEPL(orderEventGTPrice.getStatement());
        orderGTEventStatement.setSubscriber(orderEventGTPrice);
    }
    
    private void createVwapCheckExpression() {

        LOG.debug("create Timed Average Monitor");
        twapEventStatement = epService.getEPAdministrator().createEPL(twapOrder.getStatement());
        twapEventStatement.setSubscriber(twapOrder);
    }

    //Handle the incoming Event.
    public void handle(PriceEvent event) {
        LOG.debug(event.toString());
        epService.getEPRuntime().sendEvent(event);
    }

    public void afterPropertiesSet() {
        LOG.debug("Configuring..");
        initService();
    }
}
