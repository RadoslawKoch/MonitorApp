package monitoringservice.alert;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.atomic.AtomicInteger;
import monitoringservice.global.GlobalVariables;
import monitoringservice.resources.definitions.Monitoreable;

/**
 *
 * @author rkoch
 */
public abstract class Alert 
        implements Message{
    
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    
    private final long id;
    private final String title;
    private final String content;
    private final Monitoreable source;
    private final MessageLevel level;
    private int messageCode;
    private final LocalDateTime time; 
    
    public Alert(String title, String content, Monitoreable source,MessageLevel level) {
        this.id = COUNTER.getAndIncrement();
        this.title = title;
        this.content = content;
        this.source = source;
        this.level = level;
        this.time = LocalDateTime.now(ZoneId.of(GlobalVariables.USED_TIME_ZONE));
    }

    public Alert(String title, String content, Monitoreable source, MessageLevel level, int errorCode) {
        this(title,content,source,level);
        this.messageCode = errorCode;
    }

    public long getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }
    
    @Override
    public String getContent() {
        return content;
    }

    @Override
    public Monitoreable getSource() {
        return source;
    }

    @Override
    public MessageLevel getLevel() {
        return level;
    }
    
    @Override
    public Integer getMessageCode() {
        return messageCode;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return time;
    }  
    
    public String toJson(){
        StringBuilder jsonBuilder = new StringBuilder("{");
        jsonBuilder.append("\"title\":\"").append(this.title).append("\",\n");
        jsonBuilder.append("\"content\":\"").append(this.content).append("\",\n");
        jsonBuilder.append("\"source\":\"").append(this.source).append("\",\n");
        jsonBuilder.append("\"time\":\"").append(this.time).append("\",\n");
        jsonBuilder.append("\"level\":\"").append(this.level).append("\"}");
        return jsonBuilder.toString();
    }
    
    @Override
    public String toString() {
        return this.level+this.title+
                "\n"+ content + 
                "\nsource=" + source + 
                "\nlevel=" + level + 
                "\nerrorCode=" + messageCode;
    } 


}
