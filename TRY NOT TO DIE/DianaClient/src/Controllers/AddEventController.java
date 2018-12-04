package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Client;

public class AddEventController {
    Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField name;

    @FXML
    private TextField type;

    @FXML
    private Button button;

    @FXML
    private TextField age;

    @FXML
    private TextField number;

    @FXML
    private ImageView back;

    @FXML
    void initialize() {
        back.setOnMouseClicked(event ->
        {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/menu.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            MenuController controller = loader.getController();
            controller.setClient(client);
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage thisstage = (Stage) back.getScene().getWindow();
            thisstage.hide();

        });
        button.setOnMouseClicked(event -> {
            Event newevent = new Event();
            newevent.setType(type.getText().trim());
            newevent.setNumberofpeople(number.getText().trim());
            newevent.setName(name.getText().trim());
            newevent.setAge(age.getText().trim());
            client.write(newevent);
        });

    }
}
