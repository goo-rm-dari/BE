package com.goorm.server.repository;

import com.goorm.server.domain.PloggingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PloggingRecordRepository extends JpaRepository<PloggingRecord, String> {
    List<PloggingRecord> findAllByMemberId(String memberId);

    List<PloggingRecord> findByMemberId(String memberId);
}
