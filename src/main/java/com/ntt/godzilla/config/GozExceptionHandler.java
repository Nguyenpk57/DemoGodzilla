package com.ntt.godzilla.config;

import com.ntt.godzilla.dto.ErrorResponseDTO;
import com.ntt.godzilla.dto.BaseResponseDTO;
import com.ntt.godzilla.exception.GozBizException;
import com.ntt.godzilla.util.ErrorCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GozExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(GozBizException.class)
    public ResponseEntity<BaseResponseDTO> handleBadRequestException(GozBizException ex){
        String errorCode = ex.getCode();
        log.error("ERROR BadRequestException on: {}", errorCode);
        BaseResponseDTO response = new BaseResponseDTO(errorCode, ErrorCodeUtils.getErrorMessage(errorCode));
        return ResponseEntity.ok(response);
    }
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ErrorResponseDTO> handleTransactionSystemException(TransactionSystemException ex){
        log.error("ERROR a TransactionSystemException throw: {}", ex.getOriginalException().getMessage());
        ErrorResponseDTO errorMessageDTO = new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getOriginalException().getMessage());
        return new ResponseEntity<>(errorMessageDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex){
        log.error("ERROR Exception on: {}", ex.getMessage());
        ErrorResponseDTO errorMessageDTO = new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),  ex.getMessage());
        return new ResponseEntity<>(errorMessageDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        log.error("ERROR MethodArgumentNotValidException on: {}", ex.getMessage());
        ErrorResponseDTO errorMessageDTO = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), "Argument not match");
        return new ResponseEntity<>(errorMessageDTO, HttpStatus.BAD_REQUEST);
    }
}
