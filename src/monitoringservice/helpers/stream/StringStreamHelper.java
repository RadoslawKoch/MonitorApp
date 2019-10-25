package monitoringservice.helpers.stream;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import monitoringservice.global.GlobalVariables;

/**
 *
 * @author rkoch
 */
public class StringStreamHelper {
    
    public static String readDataFrom(InputStream inputStream) 
            throws IOException{
        return readDataFrom(inputStream,GlobalVariables.GLOBAL_ENCODING);
    }
    
    public static String readDataFrom(InputStream inputStream, String encoding) 
            throws IOException{
        StringBuilder readData = new StringBuilder();
        try(BufferedReader br = new BufferedReader(
            new InputStreamReader(inputStream, encoding))) {

            String dataLine;
            
            while ((dataLine = br.readLine()) != null) {
                readData.append(dataLine.trim());
            }
        }
        return readData.toString();
    }
    
    public static void sendDataTo(OutputStream outputStream,String dataToWrite) 
            throws IOException{
        sendDataTo(outputStream,dataToWrite,GlobalVariables.GLOBAL_ENCODING);
    }
    
    public static void sendDataTo(OutputStream outputStream,String dataToWrite,String encoding) 
            throws IOException{
        try(BufferedOutputStream bufferedStream = 
                new BufferedOutputStream(outputStream)) {
            bufferedStream.write(dataToWrite.getBytes(encoding));
        }
    }
}
