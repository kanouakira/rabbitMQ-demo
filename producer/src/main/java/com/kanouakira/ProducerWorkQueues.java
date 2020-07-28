package com.kanouakira;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerWorkQueues {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
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
        channel.queueDeclare("work_queues", true, false, false, null);
        //6.发送消息
        /*
        basicPublish(String exchange, String routingKey, AMQP.BasicProperties props, byte[] body)
        exchange:交换机名称 默认”“
        routingKey:路由名称
        props:参数
        body:信息
        */
        for (int i = 0; i < 200000; i++) {
            String msg = "test rabbitMQ - "+i;
            channel.basicPublish("", "work_queues", null, msg.getBytes());
        }

        channel.close();
        connection.close();
    }
}
