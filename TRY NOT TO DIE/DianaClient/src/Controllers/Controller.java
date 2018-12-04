package Controllers;

import entity.AutorizationData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
   private Client client;

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
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button signInButton;

    @FXML
    private Button singUpButton;

    @FXML
    void initialize() {

            signInButton.setOnAction(event -> {
                if(!login_field.getText().trim().equals("") && !password_field.getText().trim().equals("")) {
                    AutorizationData user = new AutorizationData();
                    user.setLogin(login_field.getText().trim());
                    user.setPassword(password_field.getText().trim());
                    client.write(user);
                    if (client.readAnswer()) {
                        Stage thisstage = (Stage) signInButton.getScene().getWindow();
                        thisstage.hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/FXML/menu.fxml"));
                        try {
                            loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        MenuController controller = loader.getController();
                        client.setAutorizationData(user);
                        controller.setClient(client);
                        Parent root = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.showAndWait();

                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Ошибка!");
                        alert.setHeaderText(null);
                        alert.setContentText("Введены неверные данные!");
                        alert.showAndWait();
                    }
                }
                });
            singUpButton.setOnAction(event -> {
               singUpButton.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/FXML/registration.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                SignUpController controller = loader.getController();
                controller.setClient(client);
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            });

    }
}
