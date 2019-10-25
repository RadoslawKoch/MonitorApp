package monitoringservice.db;

import java.io.IOException;
import java.net.HttpURLConnection;
import monitoringservice.global.GlobalVariables;
import monitoringservice.helpers.http.HttpOperationHelper;
import monitoringservice.helpers.http.HttpRequestResponse;
import monitoringservice.helpers.http.HttpUrlConnectionProperties;
import monitoringservice.helpers.http.HttpRequestType;

/**
 *
 * @author rkoch
 */
public class ElasticSearchIndex {
    
    private final String elasticSearchUrl;
    
    public ElasticSearchIndex(String elasticSearchUrl) {
        this.elasticSearchUrl = elasticSearchUrl;
    }
        
    public void addDocument(String jsonInput) 
            throws IOException, ElasticSearchException{
        
        HttpUrlConnectionProperties httpProperties = 
                new HttpUrlConnectionProperties(elasticSearchUrl.concat("/_doc"), HttpRequestType.POST);
        
        httpProperties.addHttpProperty("Content-Type","application/json; utf-16");
        httpProperties.addHttpProperty("Accept","application/json");
        
        HttpURLConnection httpConnection = HttpOperationHelper
                .getHttpURLConnection(httpProperties);
        
        HttpOperationHelper.sendPayloadDataTo(httpConnection, jsonInput);

        HttpRequestResponse httpResponse = HttpOperationHelper.getRequestResponseFrom(httpConnection);
        httpConnection.disconnect();
        validateReceivedResposeCode(httpResponse);

    }
    
    private void validateReceivedResposeCode(HttpRequestResponse httpResponse) 
            throws ElasticSearchException{
        if(!Integer.toString(httpResponse.getCode())
                .startsWith(GlobalVariables.VALID_HTTP_RESPONSE_CODE)){
            throw new ElasticSearchException("Zapis do bazy danych ElasticSearch się nie powiódł."
                    + "\nBłąd: "+httpResponse.getMessage()
            +"\nKod odpowiedzi: "+httpResponse.getCode());     
        }
    }
    
    public void deleteDocument(String documentId) 
            throws IOException {
        HttpUrlConnectionProperties properties = 
                new HttpUrlConnectionProperties(elasticSearchUrl
                        .concat("/_doc/".concat(documentId)), HttpRequestType.DELETE);
        
        properties.setDoOutput(false);
        
        HttpURLConnection connection = HttpOperationHelper.getHttpURLConnection(properties);
        connection.disconnect();
    }
    
}
