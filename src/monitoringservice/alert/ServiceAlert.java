package monitoringservice.alert;


import java.time.LocalDateTime;
import monitoringservice.resources.definitions.ResourceStatus;
import monitoringservice.services.definition.ServiceProxy;

/**
 *
 * @author rkoch
 */
public class ServiceAlert 
        extends Alert{
    
    public ServiceAlert(String title, String content, ServiceProxy source, MessageLevel level) {
        super(title, content, source, level);
    }
    
    public ServiceAlert(String title, String content, ServiceProxy source, MessageLevel level, int messageCode) {
        super(title, content, source, level);
    }
    
        
    public static Alert createAlertFrom(ServiceProxy service, String additionInformations){
        AlertBuilder builder = new AlertBuilder();
        builder.setTitle(getAlertTitle(service));      
        builder.setSource(service);
        builder.setLevel(service.getState().getStatus()==ResourceStatus.DOWN?
                MessageLevel.ERROR : MessageLevel.WARNING);
        builder.setContent(getAlertContent(service,additionInformations));
        return builder.build();
    }
    
    private static String getAlertTitle(ServiceProxy service){
        StringBuilder builder = new StringBuilder();
        builder.append("[ SERVICE {");
        builder.append(service.getUrl().getPath());
        builder.append("} IS ");
        builder.append(service.getState().getStatus());
        builder.append(" ]");
        return builder.toString();
    }
    
    private static String getAlertContent(ServiceProxy service, String additionInformations){
        StringBuilder builder = new StringBuilder();
        builder.append("Alert time: ");
        builder.append(LocalDateTime.now());
        builder.append("\\nService name: ");
        builder.append(service.getName());
        builder.append("\\nService path: ");
        builder.append(service.getUrl().getPath());
        builder.append("\\nService Protocol: http");
        builder.append("\\nService state: ");
        builder.append(service.getState().getStatus());
        builder.append(additionInformations.length()>0?"\\n":"");
        builder.append(additionInformations);
        return builder.toString();
    }
    
    public static Alert createAlertFrom(ServiceProxy service){
        return createAlertFrom(service,"");
    }
}
