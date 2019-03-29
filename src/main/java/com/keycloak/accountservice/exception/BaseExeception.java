package com.keycloak.accountservice.exception;

public class BaseExeception  extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
