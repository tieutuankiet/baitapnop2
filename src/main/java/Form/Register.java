package Form;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Register extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Đăng Ký Tài Khoản");

        // Tạo layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setVgap(15);
        grid.setHgap(15);

        // Tạo các thành phần giao diện
        Label usernameLabel = new Label("Địa chỉ Email:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Mật khẩu:");
        PasswordField passwordField = new PasswordField();
        Label fullNameLabel = new Label("Họ và tên:");
        TextField fullNameField = new TextField();
        Label genderLabel = new Label("Giới tính:");
        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleButton = new RadioButton("Nam");
        RadioButton femaleButton = new RadioButton("Nữ");
        maleButton.setToggleGroup(genderGroup);
        femaleButton.setToggleGroup(genderGroup);
        Label dobLabel = new Label("Ngày sinh:");
        DatePicker dobPicker = new DatePicker();
        Button registerButton = new Button("Đăng Ký");
        Label messageLabel = new Label();

        // Thêm các thành phần vào layout
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(fullNameLabel, 0, 2);
        grid.add(fullNameField, 1, 2);
        grid.add(genderLabel, 0, 3);
        grid.add(maleButton, 1, 3);
        grid.add(femaleButton, 1, 4);
        grid.add(dobLabel, 0, 5);
        grid.add(dobPicker, 1, 5);
        grid.add(registerButton, 1, 6);
        grid.add(messageLabel, 0, 7, 2, 1);

        // Thêm sự kiện cho nút đăng ký
        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String fullName = fullNameField.getText();
            String gender = maleButton.isSelected() ? "Nam" : "Nữ";
            String dob = dobPicker.getValue() != null ? dobPicker.getValue().toString() : "";

            // Hash mật khẩu
            String hashedPassword = hashPassword(password);

            // Kiểm tra dữ liệu và hiển thị thông báo
            if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() || dob.isEmpty()) {
                messageLabel.setText("Vui lòng điền tất cả các trường.");
            } else {
                messageLabel.setText("Đăng ký thành công!"); // Ở đây bạn có thể thêm logic lưu trữ
                // In thông tin đăng ký ra console (để kiểm tra)
                System.out.println("Email: " + username);
                System.out.println("Mật khẩu (hash): " + hashedPassword);
                System.out.println("Họ tên: " + fullName);
                System.out.println("Giới tính: " + gender);
                System.out.println("Ngày sinh: " + dob);
            }
        });

        // Tạo và hiển thị scene
        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Hàm hash mật khẩu bằng SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}