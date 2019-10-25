package monitoringservice.impl.resources;

import java.net.URL;
import monitoringservice.def.http.HttpConnectProxy;
import monitoringservice.services.definition.ServiceProxy;
import monitoringservice.services.definition.ServiceAnalizer;
import monitoringservice.services.definition.ServiceChecker;

/**
 *
 * @author rkoch
 */
public class ServiceProxyImpl 
        extends ServiceProxy{

    public ServiceProxyImpl(HttpConnectProxy url,ServiceChecker serviceChecker,ServiceAnalizer serviceAnalizer) {
        super(url, serviceChecker, serviceAnalizer);
    }
  
}
