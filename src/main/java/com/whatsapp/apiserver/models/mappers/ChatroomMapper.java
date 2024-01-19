package com.whatsapp.apiserver.models.mappers;

import com.whatsapp.apiserver.models.dto.ChatroomDTO;
import com.whatsapp.apiserver.models.entities.Chatroom;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChatroomMapper {

    ChatroomMapper CHATROOM_MAPPER = Mappers.getMapper(ChatroomMapper.class);

    ChatroomDTO toChatroomDto(Chatroom chatroom);
}
