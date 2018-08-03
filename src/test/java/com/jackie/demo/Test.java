package com.jackie.demo;

import com.jackie.Config;
import org.testng.Assert;

public class Test {
    @org.testng.annotations.Test
    public void test(){
        Assert.assertEquals(1,1);
    }

    @org.testng.annotations.Test(expectedExceptions = ArithmeticException.class)
    public void divisionWithException(){
        int i = 1/0;
        System.out.println("error with division exception");
        ArithmeticException arithmeticException = new ArithmeticException();


    }
    @org.junit.Test
    public void test1(){
        int a =1;

        a++;
    }

    class A extends Config {

    }
}
