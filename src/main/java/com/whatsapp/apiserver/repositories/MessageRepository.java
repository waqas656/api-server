package com.whatsapp.apiserver.repositories;

import com.whatsapp.apiserver.models.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}

