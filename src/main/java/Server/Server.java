package Server;

import javafx.application.Application;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;

public class Server {
    private String ip;
    private int port;

    public Server(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void start() {
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Server đang lắng nghe tại port " + port);
            while (true) {
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

            String action = reader.readLine(); // Đọc hành động (đăng ký hoặc đăng nhập)
            if ("register".equals(action)) {
                String username = reader.readLine();
                String password = reader.readLine();
                String hashedPassword = hashPassword(password);
                if (registerUser(username, hashedPassword)) {
                    writer.println("Đăng ký thành công!");
                } else {
                    writer.println("Email đã được sử dụng.");
                }
            } else if ("login".equals(action)) {
                String username = reader.readLine();
                String password = reader.readLine();
                String hashedPassword = hashPassword(password);
                if (authenticate(username, hashedPassword)) {
                    writer.println("Đăng nhập thành công!");
                } else {
                    writer.println("Tên đăng nhập hoặc mật khẩu sai.");
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi kết nối từ client: " + e.getMessage());
        }
    }

    private boolean registerUser(String username, String hashedPassword) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            if (isUserExists(username)) {
                return false; // Email đã tồn tại
            }
            writer.write(username + "," + hashedPassword);
            writer.newLine();
            return true; // Đăng ký thành công
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Đăng ký thất bại
        }
    }

    private boolean isUserExists(String username) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean authenticate(String username, String hashedPassword) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(hashedPassword)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Application.launch(ServerLauncher.class, args);
    }
}