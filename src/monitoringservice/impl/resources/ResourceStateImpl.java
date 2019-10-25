package monitoringservice.impl.resources;

import monitoringservice.resources.definitions.ResourceState;
import monitoringservice.resources.definitions.ResourceStatus;

/**
 *
 * @author rkoch
 */
public class ResourceStateImpl 
        implements ResourceState{
    
    private ResourceStatus status;
    private String message;

    public ResourceStateImpl(ResourceStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public ResourceStatus getStatus() {
       return this.status;
    }

    @Override
    public String getMessage() {
        return this.message;
    }  

    @Override
    public void setStatus(ResourceStatus status) {
        this.status = status;
    }

    @Override
    public void setMessage(String msg) {
        this.message = msg;
    }
    
}
