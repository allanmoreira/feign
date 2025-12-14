package br.com.moreirallan.feign.domain.error;

import br.com.moreirallan.feign.properties.service.ServiceItem;
import br.com.moreirallan.feign.utils.FeignUtils;
import feign.Response;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class RestException extends RuntimeException {

    protected transient ServiceItem serviceItem;
    protected String message;
    protected String methodKey;
    protected String method;
    protected String url;
    protected int status;
    protected Map<String, Collection<String>> requestHeaders;
    protected Map<String, Collection<String>> responseHeaders;
    protected String responseBody;
    protected Throwable cause;
    protected String reason;
    protected Object payload;

    public RestException(ServiceItem serviceItem) {
        this.serviceItem = serviceItem;
    }

    public RestException(ServiceItem serviceItem, String message, Response response, String methodKey, Object payload) {
        super(message);
        this.methodKey = methodKey;
        if (response != null) {
            this.status = response.status();
            if (!StringUtils.isEmpty(response.reason())) {
                this.reason = response.reason();
            }
            this.responseHeaders = response.headers();
            if (response.request() != null) {
                this.method = String.valueOf(response.request().httpMethod());
                this.url = response.request().url();
                this.requestHeaders = response.request().headers();
            }
            this.payload = payload;
            FeignUtils.close(response);
        }
    }

    public RestException(ServiceItem serviceItem, String message, Response response, String methodKey, boolean includePayload) {
        super(message);
        this.methodKey = methodKey;
        if (response != null) {
            this.status = response.status();
            if (!StringUtils.isEmpty(response.reason())) {
                this.reason = response.reason();
            }
            this.responseHeaders = response.headers();
            if (response.request() != null) {
                this.method = String.valueOf(response.request().httpMethod());
                this.url = response.request().url();
                this.requestHeaders = response.request().headers();
            }
            if (response.body() != null && includePayload) {
                try {
                    this.responseBody = IOUtils.toString(response.body().asReader());
                } catch (IOException ex) {
                    // ignorar
                }
            }
            FeignUtils.close(response);
        }
    }

    public RestException(String message) {
        super(message);
    }

    public ServiceItem getServiceItem() {
        return serviceItem;
    }

    public void setServiceItem(ServiceItem serviceItem) {
        this.serviceItem = serviceItem;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RestException cause(Throwable cause) {
        return this;
    }

    public Throwable getCause() {
        return cause;
    }
}
