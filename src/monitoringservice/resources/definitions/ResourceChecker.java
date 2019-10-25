package monitoringservice.resources.definitions;


/**
 *
 * @author rkoch
 * @param <T>
 */
@FunctionalInterface
public interface ResourceChecker<T> {
    
    ResourceState checkState(T resource) throws Exception;
    
}
