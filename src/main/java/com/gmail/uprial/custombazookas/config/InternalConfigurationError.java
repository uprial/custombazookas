package com.gmail.uprial.custombazookas.config;

@SuppressWarnings("ExceptionClassNameDoesntEndWithException")
class InternalConfigurationError extends RuntimeException {
    InternalConfigurationError(String message) {
        super(message);
    }
}
