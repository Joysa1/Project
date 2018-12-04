package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.Tovar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Client;

public class RegistrTovarController {
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
    private Button signInButton;

    @FXML
    private TextField kind;

    @FXML
    private TextField cena;

    @FXML
    private TextField kolvo;
    @FXML
    private Button back;

    @FXML
    void initialize() {
        back.setOnMouseClicked(event -> {
            MenuController menuController = new MenuController();
            menuController.showWindowMenu(client);
            Stage thisstage = (Stage) back.getScene().getWindow();
            thisstage.hide();
        });

        signInButton.setOnMouseClicked(event -> {
            Tovar tovar = new Tovar();
            if(!cena.getText().isEmpty() && !name.getText().isEmpty() && !kolvo.getText().isEmpty() && !kind.getText().isEmpty()) {
                tovar.setName(name.getText().trim());
                tovar.setCena(cena.getText().trim());
                tovar.setKolvo(kolvo.getText().trim());
                tovar.setKind(kind.getText().trim());
                client.write(tovar);
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
                Stage thisstage = (Stage) signInButton.getScene().getWindow();
                thisstage.hide();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Поля должны быть заполнены!");
                alert.showAndWait();
            }
        });

    }
}
