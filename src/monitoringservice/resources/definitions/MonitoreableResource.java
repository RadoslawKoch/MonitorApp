package monitoringservice.resources.definitions;

/**
 *
 * @author rkoch
 * @param <I> ID type used by class to find an object
 */
public interface MonitoreableResource<I>
    extends Monitoreable{
    
    I getId();
    String getName();
    
    
}
