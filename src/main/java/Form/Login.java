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
        grid.add(registerButton, 1, 4);
        grid.add(messageLabel, 0, 5, 2, 1);

        // Thêm sự kiện cho nút đăng nhập
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String ip = ipField.getText();
            String port = portField.getText();

            // Kiểm tra cổng hợp lệ
            if (!isValidPort(port)) {
                messageLabel.setText("Cổng không hợp lệ. Vui lòng nhập từ 1024 đến 65535.");
                return;
            }

            // Khởi động client để đăng nhập
            Client client = new Client(ip, Integer.parseInt(port));
            client.login(username, password, response -> {
                messageLabel.setText(response); // Hiển thị phản hồi từ server

                // Nếu đăng nhập thành công, chuyển sang màn hình chính
                if ("Đăng nhập thành công!".equals(response)) {
                    primaryStage.close(); // Đóng cửa sổ đăng nhập
                    MainAppScreen mainApp = new MainAppScreen(); // Tạo đối tượng màn hình chính
                    try {
                        mainApp.start(new Stage()); // Mở cửa sổ chính
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        });

        // Thêm sự kiện cho nút đăng ký
        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String ip = ipField.getText();
            String port = portField.getText();

            // Kiểm tra cổng hợp lệ
            if (!isValidPort(port)) {
                messageLabel.setText("Cổng không hợp lệ. Vui lòng nhập từ 1024 đến 65535.");
                return;
            }

            // Khởi động client để đăng ký
            Client client = new Client(ip, Integer.parseInt(port));
            String response = client.register(username, password);
            messageLabel.setText(response); // Hiển thị phản hồi từ server
        });

        // Tạo và hiển thị scene
        Scene scene = new Scene(grid, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Hàm kiểm tra cổng hợp lệ
    private boolean isValidPort(String port) {
        try {
            int portNumber = Integer.parseInt(port);
            return portNumber >= 1024 && portNumber <= 65535;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}