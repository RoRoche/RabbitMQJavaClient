package fr.guddy.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerMain {

    //region Constants
    private static final String HOST = "localhost";
    private static final String QUEUE_NAME = "sample_queue";
    //endregion

    //region Main
    public static void main(final String[] pasArgv) throws IOException, TimeoutException, InterruptedException {
        final Producer loProducer = new Producer(HOST, QUEUE_NAME);
        for (int liIndex = 0; liIndex < 500; liIndex++) {
            loProducer.publishMessage("My message #" + liIndex);
            System.out.println("Thread #" + Thread.currentThread().getId() + " - message #" + liIndex + " sent to queue");
            Thread.sleep(1000L);
        }
        loProducer.close();
    }
    //endregion
}
