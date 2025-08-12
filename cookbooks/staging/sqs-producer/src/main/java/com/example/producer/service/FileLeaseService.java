package com.example.producer.service;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.*;

@Service
public class FileLeaseService {

  public FileLock tryAcquireLock(Path file) throws IOException {
    Path lockPath = Path.of(file.toString() + ".lock");
    FileChannel channel = FileChannel.open(lockPath,
        StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    FileLock lock = channel.tryLock();
    if (lock == null) {
      channel.close();
      return null;
    }
    return lock;
  }

  public void releaseLock(FileLock lock) throws IOException {
    if (lock != null && lock.isValid()) {
      FileChannel ch = lock.channel();
      lock.release();
      ch.close();
    }
  }
}
