/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intelym.quickengine.util;
import com.intelym.quickengine.util.*;
/**
 *
 * @author Rajesh
 */
public class EPLStatement {
    
    public static final String TVAP = "select avg(feed) as avg_val from PriceEvent.win:time_batch(5 sec)";
    
}
