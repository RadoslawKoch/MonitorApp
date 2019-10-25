
package monitoringservice.alert;

import java.time.LocalDateTime;
import monitoringservice.resources.definitions.Monitoreable;
/**
 *
 * @author rkoch
 */
public interface Message {
    
    String getTitle();
    String getContent();
    MessageLevel getLevel();
    LocalDateTime getTimestamp();
    Integer getMessageCode();
    Monitoreable getSource();
}
