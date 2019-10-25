package monitoringservice;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import monitoringservice.resources.definitions.Monitoreable;
import monitoringservice.resources.definitions.MonitoreableResource;

/**
 *
 * @author rkoch
 * @param <S>
 * @param <T>
 */
public class ResourceMonitor<T> {
    
    private final Set<MonitoreableResource<T>> monitoredResources = new HashSet();
    private ExecutorService monitoringExecutorService;
    private Thread asyncMonitoringThread;

    public void addResourceToMonitor(MonitoreableResource<T> resourceToMonitor){
        this.monitoredResources.add(resourceToMonitor);
    }
    
    public void removeResourceFromMonitor(T resourceId){
        Optional<MonitoreableResource<T>> resourceToRemove =
                getMonitoredResource(resourceId);
        if(resourceToRemove.isPresent()){
            this.monitoredResources.remove(resourceToRemove.get());
        }
    }
    
    public Optional<MonitoreableResource<T>> getMonitoredResource(T resourceId){
        return this.monitoredResources
                .stream()
                .filter(resource->resource.getId().equals(resourceId))
                .findFirst();
    }
    
    public void startMonitor(){
        this.monitoredResources.forEach(resource -> {
            try {              
                resource.checkState();
            } catch (Exception ex) {
                Logger.getLogger(ResourceMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    private void monitorParallel(){
        this.monitoringExecutorService = Executors.newCachedThreadPool();
        
        this.monitoredResources.forEach(resource -> {
            try {
                this.monitoringExecutorService.execute(()->{
                    try {
                        resource.checkState();
                    } catch (Exception ex) {
                        Logger.getLogger(ResourceMonitor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            } catch (Exception ex) {
                Logger.getLogger(ResourceMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        monitoringExecutorService.shutdown();
    }
    
    public synchronized Thread startAsyncMonitor(int delay){
        this.asyncMonitoringThread = new Thread(()->{
            for(;;){          
                try {
                    monitorParallel();
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ResourceMonitor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        asyncMonitoringThread.start();
        return asyncMonitoringThread;
    }
    
    public Set<Monitoreable> getMonitoredResources(Predicate<MonitoreableResource<T>>... searchPredicates){
        Stream<MonitoreableResource<T>> monitoredResourceStream = this.monitoredResources.stream();
        for(Predicate predicate : searchPredicates){
            monitoredResourceStream = monitoredResourceStream.filter(predicate);
        }
        return monitoredResourceStream.collect(Collectors.toSet());
    }
    
    public Set<MonitoreableResource<T>> getMonitoredResources(){
        return this.monitoredResources;
    }
    
    public void displayMonitoredResources(){
        this.monitoredResources.forEach(monitoredResource-> {
            System.out.println(monitoredResource);
        });     
    }
    
    public void stopAsyncMonitor(){
        if(this.asyncMonitoringThread!=null && 
                this.asyncMonitoringThread.isInterrupted()){            
            this.asyncMonitoringThread.interrupt();
        }
    }
    
//    private Alert generateAlert(Service service){
//        AlertBuilder builder = new AlertBuilder();
//        builder.setSource(service);
//        builder.setLevel(MessageLevel.ERROR);
//        builder.setContent("Error was detected on service "
//                .concat(": SERVICE STATUS: DOWN"));
//        builder.setTitle("ERROR[SERVICE:".concat(service.getName()).concat("] UNAVAILABLE"));
//        return builder.build();
//    }

}
