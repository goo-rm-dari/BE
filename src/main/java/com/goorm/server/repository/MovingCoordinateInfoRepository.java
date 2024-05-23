package com.goorm.server.repository;

import com.goorm.server.domain.MovingCoordinate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovingCoordinateInfoRepository extends JpaRepository<MovingCoordinate, Long> {
    //int countByBeach(Beach beach);
}
