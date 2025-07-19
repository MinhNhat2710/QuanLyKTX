package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.Student;
import com.example.servingwebcontent.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        try {
            return studentRepository.findAll();
        } catch (Exception e) {
            return Collections.emptyList();
        } finally {

        }
    }

    public Optional<Student> getStudentById(String id) {
        try {
            return studentRepository.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        } finally {

        }
    }

    public Student saveStudent(Student student) {
        try {
            return studentRepository.save(student);
        } catch (Exception e) {
            return null;
        } finally {

        }
    }

    public void deleteStudent(String id) {
        try {
            studentRepository.deleteById(id);
        } catch (Exception e) {
           
        } finally {

        }
    }
}