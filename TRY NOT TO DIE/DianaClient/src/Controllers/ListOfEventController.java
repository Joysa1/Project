package Controllers;

import entity.Event;
import entity.Guest;
import entity.ListOfEvents;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListOfEventController {

        private ObservableList<Event> data = FXCollections.observableArrayList();

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
        private TableView<Event> table;

        @FXML
        private TableColumn<Event, String> columnid;

        @FXML
        private TableColumn<Event, String> name;

        @FXML
        private TableColumn<Event, String> type;

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
            button.setOnMouseClicked(event ->
            {
                initData();

                name.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
                type.setCellValueFactory(new PropertyValueFactory<Event, String>("type"));
                columnid.setCellValueFactory(new PropertyValueFactory<Event,String>("id"));
                table.setItems(data);

            });
        }

        private void initData() {
        ListOfEvents list = new ListOfEvents();
        client.write(list);
        list = (ListOfEvents) client.read();
        for (int i = 0; i < list.getEvents().size(); i++) {
            Event newevent = new Event();
            newevent.setName(list.getEvents().get(i).getName());
            newevent.setId(list.getEvents().get(i).getId());
            newevent.setType(list.getEvents().get(i).getType());
            data.add(newevent);
        }

    }


}
