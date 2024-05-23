package com.goorm.server.repository;

import com.goorm.server.domain.CoordinateInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinateInfoRepository extends JpaRepository<CoordinateInfo, Long> {
    //int countByBeach(Beach beach);
}
