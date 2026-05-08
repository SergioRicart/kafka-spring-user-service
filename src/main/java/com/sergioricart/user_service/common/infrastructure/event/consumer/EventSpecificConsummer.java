package com.sergioricart.user_service.common.infrastructure.event.consumer;

import org.apache.avro.specific.SpecificRecord;
import org.springframework.messaging.Message;

public interface EventSpecificConsummer<T extends SpecificRecord> {

    void accept(Message<T> genericRecordMessage);

    String getSchema();

}
