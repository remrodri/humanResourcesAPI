package com.humanResources.humanResourcesAPI.model.Exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(String message) {
        super(message);
    }
    public DepartmentNotFoundException(String message, Throwable cause) {super(message, cause);}
    public DepartmentNotFoundException(Throwable cause) {super(cause);}
}
