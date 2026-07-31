package com.example.worrydoll.entity;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.ai.chat.messages.*;

import java.awt.*;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED) // Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA
@ToString
public class ChatMessage extends BaseEntity {
    private String conversationId;
    private String messageType;
    private String content;
    private long seq;

    // import org.springframework.ai.chat.messages.Message;
    public Message toMessage() {
        MessageType messageType = MessageType.valueOf(this.messageType);
        return switch (messageType) {
            case USER -> new UserMessage(this.content);
            case ASSISTANT -> new AssistantMessage(this.content); // AI의 메시지
            case SYSTEM -> new SystemMessage(this.content);
            default -> throw new IllegalArgumentException("지원하지 않는 메시지 타입: %s".formatted(messageType));
        };
    }

    public static ChatMessage fromMessage(Message message, String conversationId, long seq) {
        return ChatMessage.builder()
                .conversationId(conversationId)
                .messageType(message.getMessageType().name())
                .content(message.getText())
                .seq(seq)
                .build();
    }
}