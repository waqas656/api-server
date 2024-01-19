package com.whatsapp.apiserver.services;

import com.whatsapp.apiserver.exceptions.ChatroomNotFoundException;
import com.whatsapp.apiserver.exceptions.UserNotFoundException;
import com.whatsapp.apiserver.models.dto.ChatroomUserDto;
import com.whatsapp.apiserver.models.dto.ChatroomDTO;
import com.whatsapp.apiserver.models.entities.Chatroom;
import com.whatsapp.apiserver.models.entities.User;
import com.whatsapp.apiserver.models.mappers.ChatroomMapper;
import com.whatsapp.apiserver.repositories.ChatroomRepository;
import com.whatsapp.apiserver.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ChatroomService {

    @Autowired
    private ChatroomRepository chatroomRepository;

    @Autowired
    private UserRepository userRepository;

    public ChatroomDTO createChatroom(ChatroomDTO chatroomDTO) {
        // Creating a new Chatroom entity and save it to the database
        Chatroom chatroom = new Chatroom();
        chatroom.setName(chatroomDTO.getName());

        chatroom = chatroomRepository.save(chatroom);

        // Convert the saved entity to a DTO and return it
        return ChatroomMapper.CHATROOM_MAPPER.toChatroomDto(chatroom);
    }

    public void leaveChatroom(@Valid ChatroomUserDto chatroomUserDto) {
        Map<String, Object> userChatroomMap = validateUserAndChatroomIds(chatroomUserDto);

        User user = (User) userChatroomMap.get("user");
        Chatroom chatroom = (Chatroom) userChatroomMap.get("chatroom");

        //remove user from existing users list of the chatroom
        chatroom.getUsers().remove(user);
    }

    public ChatroomDTO addUserToChatroom(ChatroomUserDto chatroomUserDto) {
        Map<String, Object> userChatroomMap = validateUserAndChatroomIds(chatroomUserDto);

        User user = (User) userChatroomMap.get("user");
        Chatroom chatroom = (Chatroom) userChatroomMap.get("chatroom");

        //add user to existing users list of the chatroom
        chatroom.getUsers().add(user);

        //convert Chatroom entity to DTO
        return ChatroomMapper.CHATROOM_MAPPER.toChatroomDto(chatroom);
    }

    private Map<String, Object> validateUserAndChatroomIds(ChatroomUserDto chatroomUserDto) {
        Optional<User> user = userRepository.findUserById(chatroomUserDto.getUserId());
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with ID: " + chatroomUserDto.getUserId());
        }

        Optional<Chatroom> chatroom = chatroomRepository.findChatroomById(chatroomUserDto.getChatroomId());
        if (chatroom.isEmpty()) {
            throw new ChatroomNotFoundException("Chatroom not found with ID: " + chatroomUserDto.getChatroomId());
        }

        Map<String, Object> userChatroomMap = new HashMap<>();
        userChatroomMap.put("chatroom", chatroom.get());
        userChatroomMap.put("user", user.get());

        return userChatroomMap;
    }

}

