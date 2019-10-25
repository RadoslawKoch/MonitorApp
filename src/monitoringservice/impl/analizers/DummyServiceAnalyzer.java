package monitoringservice.impl.analizers;

import monitoringservice.services.definition.ServiceProxy;
import monitoringservice.services.definition.ServiceAnalizer;

/**
 *
 * @author rkoch
 */
public class DummyServiceAnalyzer
    implements ServiceAnalizer{

    @Override
    public void analyse(ServiceProxy resource) throws Exception {}
    
}
