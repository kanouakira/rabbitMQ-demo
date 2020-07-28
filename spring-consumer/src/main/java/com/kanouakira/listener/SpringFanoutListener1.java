package com.kanouakira.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class SpringFanoutListener1 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("Fanout Listener One: "+ new String(message.getBody()));
    }
}
