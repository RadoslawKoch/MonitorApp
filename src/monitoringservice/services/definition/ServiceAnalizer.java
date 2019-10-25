package monitoringservice.services.definition;

import monitoringservice.resources.definitions.ResourceAnalyzer;

/**
 *
 * @author rkoch
 */
@FunctionalInterface
public interface ServiceAnalizer 
        extends ResourceAnalyzer<ServiceProxy>{
    
}
