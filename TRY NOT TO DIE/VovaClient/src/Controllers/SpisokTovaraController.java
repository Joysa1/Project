package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.DeleteTovar;
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

public class SpisokTovaraController {

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
    private TableView<Tovar> table;

    @FXML
    private TableColumn<Tovar, String> columnid;

    @FXML
    private TableColumn<Tovar, String> name;

    @FXML
    private TableColumn<Tovar, String> type;

    @FXML
    private TableColumn<Tovar, String> cena;
    @FXML
    private TableColumn<Tovar, String> kolvo;

    @FXML
    private Button obnovit;
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
            type.setCellValueFactory(new PropertyValueFactory<Tovar, String>("kind"));
            cena.setCellValueFactory(new PropertyValueFactory<Tovar, String>("cena"));
            columnid.setCellValueFactory(new PropertyValueFactory<Tovar,String>("id"));
            kolvo.setCellValueFactory(new PropertyValueFactory<Tovar,String>("kolvo"));
            table.setItems(data);

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
            tovar.setKind(list.getTovars().get(i).getKind());
            tovar.setCena(list.getTovars().get(i).getCena());
            tovar.setKolvo(list.getTovars().get(i).getKolvo());
            data.add(tovar);
        }

    }
}
