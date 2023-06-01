package org.galaxy.station.spi;

import org.galaxy.station.spi.service.IService;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;

public class SpringSpiServiceFactory {

    public static void main(String[] args) {
        List<IService> services = SpringFactoriesLoader.loadFactories(IService.class,IService.class.getClassLoader());
        System.out.println(services.get(0).hello("zht"));
    }
}
