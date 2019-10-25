package monitoringservice.resources.definitions;

/**
 *
 * @author rkoch
 * @param <R>
 */
public interface ResourceAnalyzer<R extends Monitoreable> {
    
    void analyse(R resource) throws Exception;
}
