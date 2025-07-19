package com.example.servingwebcontent.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dorm_room")
public class DormRoom {
    @Id
    @Column(name = "room_id")
    private String roomId;

    @Column(name = "room_name")
    private String roomName;

    private int capacity;

    @Column(name = "current_occupancy")
    private Integer currentOccupancy;

    private String type;

    // Sửa kiểu dữ liệu price thành Long để nhập số nguyên VNĐ
    private Long price;

    private String status; // "Còn trống", "Đã thuê", ...

    public DormRoom() {}

    // Getters & Setters
    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public Integer getCurrentOccupancy() { return currentOccupancy; }
    public void setCurrentOccupancy(Integer currentOccupancy) { this.currentOccupancy = currentOccupancy; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Long getPrice() { return price; }
    public void setPrice(Long price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}