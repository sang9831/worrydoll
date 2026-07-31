package com.example.worrydoll.repository;

import com.example.worrydoll.entity.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatUserJpaRepository extends JpaRepository<ChatUser, Long> {
    Optional<ChatUser> findByUsername(String username);
}
