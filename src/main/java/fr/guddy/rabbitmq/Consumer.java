package fr.guddy.rabbitmq;

import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer implements Runnable {
    //region Fields
    private final MessageQueue mMessageQueue;
    //endregion

    //region Constructor
    public Consumer(final String psHost, final String psQueueName) throws IOException, TimeoutException {
        mMessageQueue = new MessageQueue(psHost, psQueueName);
    }
    //endregion

    //region Runnable
    @Override
    public void run() {
        try {
            final QueueingConsumer loConsumer = new QueueingConsumer(mMessageQueue.getChannel());
            mMessageQueue.getChannel().basicConsume(mMessageQueue.getQueueName(), true, loConsumer);
            while (true) {
                final QueueingConsumer.Delivery loDelivery = loConsumer.nextDelivery();
                final String lsMessage = new String(loDelivery.getBody());
                System.out.println("Thread #" + Thread.currentThread().getId() + " - message '" + lsMessage + "' received from queue");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    //endregion
}
