package com.example.client.service;

import com.example.client.vo.Token;

public interface CacheService {
    Token cacheToken(Token token);
    Token getToken(String username);
}
