package monitoringservice.impl.alert.services;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import monitoringservice.alert.Alert;
import monitoringservice.resources.definitions.Alertable;
import monitoringservice.db.ElasticSearchException;
import monitoringservice.db.ElasticSearchIndex;

/**
 *
 * @author rkoch
 */
public class ElasticSearchAlertService 
        implements Alertable{
    
    private final ElasticSearchIndex elasticSearchIndex;
    
    public ElasticSearchAlertService(ElasticSearchIndex elasticSearchIndex)
        throws ElasticSearchException{
        if(elasticSearchIndex==null){
            throw new ElasticSearchException("ElasticSearchIndex instance cannot be null.");
        }
        this.elasticSearchIndex = elasticSearchIndex;
    }

    @Override
    public void raiseAlert(Alert alert) 
            throws Exception {  
        try {
            this.elasticSearchIndex.addDocument(alert.toJson());
        } catch (IOException | ElasticSearchException ex) {
            Logger.getLogger(ElasticSearchAlertService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
