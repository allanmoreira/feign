package br.com.moreirallan.feign.feign;

import br.com.moreirallan.feign.domain.error.RestException;
import br.com.moreirallan.feign.log.FeignLogger;
import br.com.moreirallan.feign.properties.service.ServiceItem;
import feign.Feign;
import feign.Logger;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.querymap.FieldQueryMapEncoder;

import java.util.ArrayList;
import java.util.List;

public class FeignClientFactory {

    protected Feign.Builder builder;
    protected List<RequestInterceptor> interceptors;
    protected ServiceItem configuration;

    public FeignClientFactory(ServiceItem serviceItem) {
        try {
            if (serviceItem == null) {
                throw new RestException("");
            }
            this.configuration = serviceItem;
            this.interceptors = new ArrayList<>();

            builder = new Feign.Builder();
            builder.queryMapEncoder(new FieldQueryMapEncoder());
            builder.logLevel(Logger.Level.FULL);
            setEncode();
        } catch (RestException ex) {
            throw ex;
        } catch (RuntimeException ex) {
            throw new RestException("")
                    .cause(ex);
        }
    }

    private void setEncode(){
        builder.encoder(configuration.getFeign().getEncoder());
        builder.decoder(configuration.getFeign().getDecoder());
        builder.errorDecoder(configuration.getFeign().getErrorDecoder());

        builder.queryMapEncoder(new FieldQueryMapEncoder());
        builder.retryer(Retryer.NEVER_RETRY);
    }

    public <T> T create(Class<T> restInterface) {
        builder.logger(new FeignLogger(restInterface));
        return builder.target(restInterface, configuration.getUrl());
    }

    public FeignClientFactory decoder(Decoder decoder) {
        builder.decoder(decoder);
        return this;
    }

    public FeignClientFactory encoder(Encoder encoder) {
        builder.encoder(encoder);
        return this;
    }

    public FeignClientFactory errorDecoder(ErrorDecoder errorDecoder) {
        builder.errorDecoder(errorDecoder);
        return this;
    }
}
