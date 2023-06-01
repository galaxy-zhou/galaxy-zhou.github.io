package org.galaxy.station.spi;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.galaxy.station.spi.service.IService;

public class DubboSpiServiceFactory {
    public static void main(String[] args) {
        ExtensionLoader<IService> extensionLoader = ExtensionLoader.getExtensionLoader(IService.class);
        IService service = extensionLoader.getExtension("serviceC");
        System.out.println(service.hello("zht"));
    }
}
