package Controllers;

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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeletePersonController {

        private ObservableList<Person> data = FXCollections.observableArrayList();



        ListOfPerson list;

        public ListOfPerson getList() {
            return list;
        }

        public void setList(ListOfPerson list) {
            this.list = list;
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
                    PersonID  personID = new PersonID();
                    personID.setPersonid(selectedperson.getIdperson());
                    client.write(personID);
                }

            });
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
                System.out.println( data.get(i).getName());
            }

        }

    }


