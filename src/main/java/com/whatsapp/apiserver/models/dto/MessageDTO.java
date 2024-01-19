package com.whatsapp.apiserver.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageDTO {
    private Long messageId;

    @NotBlank
    private Long senderUserId;

    @NotBlank
    private Long chatroomId;

    @NotBlank
    private MessageTypeEnum messageType;

    private LocalDateTime sentTimestamp;
    private String text;
    private String videoUrl;
    private String videoFileName;
    private String attachmentUrl;
    private String attachmentFileName;
}
