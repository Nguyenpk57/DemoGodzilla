package com.ntt.godzilla.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntt.godzilla.factory.GeneralResponse;
import com.ntt.godzilla.factory.ResponseFactory;
import com.ntt.godzilla.factory.ResponseStatusEnum;
import com.ntt.godzilla.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    private ResponseFactory responseFactory;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public ExceptionHandlerController(ResponseFactory responseFactory) {
        this.responseFactory = responseFactory;
    }

    @Override
    @ResponseBody
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        JsonMappingException jme = (JsonMappingException) e.getCause();
        String errorField = jme.getPath().get(jme.getPath().size() - 1).getFieldName();
        log.error("Invalid data type for [{}]", errorField);
        return responseFactory.error(HttpStatus.OK, ResponseStatusEnum.INVALID_DATA_TYPE.getCode(),
                String.format(ResponseStatusEnum.INVALID_DATA_TYPE.getMessage(), errorField));
    }

    @Override
    @ResponseBody
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (e.getBindingResult().hasFieldErrors()) {
            FieldError fieldError = e.getBindingResult().getFieldError();
            ResponseStatusEnum responseStatus = ResponseStatusEnum.fromCode(fieldError.getDefaultMessage());
            String fieldName = StringUtils.getNameByCamelCase(fieldError.getField());
            String message = String.format(responseStatus.getMessage(), fieldName);
            log.error(message);
            return responseFactory.error(HttpStatus.OK, responseStatus.getCode(), message);
        }
        return responseFactory.error(HttpStatus.INTERNAL_SERVER_ERROR, ResponseStatusEnum.GENERAL_ERROR);
    }

    @Override
    @ResponseBody
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException e, HttpHeaders headers, HttpStatus status,
                                                        WebRequest request) {
        if (e instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException matme = (MethodArgumentTypeMismatchException) e;
            log.error("Invalid data type for [{}]", matme.getName());
            return responseFactory.error(HttpStatus.OK, ResponseStatusEnum.INVALID_DATA_TYPE.getCode(),
                    String.format(ResponseStatusEnum.INVALID_DATA_TYPE.getMessage(), matme.getName()));
        }
        return responseFactory.error(HttpStatus.INTERNAL_SERVER_ERROR, ResponseStatusEnum.GENERAL_ERROR);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<GeneralResponse<Object>> handleException(HttpServerErrorException e) {
        GeneralResponse response;
        try {
            response = objectMapper.readValue(e.getResponseBodyAsString(), GeneralResponse.class);
        } catch (IOException ioEx) {
            log.error("Error", ioEx);
            return responseFactory.error(HttpStatus.OK, ResponseStatusEnum.GENERAL_ERROR);
        }
        return responseFactory.error(HttpStatus.OK, response.getStatus());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<GeneralResponse<Object>> handleException(Exception e) {
        log.error("Error", e);
        return responseFactory.error(HttpStatus.OK, ResponseStatusEnum.GENERAL_ERROR);
    }

    @ExceptionHandler({HttpClientErrorException.class})
    @ResponseBody
    public ResponseEntity<GeneralResponse<Object>> handleBadRequest(HttpClientErrorException httpEx) {
        GeneralResponse response;
        try {
            response = objectMapper.readValue(httpEx.getResponseBodyAsString(), GeneralResponse.class);
        } catch (IOException ioEx) {
            log.error("Error", ioEx);
            return responseFactory.error(HttpStatus.OK, ResponseStatusEnum.GENERAL_ERROR);
        }
        log.error("Got error with http status [{}], status code [{}], status message [{}]", HttpStatus.BAD_REQUEST,
                response.getStatus().getCode(), response.getStatus().getMessage());
        return responseFactory.error(HttpStatus.OK, response.getStatus());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ResponseEntity<GeneralResponse<Object>> handleValidationException(ValidationException e) {
        return responseFactory.error(HttpStatus.OK, e.getErrorStatus());
    }

    @ExceptionHandler(BadRequestValidationException.class)
    @ResponseBody
    public ResponseEntity<GeneralResponse<Object>> handleBadRequestValidationException(BadRequestValidationException e) {
        return responseFactory.error(HttpStatus.OK, e.getErrorStatus());
    }

    @ExceptionHandler(BadRequestValidationWithMessageFormattedException.class)
    @ResponseBody
    public ResponseEntity<GeneralResponse<Object>> handleBadRequestValidationDynamicMessageException(BadRequestValidationWithMessageFormattedException e) {
        return responseFactory.error(HttpStatus.OK, e.getErrorStatus().getCode(), e.getMessage());
    }

    @ExceptionHandler(ClientException.class)
    @ResponseBody
    public ResponseEntity<GeneralResponse<Object>> handleClientException(ClientException e) {
        if (e.getHttpStatus() == null) {
            return responseFactory.error(HttpStatus.OK, e.getStatus());
        } else {
            log.error("Got error with http status [{}], status code [{}], status message [{}]", HttpStatus.BAD_REQUEST,
                    e.getStatus().getCode(), e.getStatus().getMessage());
            Object data = e.getData();
            if (data != null) {
                return responseFactory.error(e.getHttpStatus(), e.getStatus(), data);
            } else {
                return responseFactory.error(e.getHttpStatus(), e.getStatus());
            }
        }
    }
}