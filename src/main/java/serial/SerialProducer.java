package serial;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeoutException;

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
                "src/main/java/data/data_1MB",
        };
        String content = Files.readString(Path.of("src/main/java/data/data_1MB"));
        this.messageProdcuer.produceMessage(content);
        this.messageProdcuer.closeConnection();
    }
}
