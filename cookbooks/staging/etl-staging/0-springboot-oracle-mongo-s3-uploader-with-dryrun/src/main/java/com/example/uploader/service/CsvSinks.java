
package com.example.uploader.service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/**
 * CSV gzip sink that conforms to the JsonSinks.JsonSink interface so it can be swapped via config.
 * Writes headers either from a fixed order (app.output.csv-columns) or inferred from first record.
 */
public class CsvSinks {

    public static class CsvGzipSink implements JsonSinks.JsonSink {
        private final File file;
        private final OutputStream out;
        private final Writer writer;
        private boolean headerWritten = false;
        private List<String> headers = new ArrayList<>();
        private final String csvColumns;

        public CsvGzipSink(File file, String csvColumns) throws IOException {
            this.file = file;
            this.out = new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            this.writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
            this.csvColumns = csvColumns;
        }

        @Override
        public void write(Map<String, Object> item) throws IOException {
            if (!headerWritten) {
                if (csvColumns != null && !csvColumns.trim().isEmpty()) {
                    headers = new ArrayList<>();
                    for (String h : csvColumns.split(",")) {
                        headers.add(h.trim());
                    }
                } else {
                    headers = new ArrayList<>(item.keySet());
                    Collections.sort(headers);
                }
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
            if (s == null) return "";
            boolean needQuote = s.contains(",") || s.contains(""") || s.contains("\n") || s.contains("\r");
            String val = s.replace(""", """");
            return needQuote ? """ + val + """ : val;
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
