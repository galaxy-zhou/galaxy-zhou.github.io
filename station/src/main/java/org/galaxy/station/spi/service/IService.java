package org.galaxy.station.spi.service;

import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * TODO
 *
 * @author zhouhaitao
 * @date 2023/5/30 15:47
 */
@SPI
public interface IService {
    String hello(String params);
}
