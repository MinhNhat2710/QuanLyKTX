package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.model.RentalContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalContractRepository extends JpaRepository<RentalContract, Integer> {
    List<RentalContract> findByDormRoom_RoomIdAndStatus(String roomId, String status);
    List<RentalContract> findByStudent_StudentIDAndStatus(String studentId, String status);
    List<RentalContract> findByDormRoom_RoomId(String roomId);
    List<RentalContract> findByStudent_StudentID(String studentId);
    // XÓA dòng trùng lặp bên dưới (chỉ giữ 1 dòng này):
    // List<RentalContract> findByStudent_StudentIDAndStatus(String studentId, String status);
}