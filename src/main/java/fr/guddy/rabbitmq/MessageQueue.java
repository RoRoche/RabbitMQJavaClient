package fr.guddy.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MessageQueue {

    //region Fields
    private final Connection mConnection;
    private final String mQueueName;
    private final ThreadLocal<Channel> mChannels = new ThreadLocal<>();
    //endregion

    //region Constructor
    public MessageQueue(final String psHost, final String psQueueName) throws IOException, TimeoutException {
        mQueueName = psQueueName;

        // create a connection factory
        final ConnectionFactory loConnectionFactory = new ConnectionFactory();

        loConnectionFactory.setHost(psHost);
        // get a new connection
        mConnection = loConnectionFactory.newConnection();
    }
    //endregion

    //region Visible API
    public final Channel getChannel() throws IOException {
        Channel loChannel = mChannels.get();
        if (loChannel == null) {
            loChannel = mConnection.createChannel();
            mChannels.set(loChannel);
        }
        return loChannel;
    }

    public String getQueueName() {
        return mQueueName;
    }

    public void close() throws IOException {
        mConnection.close();
    }
    //endregion
}
