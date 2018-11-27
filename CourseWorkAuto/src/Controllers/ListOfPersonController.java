package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Client;

public class ListOfPersonController {
    private ObservableList<Person> data = FXCollections.observableArrayList();
    int typeofpremiya;
     ListOfPerson list;

    public ListOfPerson getList() {
        return list;
    }

    public void setList(ListOfPerson list) {
        this.list = list;
    }

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
    private Button button_choose;
    @FXML
    private TableColumn<Person, String> columnid;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button;

    @FXML
    private TableView<Person> table;

    @FXML
    private TableColumn<Person, String> surname;

    @FXML
    private TableColumn<Person, String> name;

    @FXML
    private TableColumn<Person, String> numbetr;
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
            initData();

            name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
            surname.setCellValueFactory(new PropertyValueFactory<Person, String>("surname"));
            numbetr.setCellValueFactory(new PropertyValueFactory<Person, String>("numotdel"));
            columnid.setCellValueFactory(new PropertyValueFactory<Person,String>("idperson"));
            table.setItems(data);

        });
        button_choose.setOnMouseClicked(event ->
        {
            TableView.TableViewSelectionModel<Person> selectionModel = table.getSelectionModel();
            if(!selectionModel.isEmpty()) {
                Person selectedperson = selectionModel.getSelectedItem();
                Premiya newpremiya = new Premiya();
                newpremiya.setPersonId(selectedperson.getIdperson());
                newpremiya.setPremiya(premiya);
                newpremiya.setTypeOfPremiya(typeofpremiya);
                client.write(newpremiya);
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
            }

        });
    }
    private void initData() {
        list = new ListOfPerson();
        client.write(list);
        list = (ListOfPerson) client.read();
        for (int i = 0; i < list.getListOfPerson().size(); i++) {
            Person newperson = new Person();
            newperson.setName(list.getListOfPerson().get(i).getName());
            newperson.setSurname(list.getListOfPerson().get(i).getSurname());
            newperson.setNumotdel(list.getListOfPerson().get(i).getNumotdel());
            newperson.setIdperson(list.getListOfPerson().get(i).getIdperson());
            data.add(newperson);

          //  System.out.println( data.get(i).getName());
        }

    }

}
