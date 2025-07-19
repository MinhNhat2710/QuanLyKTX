package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.RentalContract;
import com.example.servingwebcontent.model.Student;
import com.example.servingwebcontent.model.DormRoom;
import com.example.servingwebcontent.service.RentalContractService;
import com.example.servingwebcontent.service.StudentService;
import com.example.servingwebcontent.service.DormRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/contracts")
public class RentalContractController {
    @Autowired
    private RentalContractService rentalContractService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private DormRoomService dormRoomService;

    @GetMapping
    public String listContracts(Model model) {
        List<RentalContract> contracts = rentalContractService.getAllContracts();
        model.addAttribute("contracts", contracts);
        return "contracts";
    }

    @GetMapping("/add")
    public String showAddForm(@RequestParam(value = "roomId", required = false) String roomId, Model model) {
        List<DormRoom> rooms = dormRoomService.getAllDormRooms();
        Map<String, Integer> roomAvailable = new HashMap<>();
        for (DormRoom r : rooms) {
            int current = r.getCurrentOccupancy() != null ? r.getCurrentOccupancy() : 0;
            roomAvailable.put(r.getRoomId(), r.getCapacity() - current);
        }
        RentalContract contract = new RentalContract();
        if (roomId != null) {
            DormRoom selectedRoom = dormRoomService.getDormRoomById(roomId).orElse(null);
            contract.setDormRoom(selectedRoom);
        }
        model.addAttribute("contract", contract);
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("rooms", rooms);
        model.addAttribute("roomAvailable", roomAvailable);
        return "contract_form";
    }

    @PostMapping("/add")
    public String addContract(@ModelAttribute RentalContract contract,
                              @RequestParam String roomId,
                              @RequestParam String studentId,
                              Model model) {
        try {
            // Kiểm tra phòng có tồn tại không (Đỗ Minh Nhật)
            DormRoom room = dormRoomService.getDormRoomById(roomId).orElse(null);
            if (room == null) {
                setFormModel(model, contract, "Không tìm thấy phòng!", studentId, roomId);
                return "contract_form";
            }
            // Kiểm tra sinh viên có tồn tại không
            Student student = studentService.getStudentById(studentId).orElse(null);
            if (student == null) {
                setFormModel(model, contract, "Không tìm thấy sinh viên!", studentId, roomId);
                return "contract_form";
            }
            // Kiểm tra ngày kết thúc phải >= ngày bắt đầu
            if (contract.getEndDate() != null && contract.getStartDate() != null
                    && contract.getEndDate().isBefore(contract.getStartDate())) {
                setFormModel(model, contract, "Ngày kết thúc phải bằng hoặc sau ngày bắt đầu!", studentId, roomId);
                return "contract_form";
            }
            // Kiểm tra phòng đã đầy chưa (Vũ Huy Kỳ)
            int current = room.getCurrentOccupancy() != null ? room.getCurrentOccupancy() : 0;
            if (current >= room.getCapacity()) {
                setFormModel(model, contract, "Phòng đã đủ người, không thể thêm hợp đồng mới!", studentId, roomId);
                return "contract_form";
            }
            // Kiểm tra sinh viên đã có hợp đồng còn hiệu lực chưa
            List<RentalContract> activeContracts = rentalContractService.findByStudent_StudentIDAndStatus(studentId, "Còn hiệu lực");
            if (!activeContracts.isEmpty()) {
                setFormModel(model, contract, "Sinh viên này đang ở phòng khác, không thể thêm hợp đồng mới!", studentId, roomId);
                return "contract_form";
            }
            // Đồng bộ dữ liệu
            contract.setDormRoom(room);
            contract.setStudent(student);

            // Cập nhật sinh viên (gán sinh viên vào phòng)
            student.setDormRoomID(room.getRoomId());
            student.setStatus("Đang thuê");
            studentService.saveStudent(student);

            // Cập nhật phòng
            if (room.getCurrentOccupancy() == null) room.setCurrentOccupancy(0);
            room.setCurrentOccupancy(room.getCurrentOccupancy() + 1);
            if (room.getCurrentOccupancy() >= room.getCapacity()) {
                room.setStatus("Đã thuê");
            } else if (room.getCurrentOccupancy() > 0) {
                room.setStatus("Đang thuê");
            } else {
                room.setStatus("Còn trống");
            }
            dormRoomService.saveDormRoom(room);

            rentalContractService.saveContract(contract);
            return "redirect:/contracts";
        } catch (Exception e) {
            setFormModel(model, contract, "Có lỗi xảy ra khi lưu hợp đồng: " + e.getMessage(), studentId, roomId);
            return "contract_form";
        }
    }

