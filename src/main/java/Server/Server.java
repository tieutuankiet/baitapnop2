package Server;

import javafx.scene.control.Label;
import javafx.application.Application;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private String ip;
    private int port;

    public Server(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

        public void start(Label messageLabel) {
            try (ServerSocket server = new ServerSocket(port)) {
                while (true) {
                    System.out.println("Server đang lắng nghe tại port " + port);
                    Socket socket = server.accept();
                    handleClient(socket);
                }
            } catch (IOException e) {
                System.err.println("Lỗi khởi tạo server socket: " + e.getMessage());
            }
        }
    private void handleClient(Socket socket) {
        System.out.println("Đã chấp nhận kết nối từ client: " + socket.getRemoteSocketAddress());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {
            String dataFromClient;
            while ((dataFromClient = reader.readLine()) != null) {
                System.out.println("Server nhận: " + dataFromClient);
                if (dataFromClient.equalsIgnoreCase("bye")) {
                    System.out.println("Server nhận yêu cầu đóng kết nối từ client.");
                    break;
                }
                String response = processData(dataFromClient);
                writer.println(response);
                writer.println("<END>"); // Báo client biết đã kết thúc gửi dữ liệu.
            }
        } catch (IOException e) {
            System.err.println("Lỗi kết nối từ client: " + e.getMessage());
        }
    }
    private String processData(String input) {
        StringBuilder response = new StringBuilder(input);
        return "Server phản hồi: " + response.reverse().toString();
    }


    public static void main(String[] args) {
        // Khởi động ứng dụng JavaFX
        Application.launch(ServerLauncher.class, args);
    }
}