package parallel;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import shared.Producer;

public class ParallelImplementation {

    private final ParallelProducer parallelMessageProducer;

    public ParallelImplementation() throws
            IOException,
            URISyntaxException,
            NoSuchAlgorithmException,
            KeyManagementException,
            TimeoutException
    {
        Producer messageProducer = new Producer("content_parse2");
        this.parallelMessageProducer = new ParallelProducer(messageProducer);
    }

    public void run() throws
            IOException,
            TimeoutException
    {
        this.parallelMessageProducer.produce();
    }
}
