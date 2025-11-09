package shared;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class Producer {

    private final String topicName;
    private final Channel channel;

    public Producer(String topicName) throws
            IOException,
            TimeoutException,
            URISyntaxException,
            NoSuchAlgorithmException,
            KeyManagementException
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://userName:password@hostName:portNumber/virtualHost");
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();

        this.topicName = topicName;
        this.channel = channel;
    }

    public void produceMessage(String message) throws
            IOException
    {
        channel.queueDeclare(topicName, true, false, false, null);
        byte[] messageBytes = message.getBytes();
        channel.basicPublish("", topicName, null, messageBytes);
    }
}
