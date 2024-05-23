package com.goorm.server.service;

import com.goorm.server.domain.CoordinateInfo;
import com.goorm.server.domain.PloggingRecord;
import com.goorm.server.dto.request.CoordinateInfoDTO;
import com.goorm.server.dto.request.PloggingRecordRegisterRequest;
import com.goorm.server.dto.response.PloggingRecordListResponse;
import com.goorm.server.dto.response.PloggingRecordRegisterResponse;
import com.goorm.server.dto.response.PloggingRecordResponse;
import com.goorm.server.repository.CoordinateInfoRepository;
import com.goorm.server.repository.PloggingRecordRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PloggingRecordService {

    private final PloggingRecordRepository ploggingRecordRepository;
    private final CoordinateInfoRepository coordinateInfoRepository;

    @Transactional
    public PloggingRecordRegisterResponse registerPloggingRecord(PloggingRecordRegisterRequest request) {
        PloggingRecord ploggingRecord = new PloggingRecord(
                request.getMemberId(),
                new ArrayList<>(),
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

        return new PloggingRecordRegisterResponse(
                savedPloggingRecord.getId()
        );
    }

    @Transactional(readOnly = true)
    public PloggingRecordListResponse getPloggingRecords(Long memberId) {
        List<PloggingRecord> ploggingRecords = ploggingRecordRepository.findByMemberId(memberId);
        if (ploggingRecords.isEmpty()) {
            return new PloggingRecordListResponse(new ArrayList<>());
        }

        List<PloggingRecordResponse> ploggingRecordResponses = ploggingRecords.stream()
                .map(ploggingRecord -> new PloggingRecordResponse(
                        ploggingRecord.getCreatedTime().toLocalDateTime().toLocalDate(),
                        ploggingRecord.getCoordinateInfos().stream()
                                .map(coordinateInfo -> new CoordinateInfoDTO(coordinateInfo.getLat(), coordinateInfo.getLng(), coordinateInfo.isTrash()))
                                .collect(Collectors.toList()),
                        ploggingRecord.getCount(),
                        ploggingRecord.getTotalCalories(),
                        ploggingRecord.getMovingTime(),
                        ploggingRecord.getMovingDistance()
                ))
                .collect(Collectors.toList());

        return new PloggingRecordListResponse(ploggingRecordResponses);
    }
}