    private void setFormModel(Model model, RentalContract contract, String error, String studentId, String roomId) {
        model.addAttribute("error", error);
        model.addAttribute("contract", contract);
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("rooms", dormRoomService.getAllDormRooms());
        Map<String, Integer> roomAvailable = new HashMap<>();
        for (DormRoom r : dormRoomService.getAllDormRooms()) {
            int current = r.getCurrentOccupancy() != null ? r.getCurrentOccupancy() : 0;
            roomAvailable.put(r.getRoomId(), r.getCapacity() - current);
        }
        model.addAttribute("roomAvailable", roomAvailable);
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        RentalContract contract = rentalContractService.getContractById(id).orElse(null);
        List<DormRoom> rooms = dormRoomService.getAllDormRooms();
        Map<String, Integer> roomAvailable = new HashMap<>();
        for (DormRoom r : rooms) {
            int current = r.getCurrentOccupancy() != null ? r.getCurrentOccupancy() : 0;
            roomAvailable.put(r.getRoomId(), r.getCapacity() - current);
        }
        model.addAttribute("contract", contract);
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("rooms", rooms);
        model.addAttribute("roomAvailable", roomAvailable);
        return "contract_form";
    }

    @PostMapping("/edit")
    public String editContract(@ModelAttribute RentalContract contract,
                               @RequestParam String roomId,
                               @RequestParam String studentId,
                               Model model) {
        try {
            DormRoom newRoom = dormRoomService.getDormRoomById(roomId).orElse(null);
            Student student = studentService.getStudentById(studentId).orElse(null);
            List<DormRoom> rooms = dormRoomService.getAllDormRooms();
            Map<String, Integer> roomAvailable = new HashMap<>();
            for (DormRoom r : rooms) {
                int current = r.getCurrentOccupancy() != null ? r.getCurrentOccupancy() : 0;
                roomAvailable.put(r.getRoomId(), r.getCapacity() - current);
            }
            // Kiểm tra ngày kết thúc phải >= ngày bắt đầu
            if (contract.getEndDate() != null && contract.getStartDate() != null
                    && contract.getEndDate().isBefore(contract.getStartDate())) {
                model.addAttribute("error", "Ngày kết thúc phải bằng hoặc sau ngày bắt đầu!");
                model.addAttribute("contract", contract);
                model.addAttribute("students", studentService.getAllStudents());
                model.addAttribute("rooms", rooms);
                model.addAttribute("roomAvailable", roomAvailable);
                return "contract_form";
            }
            if (newRoom == null || student == null) {
                model.addAttribute("error", "Không tìm thấy sinh viên hoặc phòng!");
                model.addAttribute("contract", contract);
                model.addAttribute("students", studentService.getAllStudents());
                model.addAttribute("rooms", rooms);
                model.addAttribute("roomAvailable", roomAvailable);
                return "contract_form";
            }

            // Lấy hợp đồng cũ để kiểm tra phòng cũ
            RentalContract oldContract = rentalContractService.getContractById(contract.getContractId()).orElse(null);
            DormRoom oldRoom = oldContract != null ? oldContract.getDormRoom() : null;

            // Nếu chuyển phòng, cập nhật occupancy phòng cũ và mới
            if (oldRoom != null && !oldRoom.getRoomId().equals(newRoom.getRoomId())) {
                // Giảm số người ở phòng cũ
                if (oldRoom.getCurrentOccupancy() != null && oldRoom.getCurrentOccupancy() > 0) {
                    oldRoom.setCurrentOccupancy(oldRoom.getCurrentOccupancy() - 1);
                    if (oldRoom.getCurrentOccupancy() >= oldRoom.getCapacity()) {
                        oldRoom.setStatus("Đã thuê");
                    } else if (oldRoom.getCurrentOccupancy() > 0) {
                        oldRoom.setStatus("Đang thuê");
                    } else {
                        oldRoom.setStatus("Còn trống");
                    }
                    dormRoomService.saveDormRoom(oldRoom);
                }
                // Tăng số người ở phòng mới
                if (newRoom.getCurrentOccupancy() == null) newRoom.setCurrentOccupancy(0);
                newRoom.setCurrentOccupancy(newRoom.getCurrentOccupancy() + 1);
                if (newRoom.getCurrentOccupancy() >= newRoom.getCapacity()) {
                    newRoom.setStatus("Đã thuê");
                } else if (newRoom.getCurrentOccupancy() > 0) {
                    newRoom.setStatus("Đang thuê");
                } else {
                    newRoom.setStatus("Còn trống");
                }
                dormRoomService.saveDormRoom(newRoom);
            }

            // Cập nhật sinh viên
            student.setDormRoomID(newRoom.getRoomId());
            student.setStatus("Đang thuê");
            studentService.saveStudent(student);

            // Cập nhật hợp đồng
            contract.setDormRoom(newRoom);
            contract.setStudent(student);
            rentalContractService.saveContract(contract);
            return "redirect:/contracts";
        } catch (Exception e) {
            model.addAttribute("error", "Có lỗi xảy ra khi sửa hợp đồng: " + e.getMessage());
            model.addAttribute("contract", contract);
            model.addAttribute("students", studentService.getAllStudents());
            model.addAttribute("rooms", dormRoomService.getAllDormRooms());
            // Tính lại roomAvailable
            Map<String, Integer> roomAvailable = new HashMap<>();
            for (DormRoom r : dormRoomService.getAllDormRooms()) {
                int current = r.getCurrentOccupancy() != null ? r.getCurrentOccupancy() : 0;
                roomAvailable.put(r.getRoomId(), r.getCapacity() - current);
            }
            model.addAttribute("roomAvailable", roomAvailable);
            return "contract_form";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteContract(@PathVariable Integer id, Model model) {
        try {
            RentalContract contract = rentalContractService.getContractById(id).orElse(null);
            if (contract != null) {
                Student student = contract.getStudent();
                DormRoom room = contract.getDormRoom();
                // Cập nhật sinh viên
                if (student != null) {
                    student.setDormRoomID(null);
                    student.setStatus("Chưa thuê");
                    studentService.saveStudent(student);
                }
                // Cập nhật phòng
                if (room != null && room.getCurrentOccupancy() != null && room.getCurrentOccupancy() > 0) {
                    room.setCurrentOccupancy(room.getCurrentOccupancy() - 1);
                    if (room.getCurrentOccupancy() >= room.getCapacity()) {
                        room.setStatus("Đã thuê");
                    } else if (room.getCurrentOccupancy() > 0) {
                        room.setStatus("Đang thuê");
                    } else {
                        room.setStatus("Còn trống");
                    }
                    dormRoomService.saveDormRoom(room);
                }
                rentalContractService.deleteContract(id);
            }
            return "redirect:/contracts";
        } catch (Exception e) {
            // Nếu có lỗi, quay lại danh sách hợp đồng và báo lỗi
            model.addAttribute("error", "Có lỗi xảy ra khi xóa hợp đồng: " + e.getMessage());
            return "contracts";
        }
    }
}