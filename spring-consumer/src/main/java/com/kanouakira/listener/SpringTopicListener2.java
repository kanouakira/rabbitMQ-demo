package com.kanouakira.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class SpringTopicListener2 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("Topic Listener Two(#.kanouakira) >>> "+ new String(message.getBody()));
    }
}
