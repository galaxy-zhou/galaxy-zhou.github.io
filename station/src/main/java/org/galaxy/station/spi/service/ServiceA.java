package org.galaxy.station.spi.service;

/**
 * TODO
 *
 * @author zhouhaitao
 * @date 2023/5/30 18:18
 */
public class ServiceA implements IService{
    @Override
    public String hello(String params) {
        return "ServiceA";
    }
}
