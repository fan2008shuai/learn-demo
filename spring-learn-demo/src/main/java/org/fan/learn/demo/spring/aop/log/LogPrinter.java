package org.fan.learn.demo.spring.aop.log;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogPrinter implements Printer {

    @Pointcut("execution(* org.fan.learn.demo.spring.aop.biz.Biz.biz(String)) && args(business)")
    public void printLog(String business) {}

    @Before("printLog(business)")
    @Override
    public void log(String business) {
        System.out.println(business);
    }
}
