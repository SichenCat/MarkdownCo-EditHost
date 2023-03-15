package com.demo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class demo1 {
     // 创建logback对象
    public static final Logger LOGGER = LoggerFactory.getLogger("demo1.class");
    public static void main(String[] args) {
        try {
        LOGGER.debug("main starts.");
        LOGGER.info("line1: go divide");
        int a=10,b=0;
        LOGGER.trace("a=" + a);
        LOGGER.trace("b=" + b);

            System.out.println( a / b);
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("error occurs:"+e);
        }
    }
}
