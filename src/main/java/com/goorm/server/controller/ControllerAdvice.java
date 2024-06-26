package com.goorm.server.controller;

import com.goorm.server.exception.GoormException;
import com.goorm.server.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ControllerAdvice {

    private static final int FIELD_ERROR_CODE_INDEX = 0;
    private static final int FIELD_ERROR_MESSAGE_INDEX = 1;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleInputFieldException(MethodArgumentNotValidException e) {
        FieldError mainError = e.getFieldErrors().get(0);
        String[] errorInfo = Objects.requireNonNull(mainError.getDefaultMessage()).split(":");

        int code = Integer.parseInt(errorInfo[FIELD_ERROR_CODE_INDEX]);
        String message = errorInfo[FIELD_ERROR_MESSAGE_INDEX];

        return ResponseEntity.badRequest().body(new ErrorResponse(code, message));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleJsonException(HttpMessageNotReadableException e) {
        log.warn("Json Exception ErrMessage={}\n", e.getMessage());

        return ResponseEntity.badRequest()
                .body(new ErrorResponse(9000, "Json 형식이 올바르지 않습니다."));
    }

    @ExceptionHandler(HttpMediaTypeException.class)
    public ResponseEntity<ErrorResponse> handleContentTypeException(HttpMediaTypeException e) {
        log.warn("ContentType Exception ErrMessage={}\n", e.getMessage());

        return ResponseEntity.badRequest()
                .body(new ErrorResponse(9001, "ContentType 값이 올바르지 않습니다."));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleRequestMethodException(HttpRequestMethodNotSupportedException e) {
        log.warn("Http Method not supported Exception ErrMessage={}\n", e.getMessage());

        return ResponseEntity.badRequest()
                .body(new ErrorResponse(9002, "해당 Http Method에 맞는 API가 존재하지 않습니다."));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingRequestParamException(MissingServletRequestParameterException e) {
        log.warn("Request Param is Missing! ErrMessage={}\n", e.getMessage());

        return ResponseEntity.badRequest()
                .body(new ErrorResponse(9003, "요청 param 이름이 올바르지 않습니다."));
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<ErrorResponse> handleMissingMultiPartParamException(FileUploadException e) {
        log.warn("File Upload Exception! Please check request. ErrMessage={}\n", e.getMessage());

        return ResponseEntity.badRequest()
                .body(new ErrorResponse(9004, "요청 파일이 올바르지 않습니다. 파일 손상 여부나 요청 형식을 확인해주세요."));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.warn("Invalid date format! Please provide the date in the format yyyy-MM-dd. ErrMessage={}\n", e.getMessage());

        return ResponseEntity.badRequest()
                .body(new ErrorResponse(9005, "올바른 날짜 형식으로 입력해주세요."));
    }

    @ExceptionHandler(GoormException.class)
    public ResponseEntity<ErrorResponse> handleGoormException(GoormException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(new ErrorResponse(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> unhandledException(Exception e, HttpServletRequest request) {
        log.error("UnhandledException: {} {} errMessage={}\n",
                request.getMethod(),
                request.getRequestURI(),
                e.getMessage()
        );
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse(9999, "일시적으로 접속이 원활하지 않습니다. 서버 팀에 문의 부탁드립니다."));
    }
}
