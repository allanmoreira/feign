package br.com.moreirallan.feign.properties.service;

import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;

public class FeignConfig {

    protected Decoder decoder;
    protected Encoder encoder;
    protected ErrorDecoder errorDecoder;

    public FeignConfig(Encoder encoder, Decoder decoder, ErrorDecoder errorDecoder) {
        this.decoder = decoder;
        this.encoder = encoder;
        this.errorDecoder = errorDecoder;
    }

    public Decoder getDecoder() {
        return decoder;
    }

    public void setDecoder(Decoder decoder) {
        this.decoder = decoder;
    }

    public Encoder getEncoder() {
        return encoder;
    }

    public void setEncoder(Encoder encoder) {
        this.encoder = encoder;
    }

    public ErrorDecoder getErrorDecoder() {
        return errorDecoder;
    }

    public void setErrorDecoder(ErrorDecoder errorDecoder) {
        this.errorDecoder = errorDecoder;
    }
}
