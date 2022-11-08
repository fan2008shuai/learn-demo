package org.fan.learn.demo.spring.aop.proxy;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class ProxyAspect {
    @DeclareParents(value = "org.fan.learn.demo.spring.aop.biz.Biz+", defaultImpl = ProxyImpl.class)
    Proxy proxy;
}
