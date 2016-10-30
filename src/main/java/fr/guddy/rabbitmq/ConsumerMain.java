package fr.guddy.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerMain {
    //region Constants
    private static final String HOST = "localhost";
    private static final String QUEUE_NAME = "sample_queue";
    //endregion

    //region Main
    public static void main(final String[] pasArgv) throws IOException, TimeoutException, InterruptedException {
        final Consumer loConsumer = new Consumer(HOST, QUEUE_NAME);
        final Thread loConsumerThread = new Thread(loConsumer);
        loConsumerThread.start();
    }
    //endregion
}
