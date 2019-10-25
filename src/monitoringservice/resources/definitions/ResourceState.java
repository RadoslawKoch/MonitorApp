package monitoringservice.resources.definitions;

/**
 *
 * @author rkoch
 */
public interface ResourceState {
    
    void setStatus(ResourceStatus status);
    ResourceStatus getStatus();
    void setMessage(String msg);
    String getMessage();
    
}
