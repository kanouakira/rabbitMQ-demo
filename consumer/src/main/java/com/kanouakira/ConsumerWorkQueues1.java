package com.kanouakira;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerWorkQueues1 {
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
        channel.queueDeclare("work_queues", true, false, false, null);
        //6.接收消息
        DefaultConsumer consumer = new DefaultConsumer(channel){
            // 收到消息后执行该方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body:" + new String(body));
            }
        };
        channel.basicConsume("work_queues", true, consumer);
    }
}
