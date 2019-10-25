package monitoringservice.resources.definitions;


/**
 *
 * @author rkoch
 */
@FunctionalInterface
public interface Monitoreable {
    
    ResourceState checkState() throws Exception;
    
}
