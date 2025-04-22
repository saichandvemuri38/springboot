package com.example.OrderService.external.decoder;

import com.example.OrderService.exceptions.CustomException;
import com.example.OrderService.external.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("Error while calling external service:{}",response.request().url());
        log.info("Error while calling external service:{}",response.headers());
        try{
            ErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);
            return new CustomException(errorResponse.getErrorMessage(),errorResponse.getErrorCode(),response.status());
        }catch (Exception e){
            throw  new CustomException("Inter Server Error","INTERNAL_SERVER_ERROR",500);
        }
    }
}
