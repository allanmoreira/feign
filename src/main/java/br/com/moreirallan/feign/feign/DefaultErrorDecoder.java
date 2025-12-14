/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moreirallan.feign.feign;

import br.com.moreirallan.feign.domain.error.RestException;
import br.com.moreirallan.feign.properties.service.ServiceItem;
import br.com.moreirallan.feign.utils.FeignUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * @author jc62053
 */
public class DefaultErrorDecoder implements ErrorDecoder {

    protected ServiceItem serviceItem;
    protected Class<?> errorClass;
    protected ObjectMapper objectMapper;
    protected List<Integer> unavailableErrors = Arrays.asList(502, 503, 504);

    public DefaultErrorDecoder() {}

    public DefaultErrorDecoder(ServiceItem serviceItem) {
        this.serviceItem = serviceItem;
    }

    public DefaultErrorDecoder(ServiceItem serviceItem, Class<?> errorClass, ObjectMapper objectMapper) {
        this(serviceItem);
        this.errorClass = errorClass;
        this.objectMapper = objectMapper;
    }

    public DefaultErrorDecoder unavailableErrors(Integer... unavailableErrors) {
        this.unavailableErrors = Arrays.asList(unavailableErrors);
        return this;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            String error = FeignUtils.serializeBaseResponse(response);
            if (errorClass != null) {
                try {
                    Object payload = objectMapper.readValue(response.body().asReader(Charset.defaultCharset()), errorClass);
                    return new RestException(serviceItem, error, response, methodKey, payload);
                } catch (Exception ex) {
                    // ignorar
                }
            }
            if (unavailableErrors != null && unavailableErrors.contains(response.status())) {
                return new RestException(serviceItem, error, response, methodKey, true);
            }
            return new RestException(serviceItem, error, response, methodKey, true);
        } finally {
            FeignUtils.close(response);
        }
    }

}
