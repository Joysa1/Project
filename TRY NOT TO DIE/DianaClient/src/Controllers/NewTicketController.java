package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.Guest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Client;

public class NewTicketController {
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
    private TextField email;

    @FXML
    private Button button;

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
            Guest guest = new Guest();
            guest.setIduser(client.getAutorizationData().getLogin());
            guest.setName(name.getText().trim());
            guest.setEmail(email.getText().trim());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/setevent.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SetEventController controller = loader.getController();
            controller.setClient(client);
            controller.setGuest(guest);
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage thisstage = (Stage) button.getScene().getWindow();
            thisstage.hide();
        });


    }
}
