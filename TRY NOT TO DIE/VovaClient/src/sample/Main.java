package sample;

import Controllers.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Client client = new Client();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/sample.fxml"));
        loader.load();
        Controller controller = loader.getController();
        controller.setClient(client);
        Parent root = loader.getRoot();
        primaryStage.setTitle("Рассчёт премиальных по итогам года");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
