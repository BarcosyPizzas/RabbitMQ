package com.icc.distribuido;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

// Sender class to send messages to the queue
public class Sender {
    String QUEUE_NAME;
    ConnectionFactory connectionFactory;

    public Sender(String queue){
        QUEUE_NAME=queue;
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
    }

    public void sendMessage(String message) throws Exception{
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
    }

    public void publishMessage(String message) throws Exception{
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(QUEUE_NAME, "fanout");
        channel.basicPublish(QUEUE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
    }
}