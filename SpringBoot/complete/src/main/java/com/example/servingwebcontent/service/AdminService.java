package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.Admin;
import com.example.servingwebcontent.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin login(String username, String password) {
        try {
            Admin admin = adminRepository.findByUsername(username);
            if (admin != null && admin.getPassword().equals(password) && "ACTIVE".equalsIgnoreCase(admin.getStatus())) {
                return admin;
            }
            return null;
        } catch (Exception e) {
            return null;
        } finally {
            
        }
    }
}