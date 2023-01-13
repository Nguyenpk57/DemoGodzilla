package com.ntt.godzilla.factory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseFactory {

    public ResponseEntity success() {
        GeneralResponse<Object> responseObject = new GeneralResponse();
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setCode(ResponseStatusEnum.SUCCESS.getCode());
        responseStatus.setMessage(ResponseStatusEnum.SUCCESS.getMessage());
        responseObject.setStatus(responseStatus);
        return ResponseEntity.ok(responseObject);
    }

    public ResponseEntity success(Object data, Class clazz) {
        GeneralResponse<Object> responseObject = new GeneralResponse();
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setCode(ResponseStatusEnum.SUCCESS.getCode());
        responseStatus.setMessage(ResponseStatusEnum.SUCCESS.getMessage());
        responseObject.setStatus(responseStatus);
        responseObject.setData(clazz.cast(data));
        return ResponseEntity.ok(responseObject);
    }

    public ResponseEntity success(Object data) {
        GeneralResponse<Object> responseObject = new GeneralResponse();
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setCode(ResponseStatusEnum.SUCCESS.getCode());
        responseStatus.setMessage(ResponseStatusEnum.SUCCESS.getMessage());
        responseObject.setStatus(responseStatus);
        responseObject.setData(data);
        return ResponseEntity.ok(responseObject);
    }

    public ResponseEntity error(HttpStatus httpStatus, String errorCode, String errorMessage) {
        ResponseStatus responseStatus = new ResponseStatus();
        GeneralResponse<Object> responseObject = new GeneralResponse();
        responseStatus.setCode(errorCode);
        responseStatus.setMessage(errorMessage);
        responseObject.setStatus(responseStatus);
        return new ResponseEntity(responseObject, httpStatus);
    }

    public ResponseEntity error(HttpStatus httpStatus, String errorCode, String errorMessage, Object data) {
        ResponseStatus responseStatus = new ResponseStatus();
        GeneralResponse<Object> responseObject = new GeneralResponse();
        responseStatus.setCode(errorCode);
        responseStatus.setMessage(errorMessage);
        responseObject.setStatus(responseStatus);
        responseObject.setData(data);
        return new ResponseEntity(responseObject, httpStatus);
    }

    public ResponseEntity error(HttpStatus httpStatus, ResponseStatusEnum status) {
        ResponseStatus responseStatus = new ResponseStatus();
        GeneralResponse<Object> responseObject = new GeneralResponse();
        responseStatus.setCode(status.getCode());
        responseStatus.setMessage(status.getMessage());
        responseObject.setStatus(responseStatus);
        return new ResponseEntity(responseObject, httpStatus);
    }

    public ResponseEntity error(HttpStatus httpStatus, IResponseStatus status) {
        ResponseStatus responseStatus = new ResponseStatus();
        GeneralResponse<Object> responseObject = new GeneralResponse();
        responseStatus.setCode(status.getCode());
        responseStatus.setMessage(status.getMessage());
        responseObject.setStatus(responseStatus);
        return new ResponseEntity(responseObject, httpStatus);
    }

    public ResponseEntity error(HttpStatus httpStatus, IResponseStatus status, Object data) {
        ResponseStatus responseStatus = new ResponseStatus();
        GeneralResponse<Object> responseObject = new GeneralResponse();
        responseStatus.setCode(status.getCode());
        responseStatus.setMessage(status.getMessage());
        responseObject.setStatus(responseStatus);
        responseObject.setData(data);
        return new ResponseEntity(responseObject, httpStatus);
    }
}
