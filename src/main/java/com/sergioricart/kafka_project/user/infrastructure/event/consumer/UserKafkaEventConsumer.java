/**
 * Router de eventos Kafka entrantes.
 * Escucha el topic configurado en "app.kafka.topics.user" y despacha
 * cada evento al SpecificConsumer correspondiente según su schema Avro.
 *
 * IMPORTANTE: Este consumer solo tiene sentido si un servicio EXTERNO
 * publica en ese topic. Este servicio no debe escuchar su propio topic
 * de salida. Si se necesita reaccionar a eventos de otro servicio
 * (ej: order-service, auth-service), cambiar el topic al de salida
 * de ese servicio (ej: "order.out", "auth.out").
 */














/*
package com.sergioricart.kafka_project.user.infrastructure.event.consumer;

import com.sergioricart.kafka_project.common.infrastructure.event.consumer.EventSpecificConsummer;
import com.sergioricart.kafka_project.common.infrastructure.event.util.MessagingUtil;
import com.sergioricart.kafka_project.user.domain.constant.UserConstants;
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


// ESTE CONSUMER NO TIENE SENTIDO PORQUE ESTA ESCUCHANDO SU PROPIO TOPIC DE ENTRADA CUANDO LO QUE HAY QUE HACER ES ESCUCHAR LOS TOPICS
// ESTE CONSUMER NO TIENE SENTIDO PORQUE ESTA ESCUCHANDO SU PROPIO TOPIC DE ENTRADA CUANDO LO QUE HAY QUE HACER ES ESCUCHAR LOS TOPICS
// ESTE CONSUMER NO TIENE SENTIDO PORQUE ESTA ESCUCHANDO SU PROPIO TOPIC DE ENTRADA CUANDO LO QUE HAY QUE HACER ES ESCUCHAR LOS TOPICS
// ESTE CONSUMER NO TIENE SENTIDO PORQUE ESTA ESCUCHANDO SU PROPIO TOPIC DE ENTRADA CUANDO LO QUE HAY QUE HACER ES ESCUCHAR LOS TOPICS
// ESTE CONSUMER NO TIENE SENTIDO PORQUE ESTA ESCUCHANDO SU PROPIO TOPIC DE ENTRADA CUANDO LO QUE HAY QUE HACER ES ESCUCHAR LOS TOPICS
@Component
@Slf4j
public class UserKafkaEventConsumer implements Consumer<Message<GenericRecord>> {

    private final MessagingUtil messagingUtil;

    private final Map<String, EventSpecificConsummer<SpecificRecord>> eventSpecificConsummerMap;

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
    @KafkaListener(topics = UserConstants.USER_TOPIC_IN, groupId = "${app.kafka.group-id}", containerFactory = "kafkaListenerContainerFactory")
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
*/
