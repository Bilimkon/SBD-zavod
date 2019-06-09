package sample.components.sell;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {
    public static boolean isStageAlive = false;
    public static boolean is_clock_alive = true;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/Login.fxml"));
        primaryStage.setTitle("SBD boshqaruv tizimi.");
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(root, 1080, 720));
        primaryStage.setMinHeight(720);
        primaryStage.setMinWidth(1090);
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("style/Images/SBD-logo.png")));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e-> Platform.exit());


        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                is_clock_alive = false;
                isStageAlive =false;
                Platform.exit();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
