package com.kanouakira;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerPubSub {
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
        // exchangeDeclare(String exchange, BuiltinExchangeType type, boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments)
        channel.exchangeDeclare("exchange_fanout", BuiltinExchangeType.FANOUT, true, false, false, null);
        channel.queueDeclare("fanout_queue_one", true, false, false, null);
        channel.queueDeclare("fanout_queue_two", true, false, false, null);
        channel.queueBind("fanout_queue_one","exchange_fanout","");
        channel.queueBind("fanout_queue_two","exchange_fanout","");
        channel.basicPublish("exchange_fanout", "", null, "测试信息".getBytes());
        channel.close();
        connection.close();
    }
}
