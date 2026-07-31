package com.example.worrydoll.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED) // Builder를 위한 것
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA를 위한 것
public class ChatUser extends BaseEntity {
    @Column(unique = true)
    private String username;
}
