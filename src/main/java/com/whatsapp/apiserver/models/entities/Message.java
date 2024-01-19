package com.whatsapp.apiserver.models.entities;

import com.whatsapp.apiserver.models.dto.MessageTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_user_id")
    private User senderUser;

    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private Chatroom chatroom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageTypeEnum messageType;

    @Column(nullable = false)
    private LocalDateTime sentTimestamp;

    // Fields for a text message
    @Column(length = 1024)
    private String text;

    // Fields for a video message
    @Column(length = 255)
    private String videoUrl;

    @Column(length = 255)
    private String videoFileName;

    // Fields for an attachment message
    @Column(length = 255)
    private String attachmentUrl;

    @Column(length = 255)
    private String attachmentFileName;

}


