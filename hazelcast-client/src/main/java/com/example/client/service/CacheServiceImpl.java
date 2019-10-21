package com.example.client.service;

import com.example.client.cache.Cache;
import com.example.client.vo.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CacheServiceImpl implements CacheService {
    private static Logger log = LoggerFactory.getLogger(CacheServiceImpl.class);

    private final Cache cache;

    @Autowired
    private CacheManager cacheManager;

    public CacheServiceImpl(Cache cache) {
        this.cache = cache;
    }

    @Override
    public Token cacheToken(Token token) {
        Token cache = this.cache.cache(token.getUsername(), token);
        Token token1 = cacheManager.getCache("token-cache").get(token.getUsername(), Token.class);
        log.info("token from cache manger for user name: {}, token: {}", token.getUsername(), token1);
        if (cache == null) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Not able to store in cache");
        }
        return cache;
    }

    @Override
    public Token getToken(String username) {
        Token token = cache.cache(username, null);

        if (token == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token not found");
        }

        return token;
    }
}
