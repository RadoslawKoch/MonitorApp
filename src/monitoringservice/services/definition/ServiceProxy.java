package monitoringservice.services.definition;

import java.net.URL;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import monitoringservice.def.http.HttpConnectProxy;
import monitoringservice.resources.definitions.ResourceStatus;
import monitoringservice.resources.definitions.ResourceState;
import monitoringservice.impl.resources.ResourceStateImpl;
import monitoringservice.resources.definitions.MonitoreableResource;

/**
 *
 * @author rkoch
 */
public abstract class ServiceProxy 
    implements MonitoreableResource<Long>{
    
    private final static AtomicInteger COUNTER = new AtomicInteger(1);    
    private final long id;
    private final HttpConnectProxy url;
    private final String name;
    private final ResourceState state;
    private final ServiceChecker serviceChecker;
    private final ServiceAnalizer serviceAnalizer;

    public ServiceProxy(HttpConnectProxy url, ServiceChecker checker, ServiceAnalizer serviceAnalizer) {
        this.id = COUNTER.getAndIncrement();
        this.url = url;
        this.name = url.getPath();
        this.serviceChecker = checker;
        this.state = new ResourceStateImpl(ResourceStatus.UNKNOWN,"");
        this.serviceAnalizer = serviceAnalizer;
    }

    @Override
    public ResourceState checkState() throws Exception{
        if(isMonitored()){
            verifyResourceStateChange(this.serviceChecker.checkState(this));       
        }
        return this.state;
    }
    
    public boolean isMonitored(){
        return this.state.getStatus()!=ResourceStatus.FRONZEN;
    }
    
    private void verifyResourceStateChange(ResourceState last) throws Exception{
        if(this.state.getStatus()!=last.getStatus()){
                updateCurrentState(last);
                this.serviceAnalizer.analyse(this);
        }
    }
    
    private void updateCurrentState(ResourceState lastState){
        this.state.setMessage(lastState.getMessage());
        this.state.setStatus(lastState.getStatus());
    }
    
    public ResourceState getState(){
        return this.state;
    }
    
    @Override
    public Long getId() {
        return this.id;
    }

    public HttpConnectProxy getUrl() {
        return this.url;
    }

    @Override
    public String getName() {
        return this.name;
    }
   
    public void turnOn(){
        this.state.setStatus(ResourceStatus.FRONZEN);
    }
    
    public void turnOff(){
        this.state.setStatus(ResourceStatus.UNKNOWN);
    }

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", url=" + url + ", name=" + name + ", status=" + this.state + ", monitored=" + isMonitored() + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.url);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ServiceProxy other = (ServiceProxy) obj;
        return Objects.equals(this.url, other.url);
    }  
}
