package com.example.client.service;

import com.example.client.cache.Cache;
import com.example.client.vo.Token;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CacheServiceImpl implements CacheService {
    private final Cache cache;

    public CacheServiceImpl(Cache cache) {
        this.cache = cache;
    }

    @Override
    public Token cacheToken(Token token) {
        return cache.cache(token.getUsername(), token);
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
