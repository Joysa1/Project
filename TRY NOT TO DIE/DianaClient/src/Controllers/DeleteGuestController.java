package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Client;

public class DeleteGuestController {
    private ObservableList<Guest> data = FXCollections.observableArrayList();
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
    private Button button;

    @FXML
    private TableView<Guest> table;

    @FXML
    private TableColumn<Guest, String> columnid;

    @FXML
    private TableColumn<Guest, String> name;

    @FXML
    private TableColumn<Guest, String> type;

    @FXML
    private Button button_choose;


    @FXML
    private ImageView back;

    @FXML
    void initialize() {
        back.setOnMouseClicked(event ->
        {

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
            Stage thisstage = (Stage) back.getScene().getWindow();
            thisstage.hide();

        });
        button.setOnMouseClicked(event ->
        {
            initData();

            name.setCellValueFactory(new PropertyValueFactory<Guest, String>("name"));
            type.setCellValueFactory(new PropertyValueFactory<Guest, String>("idevent"));
            columnid.setCellValueFactory(new PropertyValueFactory<Guest,String>("id"));

            table.setItems(data);

        });
        button_choose.setOnMouseClicked(event ->
        {
            TableView.TableViewSelectionModel<Guest> selectionModel = table.getSelectionModel();
            if(!selectionModel.isEmpty()) {
                Guest selectedguest = selectionModel.getSelectedItem();
                DeleteGuest deleteGuest = new DeleteGuest();
                deleteGuest.setGuest(selectedguest);
                client.write(deleteGuest);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Удаление!");
                alert.setHeaderText(null);
                alert.setContentText("Вы успешно удалили!");
                alert.showAndWait();
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
                Stage thisstage = (Stage) button_choose.getScene().getWindow();
                thisstage.hide();
            }

        });

    }

    public void initData() {
        ListOfGuest list = new ListOfGuest();
        client.write(list);
        list = (ListOfGuest) client.read();
        for (int i = 0; i < list.getGuests().size(); i++) {
            Guest guest = new Guest();
            guest.setName(list.getGuests().get(i).getName());
            guest.setId(list.getGuests().get(i).getId());
            guest.setIdevent(String.valueOf(list.getGuests().get(i).getIdevent()));
            data.add(guest);
        }

    }
}
