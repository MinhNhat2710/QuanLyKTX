package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.RentalContract;
import com.example.servingwebcontent.repository.RentalContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RentalContractService {
    @Autowired
    private RentalContractRepository rentalContractRepository;

    public List<RentalContract> getAllContracts() {
        try {
            return rentalContractRepository.findAll();
        } catch (Exception e) {
            return Collections.emptyList();
        } finally {
            
        }
    }

    public Optional<RentalContract> getContractById(Integer id) {
        try {
            return rentalContractRepository.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            
        }
    }

    public void saveContract(RentalContract contract) {
        try {
            rentalContractRepository.save(contract);
        } catch (Exception e) {
            
        } finally {
            
        }
    }

    public void deleteContract(Integer id) {
        try {
            rentalContractRepository.deleteById(id);
        } catch (Exception e) {
            
        } finally {
            
        }
    }

    public int countActiveContractsByRoom(String roomId) {
        try {
            return rentalContractRepository.findByDormRoom_RoomIdAndStatus(roomId, "Còn hiệu lực").size();
        } catch (Exception e) {
            return 0;
        } finally {
            
        }
    }

    public List<RentalContract> findByDormRoom_RoomId(String roomId) {
        try {
            return rentalContractRepository.findByDormRoom_RoomId(roomId);
        } catch (Exception e) {
            return Collections.emptyList();
        } finally {
            
        }
    }

    public List<RentalContract> findByStudent_StudentID(String studentId) {
        try {
            return rentalContractRepository.findByStudent_StudentID(studentId);
        } catch (Exception e) {
            return Collections.emptyList();
        } finally {
            
        }
    }

    public List<RentalContract> findByStudent_StudentIDAndStatus(String studentId, String status) {
        try {
            return rentalContractRepository.findByStudent_StudentIDAndStatus(studentId, status);
        } catch (Exception e) {
            return Collections.emptyList();
        } finally {
            
        }
    }
}