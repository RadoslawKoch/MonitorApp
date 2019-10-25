package monitoringservice.impl.checkers;

import monitoringservice.resources.definitions.ResourceState;
import monitoringservice.services.definition.ServiceChecker;
import monitoringservice.services.definition.ServiceProxy;

/**
 *
 * @author rkoch
 */
public class DummyServiceChecker 
        implements ServiceChecker{

    @Override
    public ResourceState checkState(ServiceProxy resource) throws Exception {
        return resource.getState();
    }


}
