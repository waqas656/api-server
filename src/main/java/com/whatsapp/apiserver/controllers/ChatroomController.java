package com.whatsapp.apiserver.controllers;

import com.whatsapp.apiserver.models.dto.ChatroomDTO;
import com.whatsapp.apiserver.models.dto.MessageDTO;
import com.whatsapp.apiserver.models.dto.ChatroomUserDto;
import com.whatsapp.apiserver.services.ChatroomService;
import com.whatsapp.apiserver.services.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/chatrooms")
@Api(tags = "Chatroom")
public class ChatroomController {

    @Autowired
    private ChatroomService chatroomService;

    @Autowired
    private MessageService messageService;


    @PostMapping("/create")
    @ApiOperation(value = "Create a chatroom")
    public ResponseEntity<ChatroomDTO> createChatroom(@Valid @RequestBody ChatroomDTO chatroomDTO) {
        ChatroomDTO createdChatroom = chatroomService.createChatroom(chatroomDTO);
        return ResponseEntity.ok(createdChatroom);
    }

    @PostMapping("/add-user")
    @ApiOperation(value = "Add user to a chatroom")
    public ResponseEntity<ChatroomDTO> addUserChatroom(@Valid @RequestBody ChatroomUserDto chatroomUserDto) {
        ChatroomDTO createdChatroom = chatroomService.addUserToChatroom(chatroomUserDto);
        return ResponseEntity.ok(createdChatroom);
    }

    @PostMapping("/leave")
    @ApiOperation(value = "Leave a chatroom")
    public ResponseEntity<Void> leaveChatroom(@Valid @RequestBody ChatroomUserDto chatroomUserDto) {
        chatroomService.leaveChatroom(chatroomUserDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/send-message")
    @ApiOperation(value = "Send a text message")
    public ResponseEntity<MessageDTO> sendTextMessage(@Valid @RequestBody MessageDTO messageDTO) {
        MessageDTO sentMessage = messageService.sendTextMessage(messageDTO);
        return ResponseEntity.ok(sentMessage);
    }

    @PostMapping("/send-video")
    @ApiOperation(value = "Send a video message (max 50MB)")
    public ResponseEntity<MessageDTO> sendVideoMessage(@RequestParam("file") MultipartFile videoFile,
                                                       @Valid @RequestParam("payload") MessageDTO messageDTO) {
        MessageDTO sentMessage = messageService.sendVideoMessage(videoFile, messageDTO);
        return ResponseEntity.ok(sentMessage);
    }

    @PostMapping("/send-attachment")
    @ApiOperation(value = "Send an attachment")
    public ResponseEntity<MessageDTO> sendAttachment(@RequestParam("file") MultipartFile attachmentFile,
                                                     @RequestParam("payload") MessageDTO messageDTO) {
        MessageDTO sentMessage = messageService.sendAttachment(attachmentFile, messageDTO);
        return ResponseEntity.ok(sentMessage);
    }

}

