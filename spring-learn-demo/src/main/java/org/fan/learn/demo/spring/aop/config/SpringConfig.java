package org.fan.learn.demo.spring.aop.config;

import org.fan.learn.demo.spring.aop.biz.Biz;
import org.fan.learn.demo.spring.aop.biz.HomeBiz;
import org.fan.learn.demo.spring.aop.log.LogPrinter;
import org.fan.learn.demo.spring.aop.proxy.Proxy;
import org.fan.learn.demo.spring.aop.proxy.ProxyAspect;
import org.fan.learn.demo.spring.aop.proxy.ProxyImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class SpringConfig {

    /**
     *
     * @return  返回值不能是接口Printer，否则切面增强无效
     */
    @Bean
    public LogPrinter logPrinter() {
        return new LogPrinter();
    }

    /**
     * 为biz增加新功能
     * @return
     */
    @Bean
    public ProxyAspect proxyAspect() {
        return new ProxyAspect();
    }

    @Bean
    public Biz homeBiz() {
        return new HomeBiz();
    }


}
