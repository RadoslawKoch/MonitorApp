package monitoringservice.helpers.http;

/**
 *
 * @author rkoch
 */
public class HttpRequestProperty {
    
    private final String key;
    private final String value;

    public HttpRequestProperty(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    } 
}
