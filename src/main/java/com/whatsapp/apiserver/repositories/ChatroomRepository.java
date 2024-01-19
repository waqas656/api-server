package com.whatsapp.apiserver.repositories;

import com.whatsapp.apiserver.models.entities.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
    Optional<Chatroom> findChatroomById(Long id);
}

