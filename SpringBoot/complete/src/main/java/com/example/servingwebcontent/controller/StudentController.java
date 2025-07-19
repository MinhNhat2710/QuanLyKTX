package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.Student;
import com.example.servingwebcontent.model.RentalContract;
import com.example.servingwebcontent.service.StudentService;
import com.example.servingwebcontent.service.RentalContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private RentalContractService rentalContractService;

    // Lọc thông minh theo lớp, khoa, trạng thái
    @GetMapping
    public String listStudents(
            @RequestParam(required = false) String className,
            @RequestParam(required = false) String faculty,
            @RequestParam(required = false) String status,
            Model model) {
        List<Student> students = studentService.getAllStudents().stream()
                .filter(s -> className == null || className.isEmpty() || (s.getClassName() != null && s.getClassName().toLowerCase().contains(className.toLowerCase())))
                .filter(s -> faculty == null || faculty.isEmpty() || (s.getFaculty() != null && s.getFaculty().toLowerCase().contains(faculty.toLowerCase())))
                .filter(s -> status == null || status.isEmpty() || (s.getStatus() != null && s.getStatus().equalsIgnoreCase(status)))
                .collect(Collectors.toList());
        model.addAttribute("students", students);
        System.out.println("get params: " +className);
        // Giữ lại giá trị lọc trên form
        model.addAttribute("className", className);
        model.addAttribute("faculty", faculty);
        model.addAttribute("status", status);
        return "students";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "student_form";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student, Model model) {
        try {
            studentService.saveStudent(student);
            return "redirect:/students";
        } catch (Exception e) {
            model.addAttribute("student", student);
            model.addAttribute("error", "Có lỗi xảy ra khi lưu sinh viên: " + e.getMessage());
            return "student_form";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Student student = studentService.getStudentById(id).orElse(null);
        model.addAttribute("student", student);
        return "student_form";
    }

    @PostMapping("/edit")
    public String editStudent(@ModelAttribute Student student, Model model) {
        try {
            studentService.saveStudent(student);
            return "redirect:/students";
        } catch (Exception e) {
            model.addAttribute("student", student);
            model.addAttribute("error", "Có lỗi xảy ra khi sửa sinh viên: " + e.getMessage());
            return "student_form";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id, Model model) {
        try {
            // Xóa tất cả hợp đồng thuê liên quan trước khi xóa sinh viên
            List<RentalContract> contracts = rentalContractService.findByStudent_StudentID(id);
            for (RentalContract contract : contracts) {
                rentalContractService.deleteContract(contract.getContractId());
            }
            studentService.deleteStudent(id);
            return "redirect:/students";
        } catch (Exception e) {
            model.addAttribute("error", "Có lỗi xảy ra khi xóa sinh viên: " + e.getMessage());
            model.addAttribute("students", studentService.getAllStudents());
            return "students";
        }
    }
}