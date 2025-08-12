package com.example.producer.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "file_checkpoint", uniqueConstraints = @UniqueConstraint(columnNames = "filePath"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FileCheckpoint {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String filePath;

  @Column(nullable = false)
  private long nextLineNumber;

  @Column(nullable = false)
  private long nextByteOffset;

  private long fileSizeBytes;
  private Instant updatedAt;

  @Column(nullable = false)
  private String status; // PENDING, IN_PROGRESS, DONE
}
