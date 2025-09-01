package com.example.uploader.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.GZIPOutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

/**
 * Output sinks:
 *  - JsonlGzipSink: .jsonl.gz
 *  - JsonArrayGzipSink: .json.gz (array)
 *  - ParquetSink: .parquet (snappy)
 *  - CsvSink: .csv (text/csv, uncompressed)
 */
public class JsonSinks {

    public interface Sink extends Closeable {
        void write(Map<String, Object> item) throws IOException;
        File getFile();
        String getContentType(); // For S3 metadata
        String getContentEncoding(); // e.g. "gzip" or null
        String getExt(); // file extension without dot
    }

    // ---------- JSONL GZIP ----------
    public static class JsonlGzipSink implements Sink {
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
        public String getContentType() { return "application/json"; }
        public String getContentEncoding() { return "gzip"; }
        public String getExt() { return "jsonl.gz"; }

        @Override
        public void close() throws IOException {
            writer.flush();
            writer.close();
            out.close();
        }
    }

    // ---------- JSON ARRAY GZIP ----------
    public static class JsonArrayGzipSink implements Sink {
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
        public String getContentType() { return "application/json"; }
        public String getContentEncoding() { return "gzip"; }
        public String getExt() { return "json.gz"; }

        @Override
        public void close() throws IOException {
            if (startedArray) gen.writeEndArray();
            gen.flush();
            gen.close();
            out.close();
        }
    }

    // ---------- PARQUET (SNAPPY) ----------
    public static class ParquetSink implements Sink {
        private final File file;
        private ParquetWriter<GenericRecord> writer;
        private Schema schema;
        private final AtomicBoolean initialized = new AtomicBoolean(false);

        public ParquetSink(File file) {
            this.file = file;
        }

        private static boolean isBoolean(Object v) { return v instanceof Boolean; }
        private static boolean isLong(Object v) { return v instanceof Long || v instanceof Integer; }
        private static boolean isDouble(Object v) { return v instanceof Double || v instanceof Float; }

        private Schema inferSchema(Map<String, Object> item) {
            SchemaBuilder.RecordBuilder<Schema> record = SchemaBuilder.record("row");
            SchemaBuilder.FieldAssembler<Schema> fields = record.fields();
            for (Map.Entry<String,Object> e : item.entrySet()) {
                String name = e.getKey();
                Object v = e.getValue();
                Schema fieldSchema;
                if (isBoolean(v)) {
                    fieldSchema = SchemaBuilder.builder().unionOf().nullType().and().booleanType().endUnion();
                } else if (isLong(v)) {
                    fieldSchema = SchemaBuilder.builder().unionOf().nullType().and().longType().endUnion();
                } else if (isDouble(v)) {
                    fieldSchema = SchemaBuilder.builder().unionOf().nullType().and().doubleType().endUnion();
                } else {
                    fieldSchema = SchemaBuilder.builder().unionOf().nullType().and().stringType().endUnion();
                }
                fields = fields.name(name).type(fieldSchema).noDefault();
            }
            return fields.endRecord();
        }

        private GenericRecord toRecord(Map<String, Object> item) {
            GenericRecord rec = new GenericData.Record(schema);
            for (Schema.Field f : schema.getFields()) {
                Object v = item.get(f.name());
                if (v == null) {
                    rec.put(f.name(), null);
                } else {
                    Schema.Type nonNullType = f.schema().getTypes().size() == 2
                            ? f.schema().getTypes().get(1).getType()
                            : f.schema().getType();
                    switch (nonNullType) {
                        case BOOLEAN -> rec.put(f.name(), (v instanceof Boolean) ? v : null);
                        case LONG -> rec.put(f.name(), (v instanceof Integer) ? Long.valueOf((Integer)v) :
                                                     (v instanceof Long) ? v : null);
                        case DOUBLE -> rec.put(f.name(), (v instanceof Number) ? ((Number)v).doubleValue() : null);
                        case STRING -> rec.put(f.name(), v.toString());
                        default -> rec.put(f.name(), v.toString());
                    }
                }
            }
            return rec;
        }

        @Override
        public synchronized void write(Map<String, Object> item) throws IOException {
            if (!initialized.get()) {
                schema = inferSchema(item);
                Configuration conf = new Configuration();
                writer = AvroParquetWriter.<GenericRecord>builder(new Path(file.getAbsolutePath()))
                        .withSchema(schema)
                        .withCompressionCodec(CompressionCodecName.SNAPPY)
                        .withConf(conf)
                        .build();
                initialized.set(true);
            }
            writer.write(toRecord(item));
        }

        public File getFile() { return file; }
        public String getContentType() { return "application/octet-stream"; }
        public String getContentEncoding() { return null; }
        public String getExt() { return "parquet"; }

        @Override
        public void close() throws IOException {
            if (writer != null) writer.close();
        }
    }

    // ---------- CSV (plain text/csv) ----------
    public static class CsvSink implements Sink {
        private final File file;
        private final BufferedWriter writer;
        private List<String> headerOrder = new ArrayList<>();
        private boolean headerWritten = false;

        public CsvSink(File file) throws IOException {
            this.file = file;
            this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
        }

        private static String esc(Object v) {
            if (v == null) return "";
            String s = String.valueOf(v);
            boolean needQuotes = s.contains(",") || s.contains(""") || s.contains("\n") || s.contains("\r");
            s = s.replace(""", """");
            return needQuotes ? """ + s + """ : s;
        }

        @Override
        public synchronized void write(Map<String, Object> item) throws IOException {
            if (!headerWritten) {
                headerOrder = new ArrayList<>(item.keySet());
                writer.write(String.join(",", headerOrder));
                writer.newLine();
                headerWritten = true;
            }
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < headerOrder.size(); i++) {
                if (i > 0) line.append(",");
                Object v = item.get(headerOrder.get(i));
                line.append(esc(v));
            }
            writer.write(line.toString());
            writer.newLine();
        }

        public File getFile() { return file; }
        public String getContentType() { return "text/csv"; }
        public String getContentEncoding() { return null; }
        public String getExt() { return "csv"; }

        @Override
        public void close() throws IOException {
            writer.flush();
            writer.close();
        }
    }
}