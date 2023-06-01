package org.galaxy.station.spi;

import org.galaxy.station.spi.service.IService;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * TODO
 *
 * @author zhouhaitao
 * @date 2023/5/30 18:20
 */
public class JdkSpiServiceFactory {
    public IService getService(boolean first){
        ServiceLoader<IService> serviceLoader = ServiceLoader.load(IService.class);
        IService service = null;
        if(first){
            service = serviceLoader.iterator().next();
        }else {
            Iterator<IService> iterator = serviceLoader.iterator();
            while(iterator.hasNext()){
                service = iterator.next();
            }
        }
        return service;
    }

    public static void main(String[] args) {
        JdkSpiServiceFactory factory = new JdkSpiServiceFactory();
        IService service = factory.getService(false);

        System.out.println(service.hello("zht"));
    }

}
