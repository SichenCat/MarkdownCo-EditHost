package com.demo1;

import org.junit.Assert;
import org.junit.Test;

public class Testtest1 {
    /**
     * 必须是公开、无参数、无返回值的方法
     * 必须用@test注解标记
     */
    @Test
    public void testMethodForTest( ){
        test1 t1= new test1();
        String rs = t1.methodForTest();
        Assert.assertEquals("测试结果有误","OK",rs);
    }
}
