package com.goorm.server.service;

import com.goorm.server.domain.Beach;
import com.goorm.server.dto.response.BeachInfoResponse;
import com.goorm.server.dto.response.TotalTrashResponse;
import com.goorm.server.repository.TrashCoordinateInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrashCoordinateService {

    private final TrashCoordinateInfoRepository trashCoordinateInfoRepository;

    public TotalTrashResponse findTotalTrashCount(Beach beach) {
        int totalTrashCount = trashCoordinateInfoRepository.countByBeach(beach);
        return new TotalTrashResponse(totalTrashCount);
    }

    public BeachInfoResponse findNearbyBeachesWithTrash(double lat, double lng) {
        return new BeachInfoResponse(Beach.GWANGCHIGI.getBeachName());
    }
}
