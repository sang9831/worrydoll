package com.example.worrydoll.repository;

import com.example.worrydoll.entity.ChatUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatUserRepository {
    private final ChatUserJpaRepository chatUserJpaRepository;

    public ChatUser save(ChatUser chatUser){
        return chatUserJpaRepository.save(chatUser);
    }

    public ChatUser findByUsername(String username){
        return chatUserJpaRepository.findByUsername(username).orElseThrow();
    }
}
