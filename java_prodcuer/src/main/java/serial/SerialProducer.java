package serial;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.UUID;
import java.time.Instant;

import shared.Producer;

public class SerialProducer {

    private final Producer messageProdcuer;

    public SerialProducer(Producer messageProducer)
    {
        this.messageProdcuer = messageProducer;
    }

    public void produce() throws
            IOException,
            TimeoutException
    {
        String[] pathArray = {
            "data/data_1MB",
            "data/data_5MB",
            "data/data_10MB",
            "data/data_25MB",
            "data/data_50MB",
        };
        for (String path : pathArray) {
            byte[] bytes = Files.readAllBytes(Path.of(path));
            String fileContent = new String(bytes); 
            String startTime = Instant.now().toString();
            UUID taskId = UUID.randomUUID();
            
            System.out.println(path + " " + taskId);

            Map<String, Object> message = new HashMap<>();
            message.put("taskId", taskId);
            message.put("all", 1); 
            message.put("value", fileContent);
            message.put("start", startTime);

            this.messageProdcuer.produceMessage(message);
        }
        this.messageProdcuer.closeConnection();
    }
}
