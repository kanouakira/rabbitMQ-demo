package com.kanouakira.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class SpringTopicListener1 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("Topic Listener One(kanouakira.*) >>> "+ new String(message.getBody()));
    }
}
