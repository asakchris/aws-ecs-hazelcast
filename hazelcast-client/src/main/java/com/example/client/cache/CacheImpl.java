package com.example.client.cache;

import com.example.client.vo.Token;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheImpl implements Cache {
    @Override
    @Cacheable(value = "token-cache", key = "#username")
    public Token cache(String username, Token token) {
        return token;
    }
}
