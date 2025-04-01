package Server;

import javafx.scene.control.Label;
import javafx.application.Application;

public class Server {
    private String ip;
    private int port;

    public Server(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void start(Label messageLabel) {
        // Logic khởi động server ở đây
        try {
            // Giả lập mở cổng
            System.out.println("Server đang chạy tại " + ip + ":" + port);
            // Thêm mã để mở cổng và lắng nghe kết nối thực tế

            // Ví dụ:
            // ServerSocket serverSocket = new ServerSocket(port, 50, InetAddress.getByName(ip));
            // while (true) {
            //     Socket clientSocket = serverSocket.accept();
            //     // Xử lý kết nối
            // }
            messageLabel.setText("Server đã khởi động tại " + ip + ":" + port);
        } catch (Exception e) {
            System.err.println("Lỗi khi khởi động server: " + e.getMessage());
            messageLabel.setText("Lỗi khi khởi động server: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Khởi động ứng dụng JavaFX
        Application.launch(ServerLauncher.class, args);
    }
}