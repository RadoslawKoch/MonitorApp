package monitoringservice.impl.http;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import monitoringservice.def.http.HttpConnectProxy;

/**
 *
 * @author rkoch
 */
public class HttpConnectProxyImpl 
    implements HttpConnectProxy{
    
    private final URL url;

    public HttpConnectProxyImpl(String url) throws MalformedURLException{
        this.url = new URL(url);
    }

    @Override
    public HttpURLConnection getUrlConnection() throws Exception {
        return (HttpURLConnection)this.url.openConnection();
    }

    @Override
    public String getPath() {
        return this.url.getPath();
    }

    @Override
    public String getProtocol() {
        return this.url.getProtocol();
    }

    @Override
    public String getHost() {
        return this.url.getHost();
    }

    @Override
    public int getPort() {
        return this.url.getPort();
    }
    
}
