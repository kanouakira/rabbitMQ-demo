package com.kanouakira;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerRouting {
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

        String exchangeName = "exchange_direct";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT, true, false, false, null);

        String queueName1 = "direct_queue_one";
        String queueName2 = "direct_queue_two";

        channel.queueDeclare(queueName1, true, false, false, null);
        channel.queueDeclare(queueName2, true, false, false, null);
        // 队列1绑定
        channel.queueBind(queueName1,exchangeName,"error");
        // 队列2绑定
        channel.queueBind(queueName2,exchangeName,"error");
        channel.queueBind(queueName2,exchangeName,"info");
        channel.queueBind(queueName2,exchangeName,"warning");
//        channel.basicPublish(exchangeName, "info", null, "Info:测试信息".getBytes());
        channel.basicPublish(exchangeName, "error", null, "Error:测试信息".getBytes());
        channel.close();
        connection.close();
    }
}
