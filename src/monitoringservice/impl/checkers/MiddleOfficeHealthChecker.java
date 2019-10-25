package monitoringservice.impl.checkers;

import java.net.HttpURLConnection;
import monitoringservice.global.GlobalVariables;
import monitoringservice.helpers.http.HttpOperationHelper;
import monitoringservice.helpers.http.HttpRequestResponse;
import monitoringservice.resources.definitions.ResourceState;
import monitoringservice.resources.definitions.ResourceStatus;
import monitoringservice.services.definition.ServiceProxy;
import monitoringservice.services.definition.ServiceChecker;
import monitoringservice.impl.resources.ResourceStateImpl;

/**
 *
 * @author rkoch
 */
public class MiddleOfficeHealthChecker 
        implements ServiceChecker{
    
            
    private final String searchWord = "state";
    private final int messageOffsetBefore = 8;
    private final int messageOffsetAfter = 10;
    private final int validCheckpointNumber = 4;
    
    @Override
    public ResourceState checkState(ServiceProxy resource) throws Exception {
        HttpURLConnection httpConnection = resource.getUrl().getUrlConnection();
        HttpRequestResponse response = HttpOperationHelper.getRequestResponseFrom(httpConnection);
        if(Integer.toString(response.getCode()).startsWith("2") && isResponseValid(response.getMessage())){
            return new ResourceStateImpl(ResourceStatus.UP,response.getMessage());
        }
        return new ResourceStateImpl(ResourceStatus.DOWN,response.getMessage());
    }

        
    private boolean isResponseValid(String message){
        int checkPointIndex = 0;
        int checkPointCounter = 0;
        while((checkPointIndex = message
                .indexOf(searchWord, checkPointIndex+searchWord.length()))!=-1){
            
             checkPointCounter++;
             String state = message
                     .substring(checkPointIndex+messageOffsetBefore,
                     checkPointIndex+messageOffsetAfter);
             
             if(!state.equals(GlobalVariables.HEALTH_CHECKER_VALID_RESPONSE_STATUS))
                 return false;
        }
        return checkPointCounter==validCheckpointNumber;
    }

}
