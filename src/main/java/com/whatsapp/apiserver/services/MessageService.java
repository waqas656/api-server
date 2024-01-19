package com.whatsapp.apiserver.services;

import com.whatsapp.apiserver.exceptions.ChatroomNotFoundException;
import com.whatsapp.apiserver.exceptions.IllegalMessageTypeException;
import com.whatsapp.apiserver.exceptions.UserNotFoundException;
import com.whatsapp.apiserver.models.dto.MessageDTO;
import com.whatsapp.apiserver.models.dto.MessageTypeEnum;
import com.whatsapp.apiserver.models.entities.Chatroom;
import com.whatsapp.apiserver.models.entities.User;
import com.whatsapp.apiserver.repositories.ChatroomRepository;
import com.whatsapp.apiserver.repositories.MessageRepository;
import com.whatsapp.apiserver.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private ChatroomRepository chatroomRepository;

    @Autowired
    private UserRepository userRepository;

    public MessageDTO sendTextMessage(MessageDTO messageDTO) {
        if (!isValidMessageType(messageDTO.getMessageType())) {
            throw new IllegalMessageTypeException("Invalid message type.");
        }

        Map<String, Object> userChatroomMap = validateUserAndChatroomIds(messageDTO);

        User user = (User) userChatroomMap.get("user");
        Chatroom chatroom = (Chatroom) userChatroomMap.get("chatroom");
        // Implement logic to save a text message and return its DTO
        // You may also add logic to save the message to the chatroom
        // and handle other message-related operations
        return null;
    }

    private boolean isValidMessageType(MessageTypeEnum messageType) {
        for (MessageTypeEnum enumValue : MessageTypeEnum.values()) {
            if (enumValue.equals(messageType)) {
                return true;
            }
        }
        return false;
    }

    public MessageDTO sendVideoMessage(MultipartFile videoFile, @Valid MessageDTO messageDTO) {
        // Implement logic to save a video message and return its DTO
        // You can use the FileService to save the video file to the server
        // Validate the video size here as well
        return null;
    }

    public MessageDTO sendAttachment(MultipartFile attachmentFile, MessageDTO messageDTO) {
        // Implement logic to save an attachment message and return its DTO
        // You can use the FileService to save the attachment file to the server
        return null;
    }

    private Map<String, Object> validateUserAndChatroomIds(MessageDTO messageDTO) {
        Optional<User> user = userRepository.findUserById(messageDTO.getSenderUserId());
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with ID: " + messageDTO.getSenderUserId());
        }

        Optional<Chatroom> chatroom = chatroomRepository.findChatroomById(messageDTO.getChatroomId());
        if (chatroom.isEmpty()) {
            throw new ChatroomNotFoundException("Chatroom not found with ID: " + messageDTO.getChatroomId());
        }

        Map<String, Object> userChatroomMap = new HashMap<>();
        userChatroomMap.put("chatroom", chatroom.get());
        userChatroomMap.put("user", user.get());

        return userChatroomMap;
    }
}

