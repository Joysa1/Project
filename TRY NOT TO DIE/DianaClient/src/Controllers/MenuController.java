package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Client;

public class MenuController {
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
    private ImageView newticket;

    @FXML
    private ImageView listofevents;

    @FXML
    private ImageView addevent;

    @FXML
    private ImageView analize;

    @FXML
    private ImageView settings;

    @FXML
    private ImageView exit;

    @FXML
    void initialize() {
        newticket.setOnMouseClicked(event ->
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/newticket.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            NewTicketController controller = loader.getController();
            controller.setClient(client);
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage thisstage = (Stage) newticket.getScene().getWindow();
            thisstage.hide();
        });
        listofevents.setOnMouseClicked(event ->
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/listofevent.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ListOfEventController controller = loader.getController();
            controller.setClient(client);
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage thisstage = (Stage) newticket.getScene().getWindow();
            thisstage.hide();
        });

        addevent.setOnMouseClicked(event ->
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/addevent.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AddEventController controller = loader.getController();
            controller.setClient(client);
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage thisstage = (Stage) newticket.getScene().getWindow();
            thisstage.hide();
        });
      analize.setOnMouseClicked(event -> {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("/FXML/analizemenu.fxml"));
          try {
              loader.load();
          } catch (IOException e) {
              e.printStackTrace();
          }
          AnalizeMenuController controller = loader.getController();
          controller.setClient(client);
          Parent root = loader.getRoot();
          Stage stage = new Stage();
          stage.setScene(new Scene(root));
          stage.show();
          Stage thisstage = (Stage) newticket.getScene().getWindow();
          thisstage.hide();
      });
      settings.setOnMouseClicked(event->{
          if(client.getAutorizationData().getLogin().equals("Diana")) {
              FXMLLoader loader = new FXMLLoader();
              loader.setLocation(getClass().getResource("/FXML/settings.fxml"));
              try {
                  loader.load();
              } catch (IOException e) {
                  e.printStackTrace();
              }
              SettingsController controller = loader.getController();
              controller.setClient(client);
              Parent root = loader.getRoot();
              Stage stage = new Stage();
              stage.setScene(new Scene(root));
              stage.show();
              Stage thisstage = (Stage) newticket.getScene().getWindow();
              thisstage.hide();
          }
          else {
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Ошибка!");
              alert.setHeaderText(null);
              alert.setContentText("Ошибка доступа!");
              alert.showAndWait();
          }
      });
      exit.setOnMouseClicked(event -> {
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
          Stage thisstage = (Stage) newticket.getScene().getWindow();
          thisstage.hide();
      });

    }
}
