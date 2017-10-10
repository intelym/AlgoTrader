package com.intelym.quickengine.handler;

import com.espertech.esper.client.EPStatement;
import com.intelym.logger.LoggerFactory;
import com.intelym.logger.QuickLogger;
import com.intelym.quickengine.subscriber.TWAPListener;
import com.intelym.quickengine.util.EPLStatement;

//@Component
//@Scope(value = "singleton")
public class TWAPEventHandler extends BaseEventHandler {

    private static QuickLogger log = LoggerFactory.getLogger(TWAPEventHandler.class);

    //private final EPStatement eplStatement;
    private static TWAPEventHandler twaPEventHandler;
    EPStatement eplStatement;
    public TWAPEventHandler() {
        this.epService = EPSServiceConfig.getEPServiceProvider();
        log.debug("create Timed Average Monitor");
        eplStatement = epService.getEPAdministrator().createEPL(EPLStatement.TVAP);
        eplStatement.addListener(new TWAPListener());
    }
    
    public static TWAPEventHandler getInstance() {
        if (null == twaPEventHandler) {
            twaPEventHandler = new TWAPEventHandler();
        }
        
        return twaPEventHandler;
    }

    //EPL to check for  events over the threshold value in our case value is 400 price - if matched, will alert listener.
//    private void createOrderGTFeedCheckExpression() {
//        log.debug("createOrderGTFeedCheckExpression Check Expression");
//        orderGTEventStatement = epService.getEPAdministrator().createEPL(orderEventGTPrice.getStatement());
//        orderGTEventStatement.setSubscriber(orderEventGTPrice);
//    }
    
}
