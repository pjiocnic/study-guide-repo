package com.example.producer.util;

import java.io.*;

public class ByteCountingReader implements Closeable {
  private final RandomAccessFile raf;

  public ByteCountingReader(File file, java.nio.charset.Charset charset, long startByteOffset) throws IOException {
    this.raf = new RandomAccessFile(file, "r");
    if (startByteOffset > 0) raf.seek(startByteOffset);
  }

  public String readLine() throws IOException {
    return raf.readLine();
  }

  public long getByteOffset() throws IOException {
    return raf.getFilePointer();
  }

  @Override public void close() throws IOException { raf.close(); }
}
