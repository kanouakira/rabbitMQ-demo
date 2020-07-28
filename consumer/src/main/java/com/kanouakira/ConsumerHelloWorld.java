package com.kanouakira;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerHelloWorld {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //2.设置工厂参数
        factory.setHost("49.234.221.187"); // ip默认localhost
        factory.setPort(5672); // 端口默认5672
        factory.setVirtualHost("/kanouakira"); // 虚拟机默认/
        factory.setUsername("kanouakira"); // 用户默认guest
        factory.setPassword("123456"); // 密码默认guest
        //3.创建Connection
        Connection connection = factory.newConnection();
        //4.创建chanel
        Channel channel = connection.createChannel();
        //5.创建队列
        /*
        queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
        queue:队列名称
        durable:是否持久化,重启后依然存在
        exclusive:是否独占,只有一个消费者
        autoDelete:没有消费者自动删除
        */
        channel.queueDeclare("hello_world", true, false, false, null);
        //6.接收消息
        /*
        basicConsume(String queue, boolean autoAck, Consumer callback)
        queue:队列名称
        autoAck:是否自动确认
        callback:回调对象
        */
        DefaultConsumer consumer = new DefaultConsumer(channel){
            // 收到消息后执行该方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumerTag:" + consumerTag);
                System.out.println("Exchange:" + envelope.getExchange());
                System.out.println("RoutingKey:" + envelope.getRoutingKey());
                System.out.println("properties:" + properties);
                System.out.println("body:" + new String(body));
            }
        };
        channel.basicConsume("hello_world", true, consumer);
    }
}
