package com.example.client.cache;

import com.example.client.vo.Token;

public interface Cache {
    Token cache(String username, Token token);
}
