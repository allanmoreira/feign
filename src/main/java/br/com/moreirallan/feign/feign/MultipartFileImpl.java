package br.com.moreirallan.feign.feign;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class MultipartFileImpl implements MultipartFile {

    protected final String name;
    protected final String originalFileName;
    protected final String contentType;
    protected final byte[] payload;

    public MultipartFileImpl(File file) throws IOException {
        this.originalFileName = file.getName();
        this.payload = FileCopyUtils.copyToByteArray(file);
        this.name = "file";
        this.contentType = "application/octet-stream";
    }

    public MultipartFileImpl(String originalFileName, byte[] payload) {
        this.originalFileName = originalFileName;
        this.payload = payload;
        this.name = "file";
        this.contentType = "application/octet-stream";
    }

    public MultipartFileImpl(String name, String originalFileName, String contentType, byte[] payload) {
        if (payload == null) {
            throw new IllegalArgumentException("Payload cannot be null.");
        }
        this.name = name;
        this.originalFileName = originalFileName;
        this.contentType = contentType;
        this.payload = payload;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOriginalFilename() {
        return originalFileName;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean isEmpty() {
        return payload.length == 0;
    }

    @Override
    public long getSize() {
        return payload.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return payload;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(payload);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        new FileOutputStream(dest).write(payload);
    }
}
