package Controllers;

import entity.RegistrationData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController {
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
    private Button signUpButton;

    @FXML
    private TextField password2;

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
        back.setOnMouseClicked(event ->
        {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/sample.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Controller controller = loader.getController();
            controller.setClient(client);
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage thisstage = (Stage) back.getScene().getWindow();
            thisstage.hide();

        });

     signUpButton.setOnAction(event -> {
         if(password_field.getText().equals(password2.getText())) {
             RegistrationData data = new RegistrationData();
             data.setLogin(login_field.getText().trim());
             data.setPassword(password_field.getText().trim());
             client.write(data);

         }
         else {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Ошибка!");
             alert.setHeaderText(null);
             alert.setContentText("Пароли должны совпадать!");
             alert.showAndWait();
         }

     });
    }
}
