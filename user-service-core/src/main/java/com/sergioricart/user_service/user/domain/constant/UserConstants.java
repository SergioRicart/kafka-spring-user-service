package com.sergioricart.user_service.user.domain.constant;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserConstants {

    // ERROR MESSAGES
    public static final String USER_NOT_FOUND_MESSAGE = "User not found";

    // REQUEST VALIDATION MESSAGE
    public static final String FIRST_NAME_NOT_BLANK = "First name cannot be blank";
    public static final String EMAIL_NOT_BLANK = "Email cannot be blank";
    public static final String PASSWORD_NOT_BLANK = "Password cannot be blank";
    public static final String ROLE_ID_NOT_BLANK = "Role id cannot be blank";


    public static  final String USER_TOPIC = "${app.kafka.topics.user}";

}
