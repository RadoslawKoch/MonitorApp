package monitoringservice.impl.alert.services;

import monitoringservice.alert.Alert;
import monitoringservice.resources.definitions.Alertable;

/**
 *
 * @author rkoch
 */
public class DefaultAlertService 
        implements Alertable {

    @Override
    public void raiseAlert(Alert alert) {
        System.out.println(alert.toJson());
    }
    
}
