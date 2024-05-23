package com.goorm.server.service;

import com.goorm.server.domain.CoordinateInfo;
import com.goorm.server.domain.PloggingRecord;
import com.goorm.server.dto.request.PloggingRecordRegisterRequest;
import com.goorm.server.dto.response.PloggingRecordRegisterResponse;
import com.goorm.server.repository.CoordinateInfoRepository;
import com.goorm.server.repository.PloggingRecordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PloggingRecordService {

    private final PloggingRecordRepository ploggingRecordRepository;
    private final CoordinateInfoRepository coordinateInfoRepository;

    @Transactional
    public PloggingRecordRegisterResponse registerPloggingRecord(PloggingRecordRegisterRequest request) {
        PloggingRecord ploggingRecord = new PloggingRecord(
                request.getMemberId(),
                new ArrayList<>(), // CoordinateInfo 리스트는 나중에 설정
                request.getCount(),
                request.getTotalCalories(),
                request.getMovingTime(),
                request.getMovingDistance()
        );
        PloggingRecord savedPloggingRecord = ploggingRecordRepository.save(ploggingRecord);

        List<CoordinateInfo> coordinateInfos = new ArrayList<>();
        for (var coordinateInfoDTO : request.getCoordinateInfos()) {
            CoordinateInfo coordinateInfo = new CoordinateInfo(
                    savedPloggingRecord,
                    coordinateInfoDTO.getLat(),
                    coordinateInfoDTO.getLng(),
                    coordinateInfoDTO.isTrash()
            );
            coordinateInfos.add(coordinateInfo);
        }
        coordinateInfoRepository.saveAll(coordinateInfos);
        savedPloggingRecord.setCoordinateInfos(coordinateInfos);

        // 응답 객체 생성 및 반환
        return new PloggingRecordRegisterResponse(
                savedPloggingRecord.getId()
        );
    }
}
