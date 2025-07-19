package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.DormRoom;
import com.example.servingwebcontent.model.RentalContract;
import com.example.servingwebcontent.service.DormRoomService;
import com.example.servingwebcontent.service.RentalContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dormrooms")
public class DormRoomController {
    @Autowired
    private DormRoomService dormRoomService;
    @Autowired
    private RentalContractService rentalContractService;

    @GetMapping
    public String listDormRooms(@RequestParam(required = false) String status, Model model) {
        List<DormRoom> dormRooms;
        if (status != null && !status.isEmpty()) {
            dormRooms = dormRoomService.getDormRoomsByStatus(status);
        } else {
            dormRooms = dormRoomService.getAllDormRooms();
        }
        model.addAttribute("dormrooms", dormRooms);
        model.addAttribute("status", status);
        return "dormrooms";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("dormRoom", new DormRoom());
        return "dormroom_form";
    }

    @PostMapping("/add")
    public String addDormRoom(@ModelAttribute DormRoom dormRoom, Model model) {
        try {
            dormRoomService.saveDormRoom(dormRoom);
            return "redirect:/dormrooms";
        } catch (Exception e) {
            model.addAttribute("dormRoom", dormRoom);
            model.addAttribute("error", "Có lỗi xảy ra khi lưu phòng: " + e.getMessage());
            return "dormroom_form";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        DormRoom dormRoom = dormRoomService.getDormRoomById(id).orElse(null);
        model.addAttribute("dormRoom", dormRoom);
        return "dormroom_form";
    }

    @PostMapping("/edit")
    public String editDormRoom(@ModelAttribute DormRoom dormRoom, Model model) {
        try {
            dormRoomService.saveDormRoom(dormRoom);
            return "redirect:/dormrooms";
        } catch (Exception e) {
            model.addAttribute("dormRoom", dormRoom);
            model.addAttribute("error", "Có lỗi xảy ra khi sửa phòng: " + e.getMessage());
            return "dormroom_form";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteDormRoom(@PathVariable String id, Model model) {
        try {
            // Xóa tất cả hợp đồng thuê liên quan trước khi xóa phòng
            List<RentalContract> contracts = rentalContractService.findByDormRoom_RoomId(id);
            for (RentalContract contract : contracts) {
                rentalContractService.deleteContract(contract.getContractId());
            }
            dormRoomService.deleteDormRoom(id);
            return "redirect:/dormrooms";
        } catch (Exception e) {
            model.addAttribute("error", "Có lỗi xảy ra khi xóa phòng: " + e.getMessage());
            model.addAttribute("dormrooms", dormRoomService.getAllDormRooms());
            return "dormrooms";
        }
    }

    @GetMapping("/check-room")
    public String showCheckRoomForm() {
        return "check_room_form";
    }

    @PostMapping("/check-room")
    public String checkRoomExist(@RequestParam String roomId, Model model) {
        try {
            boolean exists = dormRoomService.isRoomExist(roomId);
            if (!exists) {
                model.addAttribute("message", "Phòng " + roomId + " không tồn tại!");
            } else {
                DormRoom room = dormRoomService.getDormRoomById(roomId).orElse(null);
                int current = (room != null && room.getCurrentOccupancy() != null) ? room.getCurrentOccupancy() : 0;
                if (room != null && current >= room.getCapacity()) {
                    model.addAttribute("message", "Phòng " + roomId + " đã đầy!");
                } else {
                    model.addAttribute("message", "Phòng " + roomId + " còn chỗ, bạn có thể thêm hợp đồng cho phòng này.");
                    model.addAttribute("roomId", roomId);
                }
            }
        } catch (Exception e) {
            model.addAttribute("message", "Có lỗi xảy ra khi kiểm tra phòng: " + e.getMessage());
        }
        return "check_room_result";
    }
}