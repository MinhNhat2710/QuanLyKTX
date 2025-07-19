
# **Group9**

1.  Vũ Huy Kỳ 22010414 Github id: Vuhuyky
    
2.  Đỗ Minh Nhật 22010464 Github id: MinhNhat2710
    

----------
# **Link Demo Youtube**
## 🎬 Video hướng dẫn
[![Xem video demo](https://img.youtube.com/vi/m_0j-hbpx-w/0.jpg)](https://www.youtube.com/watch?v=m_0j-hbpx-w)


   
----------

# **Readme Giới thiệu**

[https://vuhuyky.github.io/Group9_OOP_N02_term3_2025/](https://vuhuyky.github.io/Group9_OOP_N02_term3_2025/)

**Xây dựng ứng dụng quản lý ký túc xá**

----------

# **Yêu cầu 2**

## **1. Tiêu đề**

**Xây dựng ứng dụng quản lý ký túc xá**

----------

## **2. Phân tích đối tượng chính**

-   **Sinh viên (Student):**  
    Quản lý thông tin cá nhân, CCCD, lớp, liên hệ (SĐT).
    
-   **Phòng ký túc xá (DormRoom):**  
    Quản lý mã phòng, loại phòng, số lượng tối đa, số lượng hiện tại, trạng thái phòng.
    
-   **Hợp đồng thuê phòng (RentalContract):**  
    Quản lý mã hợp đồng, sinh viên thuê, phòng, ngày bắt đầu, ngày kết thúc, trạng thái hợp đồng.
    

----------

## **3. Cấu trúc thư mục project**

Theo code trong `src` ở trên

----------

## **4. Các class chính**

-   `Student.java`
    
-   `DormRoom.java`
    
-   `RentalContract.java`
    

----------

## **5. Các class kiểm định (test)**

-   `StudentTest.java`
    
-   `DormRoomTest.java`
    
-   `RentalContractTest.java`
    

----------

# **Yêu cầu 3**

## **Giao diện Java Spring Boot**

### **1. Quản lý phòng ký túc xá**

-   Thêm, sửa, xoá phòng
    
-   Liệt kê thông tin phòng
    
-   Lọc phòng còn trống hoặc đã thuê
    

### **2. Quản lý sinh viên**

-   Thêm, sửa, xoá thông tin sinh viên
    
-   Liệt kê sinh viên
    
-   Lọc sinh viên theo lớp, khoa, trạng thái thuê phòng
    

### **3. Quản lý hợp đồng thuê phòng**

-   Gán sinh viên vào phòng
    
-   Kiểm tra phòng còn chỗ trống
    
-   Quản lý trạng thái thuê phòng của sinh viên
    

----------

### **Lưu trữ dữ liệu**

-   Lưu xuống file nhị phân
    
-   Dùng các `Collection` như `ArrayList`, `Map`, v.v... để lưu dữ liệu trong bộ nhớ
    

----------

### **Nội dung 02: Sơ đồ Class Diagram**

![image](https://github.com/user-attachments/assets/71143f92-4b96-4250-b912-a01a65aeb5b8)

----------

### **Nội dung 03: Sequence Diagram**

-   **Sequence thêm phòng**  
    ![sequence them phong](https://github.com/user-attachments/assets/73e63c83-b0f5-406a-8375-1ccb4821c393)
    
-   **Sequence hợp đồng**  
    ![sequence hopdong](https://github.com/user-attachments/assets/815d8e56-9648-424a-9f67-9c36f2773447)
    
-   **Sequence gán sinh viên**  
    ![sequence gánv](https://github.com/user-attachments/assets/e612bfaa-0a92-427f-9647-443ee1de42eb)
    

----------

## **Giai đoạn phát triển CRUD Java thuần**

### **1. Thực hiện CRUD cho ít nhất 03 đối tượng**
*Ở giai đoạn đầu tiên, nhóm đã xây dựng chức năng CRUD cho 3 đối tượng chính:*

-   Sinh viên (Student)
    
-   Phòng ký túc xá (DormRoom)
    
-   Hợp đồng thuê phòng (RentalContract)
    

### **2. Tái sử dụng code CRUD cho nhiều đối tượng**

-   Để tránh lặp lại code, nhóm đã thiết kế một class CRUD tổng quát sử dụng generic (hoặc interface chung), cho phép thực hiện các thao tác CRUD (thêm, sửa, xóa, tìm kiếm, liệt kê) cho nhiều đối tượng khác nhau chỉ với một lần.
    

### **3. Kiểm thử CRUD**

-   Có class kiểm thử riêng cho từng đối tượng
    
-   ![image](https://github.com/user-attachments/assets/b9e53d7d-fbec-4e03-b86c-7cd660f7656e)  
    ![image](https://github.com/user-attachments/assets/cbbd1796-580a-46c4-8275-71bbe990aee2)
    

> **Lưu ý:**  
> Mã nguồn giai đoạn này được lưu trong nhánh `"Nhat"`  
> Giai đoạn tiếp theo nhóm đã phát triển web bằng Spring Boot

----------

# **Yêu cầu 4**

## **1. Ba phương thức chính**

1.  Hiển thị và lọc danh sách sinh viên
    
2.  Kiểm tra tình trạng phòng ký túc xá
    
3.  Thêm mới hợp đồng thuê phòng cho sinh viên
    

----------

## **2. Phân công và hiện thực phương thức**

### **Đỗ Minh Nhật**

-   **Phương thức:** Hiển thị và lọc danh sách sinh viên
    
-   **Mô tả:**  
    Lọc theo lớp, khoa, trạng thái thuê phòng. Hiển thị trên giao diện web.  
    ![Screenshot 2025-06-28 135822](https://github.com/user-attachments/assets/b9671584-9b1f-4385-a37b-e0a47e9bdc51)  
    ![Screenshot 2025-06-28 135850](https://github.com/user-attachments/assets/4747cd9c-c6c7-485d-b7cc-fa87507b78ef)
    Update vào 28/6/2025

### **Vũ Huy Kỳ**

-   **Phương thức:** Thêm mới hợp đồng thuê phòng cho sinh viên
    
-   **Mô tả:**  
    Thêm sinh viên vào phòng  
    ![Screenshot 2025-06-28 140837](https://github.com/user-attachments/assets/b23aa6a4-7f60-4662-b03a-c5587d8a3fb9)  
    ![Screenshot 2025-06-28 140927](https://github.com/user-attachments/assets/9b53e1ca-937a-4ff1-bfb3-53112fd9b566)
    Update vào 28/6/2025

----------

# **Yêu cầu 5**

## **1. Lưu đồ thuật toán**

![image](https://github.com/user-attachments/assets/5b72f3c3-d2f1-47b0-997e-f9ac496ccb22)

----------

## **2. Chức năng chính của nhóm**

**Chức năng chính:** Quản lý hợp đồng thuê phòng ký túc xá

### **Các bước nhỏ:**

-   Lọc danh sách sinh viên chưa thuê phòng  
    
-   Hiển thị danh sách sinh viên có trạng thái "Chưa thuê" trên giao diện quản lý sinh viên
    
-   Chọn phòng trong quản lý phòng, kiểm tra phòng còn chỗ trống  
    
-   Tạo hợp đồng thuê phòng mới cho sinh viên  

- Chọn sinh viên chưa thuê, chọn phòng còn chỗ, tạo hợp đồng và cập nhật trạng thái phòng, sinh viên

### **Phân chia công việc:**

-   **Đỗ Minh Nhật:** Xây dựng chức năng lọc sinh viên chưa thuê
    
-   **Vũ Huy Kỳ:** Xây dựng chức năng kiểm tra phòng trống, tạo hợp đồng
    
-   **Cả nhóm:** Kết nối các chức năng thành hoàn chỉnh
    

----------

## **3. Mỗi thành viên thực hiện một phương thức nhỏ**

### **Đỗ Minh Nhật:**

**Lọc sinh viên chưa thuê**

-   Hiển thị danh sách sinh viên chưa có hợp đồng thuê phòng còn hiệu lực (trạng thái "Chưa thuê"). Chức năng này nằm trong StudentController, sử dụng bộ lọc trạng thái.
    ![image](https://github.com/user-attachments/assets/2cf7d89b-6c98-49a7-932a-51ff8e8acbba)
   Update 28/6/2025 
### **Vũ Huy Kỳ:**

**Kiểm tra phòng trống và tạo hợp đồng**

-  Khi thêm hợp đồng thuê phòng, kiểm tra phòng còn chỗ trống, sau đó tạo hợp đồng thêm sinh viên và cập nhật trạng thái phòng, sinh viên.
    ![image](https://github.com/user-attachments/assets/ec73b431-6a59-4dbb-a247-a85704b0f256)  
    ![image](https://github.com/user-attachments/assets/346df60c-db81-4339-9a8b-aca60020fe98)
     Update 28/6/2025 

----------

# **Yêu cầu 6**

Đã thêm `try-catch` trong các đoạn code xử lý

----------

# **Yêu cầu 7**

Giao diện cập nhật đã thực hiện ở yêu cầu 5

### **Giao diện chính của nhóm:**

![image](https://github.com/user-attachments/assets/bfcff7fb-f160-466c-81f8-88ff690eb858)

## 💻 Cách chạy bằng GitHub Codespaces (khuyên dùng) 
1.  Truy cập vào repository:  
    👉 [`https://github.com/Vuhuyky/Group9_OOP_N02_term3_2025`](https://github.com/Vuhuyky/Group9_OOP_N02_term3_2025)
    
2.  Nhấn vào nút **`<> Code`** màu xanh lá  
    → Chọn tab **Codespaces**  
    → Nhấn nút **"Create codespace on main"**
    
3.  Chờ GitHub tạo môi trường sẵn sàng (mất khoảng 1 phút)
    
4.  Khi vào Codespaces:
    
    -   Nhìn sang **bên trái**, mở thư mục:  
        `SpringBoot > complete > src > main > java > com > example > servingwebcontent`
        
    -   Click vào file **`ServingWebContentApplication.java`**
        
    -   Ở **góc phải dưới màn hình**, sẽ hiện hộp thoại:  
        **"Do you want to install the recommended 'Extension Pack for Java' extension from Microsoft for the Java language?"**  
        → Nhấn **Install** để cài.
        
-   Sau khi cài extension và quay lại file **`ServingWebContentApplication.java`** , GitHub Codespaces có thể hiển thị thông báo:
    
    > **Help Red Hat improve its extensions by allowing them to collect usage data. Read our privacy statement and learn how to opt out.**  
    > → Chọn **Accept** 
    
-   Nhìn lên góc phải nhấn vào nút **Run**
- Hiển thị **Build tool conflicts are detected in workspace. Which one would you like to use?** ở góc phải phía dưới màn hình nhấn **Use Maven**
    
-   Chờ terminal build và khởi động ứng dụng Spring Boot
    
-  Nó sẽ hiển thị dưới màn hình phải  **Your application running on port 8080 is available. See all forwarded ports**. Nhấn **Open in Browser**

Tài khoản đăng nhập là admin và mật khẩu là 123456
    
    
5.  Truy cập ứng dụng tại đường dẫn hiển thị, ví dụ:  
    👉 `https://<your-codespace-id>.github.dev`
