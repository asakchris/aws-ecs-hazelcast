package com.example.client.vo;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Token implements Serializable {
    private String username;
    private String token;
}
