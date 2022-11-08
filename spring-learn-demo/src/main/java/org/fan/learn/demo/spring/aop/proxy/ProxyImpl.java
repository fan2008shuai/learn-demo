package org.fan.learn.demo.spring.aop.proxy;

public class ProxyImpl implements Proxy {
    @Override
    public void enhance() {
        System.out.println("in proxy enhance");
    }
}
