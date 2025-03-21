package com.example.intia_assurance_test.config.domain;

import java.io.Serializable;

public class Response implements Serializable {

    private static final long serialVersionUID = 4359290907002728689L;

    private String message;

    public Response(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
