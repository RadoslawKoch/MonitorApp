package monitoringservice.helpers.http;

/**
 *
 * @author rkoch
 */
public class HttpRequestResponse {
    
    private final int code;
    private final String message;

    public HttpRequestResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "HttpRequestResponse{" + "code=" + code + ", message=" + message + '}';
    }
    
    
    
}
