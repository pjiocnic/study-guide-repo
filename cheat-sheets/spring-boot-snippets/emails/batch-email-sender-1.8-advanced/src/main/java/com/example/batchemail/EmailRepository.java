package com.example.batchemail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {

    @Query(value = "SELECT * FROM emails_table1 WHERE sent = false LIMIT ?1", nativeQuery = true)
    List<Email> findTopNUnsent(int limit);
}
