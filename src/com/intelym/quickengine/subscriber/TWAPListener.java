package com.intelym.quickengine.subscriber;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.intelym.logger.LoggerFactory;
import com.intelym.logger.QuickLogger;
import java.util.Map;

//@Component
public class TWAPListener  implements UpdateListener {

    private static QuickLogger log = LoggerFactory.getLogger(TWAPListener.class);

//    public String getStatement() {
//
//        return "select avg(feed) as avg_val from PriceEvent.win:time_batch(5 sec)";
//    }

    /**
     * Listener method called when Esper has detected a pattern match.
     * @param eventMap
     */
    public void update(Map<String, Double> eventMap) {
//
//        Double avg = eventMap.get("avg_val");
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("---------------------------------");
//        sb.append("\n- [TWAP orders with time window 5sec..] Average Price = ").append(avg);
//        sb.append("\n---------------------------------");
//
//        log.debug(sb.toString());
//        //addOrder();//for new order creation api.
    }

//	@Override
//	public void addOrder() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void cancelOrder() {
//		// TODO Auto-generated method stub
//		
//	}

    @Override
    public void update(EventBean[] ebs, EventBean[] ebs1) {
        log.info("TWAP Listener");
        System.out.println(ebs.length);
    }

//    @Override
//    public void update(EventBean[] ebs, EventBean[] ebs1) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
