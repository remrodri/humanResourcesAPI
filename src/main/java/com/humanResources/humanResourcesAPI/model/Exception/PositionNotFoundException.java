package com.humanResources.humanResourcesAPI.model.Exception;

public class PositionNotFoundException extends RuntimeException {
    public PositionNotFoundException(String message) {
        super(message);
    }
    public PositionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public PositionNotFoundException(Throwable cause) {
        super(cause);
    }
}
