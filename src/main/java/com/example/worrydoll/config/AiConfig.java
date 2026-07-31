package com.example.worrydoll.config;

import com.google.genai.Chat;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AiConfig {
    @Bean
    public ChatClient ragChatClient(ChatModel chatModel, VectorStore vectorStore) {
        return ChatClient
                .builder(chatModel)
                .defaultOptions(
//                        ChatOptions.builder()
                        ChatOptions.builder()
                                // spring.ai.google.genai.chat.model=gemini-3.5-flash-lite
                                .model("gemini-3.1-flash-lite") // 사용량을 나눠서 쓰기 위해 다른 모델로 변경
                                .temperature(0.2) // 0.5~0.7 자유롭거나 약간 창의적
                        // 이미 참고하는 것이 있기 때문에 비슷한 대답을 할 수 있도록 0.2~0.3
                )
                .defaultSystem(
                        "맥락 안에서만 대답하고, 모르는 것은 '정보가 없다'고 대답"
                        // 사용자 질문만 임베딩되어 있음
                )
                .defaultAdvisors(
                        QuestionAnswerAdvisor
                                .builder(vectorStore)
                                .searchRequest(SearchRequest.builder()
//                                        .topK(4)
//                                        .similarityThreshold(0.0)
                                        // 조금 더 엄격한 기준을 적용
                                        .similarityThreshold(0.5)
                                        .build())
                                .build())
                .build();
    }

    @Primary
    @Bean
    public ChatClient chatClient(ChatModel chatModel, ChatMemory chatMemory) {
        return ChatClient
                .builder(chatModel)
//                .defaultSystem()
                .defaultAdvisors(
                        MessageChatMemoryAdvisor
                                .builder(chatMemory).build())
                .build();
    }

    @Bean
    @Primary
    public ChatMemory jpaChatMemory(
            // ChatMessageRepository <- ChatMemoryRepository
            ChatMemoryRepository chatMemoryRepository) {
        return MessageWindowChatMemory.builder()
                .chatMemoryRepository(chatMemoryRepository)
//                .maxMessages(20)
                .build();
    }
}
