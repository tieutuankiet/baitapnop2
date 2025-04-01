package Form;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainAppScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chat Application");

        // Thanh bên trái
        VBox leftPane = new VBox();
        leftPane.setPadding(new Insets(10));
        leftPane.setSpacing(10);
        leftPane.setStyle("-fx-background-color: #2C3E50;"); // Màu nền của thanh bên trái

        // Thanh tìm kiếm
        HBox searchBox = new HBox();
        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        searchField.setPrefWidth(150);
        Button searchButton = new Button("🔍");
        searchBox.getChildren().addAll(searchField, searchButton);
        leftPane.getChildren().add(searchBox);

        // Các nút trong thanh bên trái
        Button homeButton = new Button("Tạo nhóm");
        Button settingsButton = new Button("Settings");
        homeButton.setStyle("-fx-background-color: #34495E; -fx-text-fill: white; -fx-pref-width: 120;");
        settingsButton.setStyle("-fx-background-color: #34495E; -fx-text-fill: white; -fx-pref-width: 120;");
        leftPane.getChildren().addAll(homeButton, settingsButton);

        // Khu vực bên phải với nền sáng
        BorderPane mainPane = new BorderPane();
        mainPane.setLeft(leftPane);
        mainPane.setStyle("-fx-background-color: #ECF0F1;"); // Nền khu vực chat

        // Khu vực chat
        VBox chatArea = new VBox();
        chatArea.setPadding(new Insets(10));
        chatArea.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1;"); // Nền trắng cho khu vực chat

        // Thông tin người dùng
        HBox userInfo = new HBox();
        userInfo.setStyle("-fx-background-color: gray; -fx-border-color: white;");
        Label userName = new Label("Mo Salah");
        userName.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        Label statusLabel = new Label("Active");
        statusLabel.setStyle("-fx-text-fill: green;");
        userInfo.getChildren().addAll(userName, statusLabel);
        chatArea.getChildren().add(userInfo); // Thêm thông tin người dùng vào chatArea

        // Khu vực nhập tin nhắn
        HBox messageBox = new HBox();
        messageBox.setPadding(new Insets(10));// Thêm padding
        messageBox.setSpacing(10);
        messageBox.setStyle("-fx-background-color: gray; -fx-border-color: black; -fx-border-width: 1; -fx-alignment: center-right;"); // Nền trắng và viền đen

        // Thêm biểu tượng cảm xúc
        Button emojiButton = new Button("😊");
        emojiButton.setPrefWidth(40); // Đặt chiều rộng cho nút biểu tượng cảm xúc
        TextField messageField = new TextField();
        messageField.setPromptText("Type a message...");
        messageField.setPrefWidth(400); // Kích thước TextField
        Button sendButton = new Button("Send");
        sendButton.setStyle("-fx-background-color: #3498DB; -fx-text-fill: white;");

        // Thêm các thành phần vào messageBox
        messageBox.getChildren().addAll(emojiButton, messageField, sendButton);

        // Đặt chatArea vào giữa và messageBox vào dưới cùng
        mainPane.setCenter(chatArea);
        mainPane.setBottom(messageBox); // Đặt messageBox xuống dưới cùng

        // Thiết lập Scene
        Scene scene = new Scene(mainPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}