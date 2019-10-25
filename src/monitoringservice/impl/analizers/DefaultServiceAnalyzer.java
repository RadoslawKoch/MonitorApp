package monitoringservice.impl.analizers;

import java.net.InetAddress;
import monitoringservice.alert.ServiceAlert;
import monitoringservice.resources.definitions.Alertable;
import monitoringservice.resources.definitions.ResourceState;
import monitoringservice.resources.definitions.ResourceStatus;
import monitoringservice.services.definition.ServiceAnalizer;
import monitoringservice.services.definition.ServiceProxy;

/**
 *
 * @author rkoch
 */
public class DefaultServiceAnalyzer 
    implements ServiceAnalizer{
    
    private final Alertable alertableService;

    public DefaultServiceAnalyzer(Alertable alertableService) {
        this.alertableService = alertableService;
    } 

    @Override
    public void analyse(ServiceProxy resource) throws Exception {
        ResourceState state = resource.getState();
        if(state.getStatus()==ResourceStatus.DOWN){
            InetAddress address = InetAddress.getByName(resource.getUrl().getHost());
            this.alertableService
                    .raiseAlert(ServiceAlert
                            .createAlertFrom(resource,
                                    "Host PING: ".concat(address.isReachable(2000)?"OK":"KO")));

        }
    }
    
    
}
