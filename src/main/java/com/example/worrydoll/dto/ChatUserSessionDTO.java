package com.example.worrydoll.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ChatUserSessionDTO {
    private final String username;
    private final long userId;
}
