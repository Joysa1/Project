package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.Person;
import entity.Premii;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Client;

public class PersonController {
    int typeofpremiya;

    public int getTypeofpremiya() {
        return typeofpremiya;
    }

    public void setTypeofpremiya(int typeofpremiya) {
        this.typeofpremiya = typeofpremiya;
    }
    Premii premiya;

    public Premii getPremiya() {
        return premiya;
    }

    public void setPremiya(Premii premiya) {
        this.premiya = premiya;
    }

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
    private TextField surname;

    @FXML
    private Button button;

    @FXML
    private TextField name;

    @FXML
    private TextField otchestvo;

    @FXML
    private TextField numotdel;
    @FXML
    private ImageView back;

    @FXML
    void initialize() {
        back.setOnMouseClicked(event ->
        {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/databasemenu.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            DataBaseMenuController controller = loader.getController();
            controller.setClient(client);
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage thisstage = (Stage) back.getScene().getWindow();
            thisstage.hide();

        });
        button.setOnMouseClicked(event -> {
            Person person = new Person();
            person.setName(name.getText());
            person.setNumotdel(numotdel.getText());
            person.setOtchestvo(otchestvo.getText());
            person.setSurname(surname.getText());
            client.write(person);
        });


    }
}
