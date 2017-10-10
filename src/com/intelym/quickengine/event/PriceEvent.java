package com.intelym.quickengine.event;

import java.util.Date;

public class PriceEvent {

    private int feed;
    
    private Date timeOfReading;
     
    
    public PriceEvent(int feed, Date timeOfReading) {
        this.feed = feed;
        this.timeOfReading = timeOfReading;
    }

  
    public int getFeed() {
        return feed;
    }
       
   
    public Date getTimeOfReading() {
        return timeOfReading;
    }

    @Override
    public String toString() {
        return "Price of " + feed + "";
    }

}
