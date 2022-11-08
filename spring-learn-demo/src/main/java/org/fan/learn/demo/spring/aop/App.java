package org.fan.learn.demo.spring.aop;

import org.fan.learn.demo.spring.aop.biz.Biz;
import org.fan.learn.demo.spring.aop.config.SpringConfig;
import org.fan.learn.demo.spring.aop.proxy.Proxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        Biz biz = (Biz) applicationContext.getBean("homeBiz");
        biz.biz("test aspect");
        Proxy proxy = (Proxy) biz;
        proxy.enhance();
    }
}
