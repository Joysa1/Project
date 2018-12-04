package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import entity.*;
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

public class SpisokPokupokController {
    private ObservableList<Pokupka> data = FXCollections.observableArrayList();
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
    private TableView<Pokupka> table;

    @FXML
    private TableColumn<Pokupka, String> columnid;

    @FXML
    private TableColumn<Pokupka, String> name;

    @FXML
    private TableColumn<Pokupka, String> type;

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

            name.setCellValueFactory(new PropertyValueFactory<Pokupka, String>("idtovara"));
            type.setCellValueFactory(new PropertyValueFactory<Pokupka, String>("kolvo"));
            columnid.setCellValueFactory(new PropertyValueFactory<Pokupka,String>("id"));

            table.setItems(data);

        });


    }
    public void initData() {
        ListOfPokupka list = new ListOfPokupka();
        client.write(list);
        list = (ListOfPokupka) client.read();
        for (int i = 0; i < list.getPokupkas().size(); i++) {
            Pokupka pokupka = new Pokupka();
            pokupka.setId(list.getPokupkas().get(i).getId());
            pokupka.setIdtovara(list.getPokupkas().get(i).getIdtovara());
            pokupka.setKolvo(list.getPokupkas().get(i).getKolvo());
            data.add(pokupka);
        }

    }
}
