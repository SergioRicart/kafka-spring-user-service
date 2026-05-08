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
package com.sergioricart.user_service.user.application.kafka.updated;

import com.sergioricart.user_service.common.application.Command;
import com.sergioricart.user_service.common.application.VoidResponse;
import com.sergioricart.user_service.user.domain.entiry.Role;
import lombok.Data;

import java.time.Instant;

@Data
public class UserUpdatedCommand implements Command<VoidResponse> {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private Instant timestamp;

}
*/
