package com.sergioricart.kafka_project.common.infrastructure.event.util;

import org.mapstruct.Mapper;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper
public interface MapperUtils {

    default Instant epochMilliToInstant(Long millis){

        return millis != null ? Instant.ofEpochMilli(millis) : null;
    };

    default String charSquareToString(CharSequence charSequence){
        return  charSequence != null ? String.valueOf(charSequence) : "";
    }

    default CharSequence stringToCharSquare(String charSequence){
        return charSequence;
    }

    default OffsetDateTime fromEpochMilli(Long epochMillis) {
      return epochMillis != null ? Instant.ofEpochMilli(epochMillis).atOffset(ZoneOffset.UTC) : null;
    };

}
