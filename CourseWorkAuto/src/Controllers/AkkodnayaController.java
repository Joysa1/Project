package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.Akkordnaya;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Client;

public class AkkodnayaController {
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
    private TextField prirabotok;

    @FXML
    private TextField kaefuch;

    @FXML
    private TextField tarifstavka;

    @FXML
    private TextField time;

    @FXML
    private Button button;

    @FXML
    private ImageView back;

    @FXML
    void initialize() {
        button.setOnMouseClicked(event ->
        {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/listofperson.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ListOfPersonController controller = loader.getController();
            controller.setClient(client);
            controller.setTypeofpremiya(2);
            Akkordnaya premiya = new Akkordnaya();
            premiya.setKaefuch(Double.parseDouble(kaefuch.getText()));
            premiya.setPrirabotok(Double.parseDouble(prirabotok.getText()));
            premiya.setTarifstavka(Double.parseDouble(tarifstavka.getText()));
            premiya.setTime(Integer.parseInt(time.getText()));
            controller.setPremiya(premiya);
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage thisstage = (Stage) button.getScene().getWindow();
            thisstage.hide();
        });
        back.setOnMouseClicked(event ->
        {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/newpremiya.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            TypeController controller = loader.getController();
            controller.setClient(client);
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage thisstage = (Stage) back.getScene().getWindow();
            thisstage.hide();

        });

    }
}
