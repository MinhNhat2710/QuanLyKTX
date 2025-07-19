package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.DormRoom;
import com.example.servingwebcontent.repository.DormRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DormRoomService {
    @Autowired
    private DormRoomRepository dormRoomRepository;

    @Autowired
    private RentalContractService rentalContractService; // Thêm dòng này

    public List<DormRoom> getAllDormRooms() {
        try {
            List<DormRoom> rooms = dormRoomRepository.findAll();
            for (DormRoom room : rooms) {
                int count = rentalContractService.countActiveContractsByRoom(room.getRoomId());
                room.setCurrentOccupancy(count);
            }
            return rooms;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Optional<DormRoom> getDormRoomById(String id) {
        try {
            Optional<DormRoom> roomOpt = dormRoomRepository.findById(id);
            roomOpt.ifPresent(room -> {
                int count = rentalContractService.countActiveContractsByRoom(room.getRoomId());
                room.setCurrentOccupancy(count);
            });
            return roomOpt;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void saveDormRoom(DormRoom dormRoom) {
        try {
            dormRoomRepository.save(dormRoom);
        } catch (Exception e) {
            // log error nếu cần
        }
    }

    public void deleteDormRoom(String id) {
        try {
            dormRoomRepository.deleteById(id);
        } catch (Exception e) {
            // log error nếu cần
        }
    }

    public List<DormRoom> getDormRoomsByStatus(String status) {
        try {
            List<DormRoom> rooms = dormRoomRepository.findByStatus(status);
            for (DormRoom room : rooms) {
                int count = rentalContractService.countActiveContractsByRoom(room.getRoomId());
                room.setCurrentOccupancy(count);
            }
            return rooms;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public boolean isRoomExist(String roomId) {
        try {
            return dormRoomRepository.existsById(roomId);
        } catch (Exception e) {
            return false;
        }
    }
}