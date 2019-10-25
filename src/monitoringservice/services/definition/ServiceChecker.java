package monitoringservice.services.definition;

import monitoringservice.resources.definitions.ResourceChecker;

/**
 *
 * @author rkoch
 */
@FunctionalInterface
public interface ServiceChecker 
        extends ResourceChecker<ServiceProxy>{
    
}
