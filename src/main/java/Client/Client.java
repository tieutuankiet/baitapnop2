package Client;

import java.io.*;
import java.net.Socket;

public class Client {

    private String host;
    private int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    // Phương thức đăng ký
    public String register(String username, String password) {
        try (Socket socket = new Socket(host, port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            writer.println("register");
            writer.println(username);
            writer.println(password);

            return reader.readLine(); // Nhận phản hồi từ server
        } catch (IOException e) {
            return "Lỗi kết nối đến server: " + e.getMessage();
        }
    }

    // Phương thức đăng nhập
    public void login(String username, String password, LoginCallback callback) {
        try (Socket socket = new Socket(host, port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            writer.println("login");
            writer.println(username);
            writer.println(password);

            String response = reader.readLine(); // Nhận phản hồi từ server
            callback.onLoginResponse(response); // Gọi callback với phản hồi
        } catch (IOException e) {
            callback.onLoginResponse("Lỗi kết nối đến server: " + e.getMessage());
        }
    }

    // Callback interface để xử lý phản hồi đăng nhập
    public interface LoginCallback {
        void onLoginResponse(String response);
    }
}