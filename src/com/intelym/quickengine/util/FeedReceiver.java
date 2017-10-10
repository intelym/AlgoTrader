/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intelym.quickengine.util;

import com.intelym.quick.java.client.common.CommonUtil;
import com.intelym.quick.java.client.data.DerivativePacket;
import com.intelym.quick.java.client.data.IndexPacket;
import com.intelym.quick.java.client.data.MarketDepthPacket;
import com.intelym.quick.java.client.data.OpenInterestPacket;
import com.intelym.quick.java.client.data.Packet;
import com.intelym.quick.java.client.data.QuotePacket;
import com.intelym.quick.java.client.data.UnsolicitedPacket;
import com.intelym.quick.java.client.services.EventDetails;
import com.intelym.quick.java.client.services.Handler;
import com.intelym.quick.java.client.services.MarketData;
import com.intelym.quick.java.client.services.QuickEvent;
import com.intelym.quickengine.event.PriceEvent;
import com.intelym.quickengine.handler.TWAPEventHandler;
import java.util.Date;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Rajesh
 */
//@Component
public class FeedReceiver  implements QuickEvent {
    private final static Logger log = LoggerFactory.getLogger(FeedReceiver.class);
    private Handler handler;
    
    //@Autowired
    private TWAPEventHandler twapEventHandler;
    
    public FeedReceiver() {
        try {
            log.info("Test");
           handler = MarketData.GetInstance();
           handler.setEventHandler(this);
           handler.setAddress("192.168.1.108");
           handler.setPort(9898);
           handler.setUserCredentials("PREM", "xxxxx");
           if(handler.connect()) {
               log.info("Connect initiated ");
           } else {
               log.info("Connect failed ");
           }   
        } catch(Exception e) {
            log.info("Exception :: " + e.getMessage());
        }
    }
    @Override
    public void onConnect() {
        System.out.println("StartQuickClient.OnConnect(:::Connect succeeded:::>)");
        twapEventHandler = TWAPEventHandler.getInstance();
				handler.addScrip(0, "22");
    }

    @Override
    public void onDisconnect(EventDetails details) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onError(EventDetails details) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onPacketArrived(Packet packet) {
        
        log.info("Packet Received :: " + packet.getScripCode());
        
        if (packet instanceof IndexPacket) {
            System.out.println("StartQuickClient.onPacketArrived(:::IndexPacket Arrived:::)");
            System.out.println("timestamp::"+packet.getTimestamp());
			
        } else if (packet instanceof DerivativePacket) {
            System.out.println("StartQuickClient.onPacketArrived(:::Derivative Quote Packet Arrived:::)");
            System.out.println(packet.getTimestamp());

        } else if (packet instanceof QuotePacket) {
            
            PriceEvent ve = new PriceEvent((((QuotePacket) packet).getLastTradedPrice()), new Date());
                twapEventHandler.handle(ve);
            System.out.println("StartQuickClient.onPacketArrived(:::QuotePacket Arrived:::)");
            System.out.println(((QuotePacket) packet).getLastTradedPrice());

        } else if(packet instanceof MarketDepthPacket){
            MarketDepthPacket p = (MarketDepthPacket)packet ;//var p = packet as MarketDepthPacket;
            String exchangeTime = CommonUtil.fromExchangeTime(packet.getTimestamp(), packet.getExchange());

            System.out.println("Scripcode :" + packet.getScripCode());
            System.out.println("BuyOrders " + p.getBuyOrders()[0] + " SellOrders " + p.getSellOrders()[0]);
            System.out.println("BuyOrders " + p.getBuyOrders()[1] + " SellOrders " + p.getSellOrders()[1]);
            System.out.println("BuyOrders " + p.getBuyOrders()[2] + " SellOrders " + p.getSellOrders()[2]);
            System.out.println("BuyOrders " + p.getBuyOrders()[3] + " SellOrders " + p.getSellOrders()[3]);
            System.out.println("BuyOrders " + p.getBuyOrders()[4] + " SellOrders " + p.getSellOrders()[4]);

            System.out.println("BuyPrice " + p.getBuyPrice()[0] + " SellPrice " + p.getSellPrice()[0]);
            System.out.println("BuyPrice " + p.getBuyPrice()[1] + " SellPrice " + p.getSellPrice()[1]);
            System.out.println("BuyPrice " + p.getBuyPrice()[2] + " SellPrice " + p.getSellPrice()[2]);
            System.out.println("BuyPrice " + p.getBuyPrice()[3] + " SellPrice " + p.getSellPrice()[3]);
            System.out.println("BuyPrice " + p.getBuyPrice()[4] + " SellPrice " + p.getSellPrice()[4]);

            System.out.println("BuyQty " + p.getBuyQty()[0] + " SellQty " + p.getSellQty()[0]);
            System.out.println("BuyQty " + p.getBuyQty()[1] + " SellQty " + p.getSellQty()[1]);
            System.out.println("BuyQty " + p.getBuyQty()[2] + " SellQty " + p.getSellQty()[2]);
            System.out.println("BuyQty " + p.getBuyQty()[3] + " SellQty " + p.getSellQty()[3]);
            System.out.println("BuyQty " + p.getBuyQty()[4] + " SellQty " + p.getSellQty()[4]);

            System.out.println(exchangeTime);
            System.out.println(p.getTimestamp());
        } else if (packet instanceof UnsolicitedPacket) {
            System.out.println("StartQuickClient.onPacketArrived(:::UnsolicitedPacket Arrived:::)");
        } else if (packet instanceof OpenInterestPacket) {
            System.out.println("StartQuickClient.onPacketArrived(:::Open Interest Packet Arrived:::)");
            //OpenInterestPacket p = (OpenInterestPacket)packet ;
            System.out.println("timestamp:::"+packet.getTimestamp());
        }
    }

    @Override
    public void onPacketArrived(Packet[] packet) {
        for (Packet p : packet) {
            log.info("p Received :: " + p.getScripCode());
        }
    }
    
}
