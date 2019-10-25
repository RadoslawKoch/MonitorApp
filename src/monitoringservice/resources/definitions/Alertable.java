package monitoringservice.resources.definitions;

import monitoringservice.alert.Alert;

/**
 *
 * @author rkoch
 */
public interface Alertable {
    
   void raiseAlert(Alert alert) throws Exception;
   
}
