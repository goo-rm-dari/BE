package com.goorm.server.repository;

import com.goorm.server.domain.PloggingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PloggingRecordRepository extends JpaRepository<PloggingRecord, Long> {
}
