package com.kafka.kafkaDemo.kafka;

import com.kafka.kafkaDemo.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {

    private static final Logger LOGGER= LoggerFactory.getLogger(JsonKafkaProducer.class);

    private KafkaTemplate<String, User> kafkaTemplate;

    @Value("${spring.kafka.topic-json.name}")
    private String jsonTopicName;

    public JsonKafkaProducer(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }

    public void sendMessage(User data){
        LOGGER.info(String.format("Message sent ->%s",data.toString()));
        Message<User> message= MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC,jsonTopicName).build();
        kafkaTemplate.send(message);
    }
}
