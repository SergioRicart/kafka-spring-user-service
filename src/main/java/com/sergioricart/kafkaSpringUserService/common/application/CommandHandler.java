package com.sergioricart.kafkaSpringUserService.common.application;

public interface CommandHandler<T extends Command<R>, R> {

    R handle(T command);

    Class<T> getCommandType();
}
