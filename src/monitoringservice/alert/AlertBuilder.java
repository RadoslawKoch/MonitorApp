package monitoringservice.alert;

import monitoringservice.services.definition.ServiceProxy;

/**
 *
 * @author rkoch
 */
public class AlertBuilder {
    
    private String title;
    private String content;
    private ServiceProxy source;
    private MessageLevel level;
    private int errorCode;

    public AlertBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public AlertBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public AlertBuilder setSource(ServiceProxy source) {
        this.source = source;
        return this;
    }

    public AlertBuilder setLevel(MessageLevel level) {
        this.level = level;
        return this;
    }

    public AlertBuilder setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }
    
    public Alert build(){
        return new ServiceAlert(this.title, this.content, this.source, this.level, this.errorCode);
    }
}
