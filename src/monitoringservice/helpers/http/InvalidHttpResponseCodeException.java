package monitoringservice.helpers.http;

import java.io.IOException;

/**
 *
 * @author rkoch
 */
public class InvalidHttpResponseCodeException extends IOException{

    public InvalidHttpResponseCodeException(String message) {
        super(message);
    }
    
    
}
