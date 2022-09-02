package com.example.trainingsystembackend.controller;

import com.example.trainingsystembackend.view.View;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import java.util.Collection;

@org.springframework.web.bind.annotation.ControllerAdvice(annotations = RestController.class)

public class ControllerAdvice extends AbstractMappingJacksonResponseBodyAdvice {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType, MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
//
    }
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<Object> allExeceptions(Exception e) {

        ObjectNode bodyOfResponse=objectMapper.createObjectNode();
        bodyOfResponse.put("status", HttpStatus.BAD_REQUEST.value());
        bodyOfResponse.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        bodyOfResponse.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyOfResponse);
    }
}