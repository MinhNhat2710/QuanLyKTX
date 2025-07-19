package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
    // Có thể bổ sung các phương thức tìm kiếm, lọc nếu cần
}