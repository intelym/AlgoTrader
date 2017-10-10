package com.intelym.quickengine.subscriber;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.intelym.quickengine.event.PriceEvent;
import com.intelym.quickengine.util.OrderInformation;


//@Component
public class OrderEventGTPrice extends OrderInformation implements StatementSubscriber {

    private final static Logger LOG = LoggerFactory.getLogger(OrderEventGTPrice.class);

    private static final String ORDER_EVENT_THRESHOLD = "400";

    
    public String getStatement() {
        
        String warningEventExpression = "select * from PriceEvent "
                + "match_recognize ( "
                + "       measures A as prices "
                + "       pattern (A) " 
                + "       define " 
                + "               A as A.feed >= " + ORDER_EVENT_THRESHOLD + ")";
        
        return warningEventExpression;
    }
    
  
    public void update(Map<String, PriceEvent> eventMap) {

        PriceEvent prices = (PriceEvent) eventMap.get("prices");

        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------------");
        sb.append("\n- Order triggered at the " + prices + ".");
        sb.append("\n--------------------------------------------------");

        LOG.debug(sb.toString());
        
        addOrder();//for new order creation api
    }


	@Override
	public void addOrder() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void cancelOrder() {
		// TODO Auto-generated method stub
		
	}
}
