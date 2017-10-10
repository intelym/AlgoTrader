/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intelym.quickengine.handler;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.intelym.logger.LoggerFactory;
import com.intelym.logger.QuickLogger;

/**
 *
 * @author Rajesh
 */
public class EPSServiceConfig {
    
    private static QuickLogger log = LoggerFactory.getLogger(EPSServiceConfig.class);
    private static EPServiceProvider epService;
    
    public static EPServiceProvider getEPServiceProvider() {
        if(null == epService) {
            log.debug("Initializing EPS Servcie Provider...");
            Configuration config = new Configuration();
            config.addEventTypeAutoName("com.intelym.quickengine.event");
            epService = EPServiceProviderManager.getDefaultProvider(config);
        }
        log.debug("Initialized EPS Servcie Provider...");
        return epService;
    }

}
