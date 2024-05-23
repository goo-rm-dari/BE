package com.goorm.server.repository;

import com.goorm.server.domain.Beach;
import com.goorm.server.domain.TrashCoordinate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrashCoordinateInfoRepository extends JpaRepository<TrashCoordinate, Long> {
    int countByBeach(Beach beach);
}
