package monitoringservice.def.http;

import java.net.HttpURLConnection;

/**
 *
 * @author rkoch
 */
public interface HttpConnectProxy {
     
    HttpURLConnection getUrlConnection() throws Exception;
    String getPath();
    String getProtocol();
    String getHost();
    int getPort();
      
}
