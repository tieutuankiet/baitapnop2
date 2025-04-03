package Client;

import Form.Login; // Import lớp Login
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Khởi động giao diện đăng nhập
        Login loginForm = new Login();
        loginForm.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}