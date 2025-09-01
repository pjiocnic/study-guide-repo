package com.example.uploader.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/**
 * Simple streaming writers for JSONL and JSON Array formats, gzip-compressed.
 */
public class JsonSinks {

    public interface JsonSink extends Closeable {
        void write(Map<String, Object> item) throws IOException;
        File getFile();
    }

    public static class JsonlGzipSink implements JsonSink {
        private final ObjectMapper mapper = new ObjectMapper();
        private final File file;
        private final OutputStream out;
        private final BufferedWriter writer;

        public JsonlGzipSink(File file) throws IOException {
            this.file = file;
            this.out = new GZIPOutputStream(new FileOutputStream(file));
            this.writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
        }

        @Override
        public void write(Map<String, Object> item) throws IOException {
            writer.write(mapper.writeValueAsString(item));
            writer.newLine();
        }

        public File getFile() { return file; }

        @Override
        public void close() throws IOException {
            writer.flush();
            writer.close();
            out.close();
        }
    }

    public static class JsonArrayGzipSink implements JsonSink {
        private final ObjectMapper mapper = new ObjectMapper();
        private final File file;
        private final OutputStream out;
        private final JsonGenerator gen;
        private boolean startedArray = false;

        public JsonArrayGzipSink(File file) throws IOException {
            this.file = file;
            this.out = new GZIPOutputStream(new FileOutputStream(file));
            this.gen = mapper.getFactory().createGenerator(out);
            this.gen.writeStartArray();
            startedArray = true;
        }

        @Override
        public void write(Map<String, Object> item) throws IOException {
            gen.writeObject(item);
        }

        public File getFile() { return file; }

        @Override
        public void close() throws IOException {
            if (startedArray) {
                gen.writeEndArray();
            }
            gen.flush();
            gen.close();
            out.close();
        }
    }
}