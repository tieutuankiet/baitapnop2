module com.example.baitapnop2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop; // Nếu bạn sử dụng FXML
    exports Form; // Xuất gói chứa lớp Login
    exports Server;
    exports Client;
}