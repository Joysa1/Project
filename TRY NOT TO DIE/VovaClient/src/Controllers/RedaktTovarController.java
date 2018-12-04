package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.ListOfTovar;
import entity.Tovar;
import entity.UpdateTovar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Client;

public class RedaktTovarController {
    private ObservableList<Tovar> data = FXCollections.observableArrayList();
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
    private Button dobavit;

    @FXML
    private TableView<Tovar> table;

    @FXML
    private TableColumn<Tovar, String> columnid;

    @FXML
    private TableColumn<Tovar, String> name;

    @FXML
    private TableColumn<Tovar, String> type;

    @FXML
    private Button obnovit;
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
        obnovit.setOnMouseClicked(event ->
        {
            initData();

            name.setCellValueFactory(new PropertyValueFactory<Tovar, String>("name"));
            type.setCellValueFactory(new PropertyValueFactory<Tovar, String>("cena"));
            columnid.setCellValueFactory(new PropertyValueFactory<Tovar,String>("id"));

            table.setItems(data);

        });
        dobavit.setOnMouseClicked(event ->
        {
            UpdateTovar tovar = new UpdateTovar();
            Tovar newtovar = new Tovar();
            TableView.TableViewSelectionModel<Tovar> selectionModel = table.getSelectionModel();
            if(!selectionModel.isEmpty()) {
                Tovar selectedItem = selectionModel.getSelectedItem();
                newtovar.setId(selectedItem.getId());
                newtovar.setCena(kolvo.getText());
                tovar.setTovar(newtovar);
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
                Stage thisstage = (Stage) dobavit.getScene().getWindow();
                thisstage.hide();
            }

        });

    }
    public void initData() {
        ListOfTovar list = new ListOfTovar();
        client.write(list);
        list = (ListOfTovar) client.read();
        for (int i = 0; i < list.getTovars().size(); i++) {
            Tovar tovar = new Tovar();
            tovar.setName(list.getTovars().get(i).getName());
            tovar.setId(list.getTovars().get(i).getId());
            tovar.setCena(list.getTovars().get(i).getCena());
            data.add(tovar);
        }

    }
}
