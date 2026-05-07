/**
 * Command que transporta los datos de un evento Kafka hacia el Handler.
 * Solo tiene sentido si un servicio externo publica eventos de este
 * dominio en el topic de entrada. Si este servicio es el único dueño
 * del dominio de usuarios, este Command no tiene utilidad real.
 *
 * En una arquitectura correcta, los Commands en application/kafka/
 * deberían pertenecer a dominios externos (ej: OrderCreatedCommand,
 * PaymentCompletedCommand) a los que este servicio necesite reaccionar.
 */




/*
package com.sergioricart.kafka_project.user.application.kafka.deleted;

import com.sergioricart.kafka_project.common.application.Command;
import com.sergioricart.kafka_project.common.application.VoidResponse;
import com.sergioricart.kafka_project.user.domain.entiry.Role;
import lombok.Data;

import java.time.Instant;

@Data
public class UserDeletedCommand implements Command<VoidResponse> {

    private String id;
    private Instant timestamp;

}
*/
