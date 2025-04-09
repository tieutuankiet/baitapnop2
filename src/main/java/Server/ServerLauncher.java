package Server;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ServerLauncher extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Khởi Động Server");

        // Tạo layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setVgap(15);
        grid.setHgap(15);

        // Tạo các thành phần giao diện
        Label ipLabel = new Label("Địa chỉ IP:");
        TextField ipField = new TextField("127.0.0.1");
        Label portLabel = new Label("Cổng:");
        TextField portField = new TextField("8080");
        Button startButton = new Button("Khởi Động Server");
        Label messageLabel = new Label();

        // Thêm các thành phần vào layout
        grid.add(ipLabel, 0, 0);
        grid.add(ipField, 1, 0);
        grid.add(portLabel, 0, 1);
        grid.add(portField, 1, 1);
        grid.add(startButton, 1, 2);
        grid.add(messageLabel, 0, 3, 2, 1);

        // Thêm sự kiện cho nút khởi động server
        startButton.setOnAction(e -> {
            String ip = ipField.getText();
            String port = portField.getText();

            // Kiểm tra định dạng cổng
            if (!isValidPort(port)) {
                messageLabel.setText("Cổng không hợp lệ. Vui lòng nhập từ 1024 đến 65535.");
                return;
            }

            // Khởi động server
            Server server = new Server(ip, Integer.parseInt(port));
            new Thread(() -> server.start()).start(); // Chạy server trong một luồng riêng
            messageLabel.setText("Server đang chạy tại " + ip + ":" + port);
        });

        // Tạo và hiển thị scene
        Scene scene = new Scene(grid, 400, 200);
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