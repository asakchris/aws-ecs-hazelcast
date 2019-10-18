package com.example.client.controller;

import com.example.client.service.CacheService;
import com.example.client.vo.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "tokens")
public class CacheController {
    private static Logger log = LoggerFactory.getLogger(CacheController.class);

    private final CacheService service;

    public CacheController(CacheService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Token> cacheToken(@RequestBody Token token) {
        log.info("token: {}", token);
        Token response = service.cacheToken(token);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(params = "username")
    public ResponseEntity<Token> getToken(@RequestParam String username) {
        log.info("username: {}", username);
        Token response = service.getToken(username);
        return ResponseEntity.ok(response);
    }
}
