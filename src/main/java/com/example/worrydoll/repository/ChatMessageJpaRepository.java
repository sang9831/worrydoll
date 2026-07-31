package com.example.worrydoll.repository;

import com.example.worrydoll.entity.ChatMessage;
import com.example.worrydoll.entity.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChatMessageJpaRepository extends JpaRepository<ChatMessage, Long> {

    @Query("SELECT DISTINCT c.conversationId FROM ChatMessage c")
    List<String> findConversationIds();

    List<ChatMessage> findAllByConversationId(String conversationId);

    void deleteAllByConversationId(String conversationId);
}