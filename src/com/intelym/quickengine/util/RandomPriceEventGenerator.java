package com.intelym.quickengine.util;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intelym.quickengine.event.PriceEvent;
import com.intelym.quickengine.handler.TWAPEventHandler;

//@Component
public class RandomPriceEventGenerator {

	private final static Logger LOG = LoggerFactory.getLogger(RandomPriceEventGenerator.class);

    //@Autowired
    private TWAPEventHandler priceEventHandler;

    public void startSendingPriceReadings(final long noOfPriceEvents) {

        ExecutorService xrayExecutor = Executors.newSingleThreadExecutor();

        xrayExecutor.submit(new Runnable() {
            public void run() {

                LOG.debug(getStartingMessage());
                
                int count = 0;
                while (count < noOfPriceEvents) {
                    PriceEvent ve = new PriceEvent(new Random().nextInt(500), new Date());
                    priceEventHandler.handle(ve);
                    count++;
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        LOG.error("Thread Interrupted", e);
                    }
                }

            }
        });
    }

    
    private String getStartingMessage(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n************************************************************");
        sb.append("\n* STARTING - ");
        sb.append("\n* PLEASE WAIT - Prices ARE RANDOM SO MAY TAKE");
        sb.append("\n* A WHILE TO SEE EVENTS!");
        sb.append("\n************************************************************\n");
        return sb.toString();
    }
}
