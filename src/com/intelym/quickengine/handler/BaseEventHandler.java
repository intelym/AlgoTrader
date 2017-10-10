/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intelym.quickengine.handler;

import com.espertech.esper.client.EPServiceProvider;
import com.intelym.logger.LoggerFactory;
import com.intelym.logger.QuickLogger;
import com.intelym.quickengine.event.PriceEvent;


/**
 *
 * @author Rajesh
 */
public abstract class BaseEventHandler {
    
    private static QuickLogger log = LoggerFactory.getLogger(BaseEventHandler.class);
    protected EPServiceProvider epService;
    
    //Handle the incoming Event.
    public void handle(PriceEvent event) {
        log.debug(event.toString());
        epService.getEPRuntime().sendEvent(event);
    }
}
