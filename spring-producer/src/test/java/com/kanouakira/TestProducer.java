package com.kanouakira;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq-config.xml")
public class TestProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSpringQueue(){
        rabbitTemplate.convertAndSend("spring_queue","hello world spring...");
    }

    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("spring_fanout_exchange","","hello world fanout...");
    }

    @Test
    public void testTopics(){
        rabbitTemplate.convertAndSend("spring_topic_exchange","kanouakira.xxx","topic:kanouakira.xxx");
        rabbitTemplate.convertAndSend("spring_topic_exchange","xxx.xxx.kanouakira","topic:xxx.xxx.kanouakira");
        rabbitTemplate.convertAndSend("spring_topic_exchange","xxx.kanouakira","topic:xxx.kanouakira");
    }


}
