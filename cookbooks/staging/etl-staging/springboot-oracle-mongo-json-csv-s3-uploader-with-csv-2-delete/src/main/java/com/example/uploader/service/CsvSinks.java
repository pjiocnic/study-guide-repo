
package com.example.uploader.service;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/**
 * CSV gzip sink that conforms to the same JsonSink interface so it can be swapped via config.
 * It writes headers inferred from the first record's keys (sorted alphabetically for stability).
 */
public class CsvSinks {

    public static class CsvGzipSink implements JsonSinks.JsonSink {
        private final File file;
        private final OutputStream out;
        private final Writer writer;
        private boolean headerWritten = false;
        private List<String> headers = new ArrayList<>();

        public CsvGzipSink(File file) throws IOException {
            this.file = file;
            this.out = new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            this.writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
        }

        @Override
        public void write(Map<String, Object> item) throws IOException {
            if (!headerWritten) {
                headers = new ArrayList<>(item.keySet());
                Collections.sort(headers);
                // Write header
                writeLine(headers);
                headerWritten = true;
            }
            // Write row in header order
            String[] row = new String[headers.size()];
            for (int i = 0; i < headers.size(); i++) {
                Object v = item.get(headers.get(i));
                row[i] = v == null ? "" : String.valueOf(v);
            }
            writeLine(row);
        }

        private void writeLine(List<String> fields) throws IOException {
            writeLine(fields.toArray(new String[0]));
        }

        private void writeLine(String[] fields) throws IOException {
            for (int i = 0; i < fields.length; i++) {
                if (i > 0) writer.write(',');
                writer.write(escape(fields[i]));
            }
            writer.write('\n');
        }

        private String escape(String s) {
            boolean needQuote = s.contains(",") || s.contains("\"") || s.contains("\n") || s.contains("\r");
            String val = s.replace("\"", "\"\"");
            return needQuote ? "\"" + val + "\"" : val;
        }

        public File getFile() { return file; }

        @Override
        public void close() throws IOException {
            writer.flush();
            writer.close();
            out.close();
        }
    }
}
