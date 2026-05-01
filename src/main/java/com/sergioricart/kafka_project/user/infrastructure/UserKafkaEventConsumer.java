package com.sergioricart.kafka_project.user.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
public class UserKafkaEventConsumer implements Consumer<Message<GenericRecord>> {

    @KafkaListener(topics = "${app.kafka.topics.user}", groupId = "${app.kafka.group-id}", containerFactory = "kafkaListenerContainerFactory")
    @Override
    public void accept(Message<GenericRecord> genericRecordMessage) {

        log.info("Received Message: {}", genericRecordMessage.toString());


    }

}
