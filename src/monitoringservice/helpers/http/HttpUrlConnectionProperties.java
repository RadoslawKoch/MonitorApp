package monitoringservice.helpers.http;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rkoch
 */
public class HttpUrlConnectionProperties {
    
    private final String urlString;
    private final HttpRequestType requestType;
    private boolean doOutput = true;
    private final List<HttpRequestProperty> httpProperties = new ArrayList();

    public HttpUrlConnectionProperties(String urlString, HttpRequestType requestType) {
        this.urlString = urlString;
        this.requestType = requestType;
    }
    
    public void addHttpProperty(String key, String value){
        this.httpProperties.add(new HttpRequestProperty(key,value));
    }

    public boolean isDoOutput() {
        return this.doOutput;
    }

    public void setDoOutput(boolean doOutput) {
        this.doOutput = doOutput;
    }

    public String getUrlString() {
        return this.urlString;
    }

    public HttpRequestType getRequestType() {
        return this.requestType;
    }

    public List<HttpRequestProperty> getProperties() {
        return this.httpProperties;
    }
    
    
    
    
    
    
}
