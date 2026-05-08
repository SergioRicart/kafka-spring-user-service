package com.sergioricart.user_service.user.infrastructure.api.dto.request;

import com.sergioricart.user_service.user.domain.constant.UserConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedRequest {

    @NotBlank(message = UserConstants.FIRST_NAME_NOT_BLANK)
    String firstName;

    String lastName;

    @NotBlank(message = UserConstants.EMAIL_NOT_BLANK)
    String email;

    @NotBlank(message = UserConstants.PASSWORD_NOT_BLANK)
    String password;

}
