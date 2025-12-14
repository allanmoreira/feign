package br.com.moreirallan.feign.domain;

public class ResponseDTO<T> {

    private T data;
    private String message;
    private Integer status;

    public ResponseDTO() {
        this.status = 200;
    }

    public ResponseDTO(T data) {
        this.data = data;
        this.status = 200;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
