package monitoringservice.helpers.http;


import monitoringservice.helpers.stream.StringStreamHelper;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import monitoringservice.global.GlobalVariables;

/**
 *
 * @author rkoch
 */
public class HttpOperationHelper {
    
    public static HttpURLConnection getHttpURLConnection(HttpUrlConnectionProperties connectionProperties) 
            throws IOException{
        URL url = new URL(connectionProperties.getUrlString());
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod(connectionProperties.getRequestType().name());
        connectionProperties.getProperties().forEach(property -> {
            connection.setRequestProperty(property.getKey(), property.getValue());
        });
        connection.setDoOutput(connectionProperties.isDoOutput());
        return connection;
    }
    
    public static HttpRequestResponse getRequestResponseFrom(HttpURLConnection connection) 
            throws IOException{
        
        return getRequestResponseFrom(connection,GlobalVariables.GLOBAL_ENCODING);
    }
    
    public static HttpRequestResponse getRequestResponseFrom(HttpURLConnection connection, String encoding) 
            throws IOException{
        String responseMessage;
        int responseCode = connection.getResponseCode();
        if(!Integer.toString(responseCode).startsWith("2")){
            responseMessage = StringStreamHelper.readDataFrom(connection.getErrorStream(),encoding);
        }else{
            responseMessage = StringStreamHelper.readDataFrom(connection.getInputStream(),encoding);
        }
        return new HttpRequestResponse(responseCode,responseMessage);
    }
    
    public static void sendPayloadDataTo(URLConnection connection, String payloadData) 
            throws IOException{
        StringStreamHelper.sendDataTo(connection.getOutputStream(), payloadData);
    }
    
    public static void sendPayloadDataTo(URLConnection connection, String payloadData,String encoding) 
            throws IOException{
        StringStreamHelper.sendDataTo(connection.getOutputStream(), payloadData, encoding);
    }
}
