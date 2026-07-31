package com.example.worrydoll.repository;

import com.example.worrydoll.entity.ChatMessage;
import com.example.worrydoll.entity.ChatUser;
import com.google.genai.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.messages.Message;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatMessageRepository implements ChatMemoryRepository {
    private final ChatMessageJpaRepository chatMessageJpaRepository;

    @Override
    public List<String> findConversationIds() {
        // conversationIds <- 대화 목록을 구분하는 구분자 (user 구분 또는 방 구분)
        return chatMessageJpaRepository.findConversationIds();
    }

    // 해당 유저/방 데이터만 찾을 수 있게
    @Override
    public List<Message> findByConversationId(String conversationId) {
        return chatMessageJpaRepository
                .findAllByConversationId(conversationId)
                .stream()
                .map(ChatMessage::toMessage)
                .toList();
    }

    // 저장되어있던 메모리를 아예 대체
    @Override
    @Transactional
    public void saveAll(String conversationId, List<Message> messages) {
        deleteByConversationId(conversationId);

        List<ChatMessage> chatMessages = new ArrayList<>();
        for (Message message : messages) {
            chatMessages.add(
                    ChatMessage.fromMessage(
                            message, conversationId,
                            chatMessages.size())
            );
        }

        chatMessageJpaRepository.saveAll(chatMessages);
    }

    // 전체 삭제 (이후 재등록 시 처리하기 위함)
    @Override
    @Transactional
    public void deleteByConversationId(String conversationId) {
        chatMessageJpaRepository.deleteAllByConversationId(conversationId);
    }
}