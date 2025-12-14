package br.com.moreirallan.feign.domain.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusResponse {

    protected String description;
    protected ExceptionResponse exception;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExceptionResponse getException() {
        return exception;
    }

    public void setException(ExceptionResponse exception) {
        this.exception = exception;
    }
}
