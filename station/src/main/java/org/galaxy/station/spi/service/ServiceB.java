package org.galaxy.station.spi.service;

/**
 * TODO
 *
 * @author zhouhaitao
 * @date 2023/5/30 18:18
 */
public class ServiceB implements IService{
    @Override
    public String hello(String params) {
        return "ServiceB";
    }
}
