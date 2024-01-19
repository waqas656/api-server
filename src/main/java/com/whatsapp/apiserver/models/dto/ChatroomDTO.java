package com.whatsapp.apiserver.models.dto;


import com.whatsapp.apiserver.models.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatroomDTO {

    @NotBlank(message = "Chatroom name cannot be blank")
    @Size(max = 50, message = "Chatroom name cannot exceed 50 characters")
    private String name;

    @NotEmpty
    private Set<User> users = new HashSet<>();
}

