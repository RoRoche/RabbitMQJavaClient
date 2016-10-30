package fr.guddy.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    //region Fields
    private final MessageQueue mMessageQueue;
    //endregion

    //region Constructor
    public Producer(final String psHost, final String psQueueName) throws IOException, TimeoutException {
        mMessageQueue = new MessageQueue(psHost, psQueueName);
    }
    //endregion

    //region Visible API
    public void publishMessage(final String psMessage) throws IOException {
        mMessageQueue.getChannel().basicPublish("", mMessageQueue.getQueueName(), null, psMessage.getBytes());
    }

    public void close() throws IOException {
        mMessageQueue.close();
    }
    //endregion
}
