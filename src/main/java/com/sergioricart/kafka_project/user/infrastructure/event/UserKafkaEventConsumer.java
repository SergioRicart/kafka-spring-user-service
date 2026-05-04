package com.sergioricart.kafka_project.user.infrastructure.event;

import com.sergioricart.kafka_project.common.infrastructure.event.consumer.EventSpecificConsummer;
import com.sergioricart.kafka_project.common.infrastructure.event.util.MessagingUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserKafkaEventConsumer implements Consumer<Message<GenericRecord>> {

    private MessagingUtil messagingUtil;

    private Map<String, EventSpecificConsummer<SpecificRecord>> eventSpecificConsummerMap;

    public UserKafkaEventConsumer(List<EventSpecificConsummer> specificConsummers, MessagingUtil messagingUtil){

        eventSpecificConsummerMap = specificConsummers.stream().collect(
                Collectors.toMap(
                        EventSpecificConsummer::getSchema,
                        consumer -> consumer
                )
        );

        this.messagingUtil = messagingUtil;

    };

    @SneakyThrows
    @KafkaListener(topics = "${app.kafka.topics.user}", groupId = "${app.kafka.group-id}", containerFactory = "kafkaListenerContainerFactory")
    @Override
    public void accept(Message<GenericRecord> genericRecordMessage) {

        log.info("Received Message: {}", genericRecordMessage.toString());

        SpecificRecord specificRecord = messagingUtil.getSpecificRecord(genericRecordMessage.getPayload());

        String schemaFullName = specificRecord.getClass().getTypeName();

        EventSpecificConsummer<SpecificRecord> specificConsummer = eventSpecificConsummerMap.get(schemaFullName);

        if (specificConsummer != null) {

            Message<SpecificRecord> specificRecordMessage = messagingUtil.buildMessage(
                    (SpecificRecord) genericRecordMessage.getPayload(),
                    genericRecordMessage.getHeaders()
            );

            log.info("Sending specific record: {}", specificRecordMessage.toString());

            specificConsummer.accept(specificRecordMessage);

        }else  {
            log.warn("No consumer for schema {}", schemaFullName);
        }

    }

}
