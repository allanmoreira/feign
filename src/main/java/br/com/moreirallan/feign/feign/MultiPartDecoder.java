package br.com.moreirallan.feign.feign;

import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

public class MultiPartDecoder implements Decoder {

    protected SpringDecoder springDecoder;

    public MultiPartDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        this.springDecoder = new SpringDecoder(messageConverters);
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        if (type instanceof Class && MultipartFile.class.isAssignableFrom((Class) type)) {
            Collection<String> contentTypes = response.headers().get("content-type");
            String contentType = "application/octet-stream";
            if (contentTypes.size() > 0) {
                String[] temp = new String[contentTypes.size()];
                contentTypes.toArray(temp);
                contentType = temp[0];
            }

            String fileName = "";

            Collection<String> contentDispositions = response.headers().get("content-disposition");
            if (contentDispositions != null && contentDispositions.size() > 0) {
                String[] temp = new String[contentDispositions.size()];
                contentDispositions.toArray(temp);
                String disposition = temp[0];
                fileName = disposition.replaceFirst("(?i)^.*filename=\"?([^\"]+)\"?.*$", "$1");
            }
            if (response.body() != null) {
                byte[] bytes = StreamUtils.copyToByteArray(response.body().asInputStream());
                MultipartFileImpl multipartFile = new MultipartFileImpl("file", fileName, contentType, bytes);
                return multipartFile;
            }
        }
        return springDecoder.decode(response, type);
    }
}
