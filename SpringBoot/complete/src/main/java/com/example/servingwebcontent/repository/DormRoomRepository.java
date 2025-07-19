package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.model.DormRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DormRoomRepository extends JpaRepository<DormRoom, String> {
    List<DormRoom> findByStatus(String status);
}