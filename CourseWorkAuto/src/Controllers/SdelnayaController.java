package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.Sdelnaya;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Client;

public class SdelnayaController {
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
    private TextField zarplata;

    @FXML
    private TextField procentzaplan;

    @FXML
    private TextField procentzapereplan;

    @FXML
    private TextField procentpereplan;

    @FXML
    private Button button;
    @FXML
    private ImageView back;

    @FXML
    void initialize() {
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
            controller.setTypeofpremiya(1);
            Sdelnaya premiya = new Sdelnaya();
            premiya.setProcentpereplan(Double.parseDouble(procentpereplan.getText()));
            premiya.setProcentzapereplan(Double.parseDouble(procentzapereplan.getText()));
            premiya.setProcentzaplan(Double.parseDouble(procentzaplan.getText()));
            premiya.setZarplata(Double.parseDouble(zarplata.getText()));
            controller.setPremiya(premiya);
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage thisstage = (Stage) button.getScene().getWindow();
            thisstage.hide();
        });


    }
}
