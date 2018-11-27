package Controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Client;

public class MenuController {
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
    private ImageView exit;

    @FXML
    private ImageView database;

    @FXML
    private ImageView newpremiya;

    @FXML
    private ImageView analyze;

    @FXML
    private ImageView aboutcompany;

    @FXML
    private ImageView faq;


    @FXML
    void initialize() {
       newpremiya.setOnMouseClicked(event ->
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
           Stage thisstage = (Stage) newpremiya.getScene().getWindow();
           thisstage.hide();

       });
        analyze.setOnMouseClicked(event ->
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/grafics.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            GraficsController controller = loader.getController();
            controller.setClient(client);
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage thisstage = (Stage) analyze.getScene().getWindow();
            thisstage.hide();
        });
        database.setOnMouseClicked(event ->
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
            Stage thisstage = (Stage) database.getScene().getWindow();
            thisstage.hide();
        });
        aboutcompany.setOnMouseClicked(event ->
        {
            System.out.println("вы нажали о компании");
        });
        faq.setOnMouseClicked(event ->
        {
            System.out.println("вы нажали FAQ");
        });
        exit.setOnMouseClicked(event ->
        {
            System.out.println("вы нажали EXIT");
        });

    }
}
