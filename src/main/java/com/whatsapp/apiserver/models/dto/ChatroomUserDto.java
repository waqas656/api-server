package com.whatsapp.apiserver.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatroomUserDto {

    @NotBlank
    Long chatroomId;

    @NotBlank
    Long userId;
}
