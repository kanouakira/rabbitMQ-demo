package com.kanouakira.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class SpringFanoutListener2 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("Fanout Listener Two: "+ new String(message.getBody()));
    }
}
