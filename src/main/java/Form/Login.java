package Form;

import Client.Client; // Import lớp Client
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Login extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Đăng Nhập");

        // Tạo layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setVgap(15);
        grid.setHgap(15);

        // Tạo các thành phần giao diện
        Label usernameLabel = new Label("Tên đăng nhập:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Mật khẩu:");
        PasswordField passwordField = new PasswordField();
        Label ipLabel = new Label("Địa chỉ IP:");
        TextField ipField = new TextField("127.0.0.1"); // Giá trị mặc định
        Label portLabel = new Label("Cổng:");
        TextField portField = new TextField("8080"); // Giá trị mặc định
        Button loginButton = new Button("Đăng Nhập");
        Button registerButton = new Button("Đăng Ký");
        Label messageLabel = new Label();


        // Thêm các thành phần vào layout
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(ipLabel, 0, 2);
        grid.add(ipField, 1, 2);
        grid.add(portLabel, 0, 3);
        grid.add(portField, 1, 3);
        grid.add(loginButton, 0, 4);
        grid.add(messageLabel, 0, 5, 2, 1);
        grid.add(registerButton, 1, 4);

        // Thêm sự kiện cho nút đăng nhập
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String ip = ipField.getText();
            String port = portField.getText();

            // Kiểm tra đăng nhập (ví dụ đơn giản)
            if (username.equals("admin") && password.equals("1234")) {
                messageLabel.setText("Đăng nhập thành công!");
                primaryStage.close(); // Đóng cửa sổ đăng nhập
                startClient(ip, Integer.parseInt(port)); // Khởi động client
            } else {
                messageLabel.setText("Tên đăng nhập hoặc mật khẩu sai.");
            }
        });

        // Tạo và hiển thị scene
        Scene scene = new Scene(grid, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Phương thức khởi động client
    private void startClient(String ip, int port) {
        Client client = new Client(ip, port);
        client.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}