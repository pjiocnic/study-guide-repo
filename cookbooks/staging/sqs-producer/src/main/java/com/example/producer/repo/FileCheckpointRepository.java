package com.example.producer.repo;

import com.example.producer.domain.FileCheckpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FileCheckpointRepository extends JpaRepository<FileCheckpoint, Long> {
  Optional<FileCheckpoint> findByFilePath(String filePath);
}
