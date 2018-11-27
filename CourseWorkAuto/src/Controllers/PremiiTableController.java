package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.ListOfPerson;
import entity.ListOfPremiya;
import entity.Person;
import entity.PersonForTable;
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

public class PremiiTableController {

    private ObservableList<PersonForTable> data = FXCollections.observableArrayList();
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
    private Button button;

    @FXML
    private TableView<PersonForTable> table;

    @FXML
    private TableColumn<PersonForTable, String> surname;

    @FXML
    private TableColumn<PersonForTable, String> sum;

    @FXML
    private TableColumn<PersonForTable, String> date;
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
        button.setOnMouseClicked(event ->
        {
            data.clear();
            initData();
            sum.setCellValueFactory(new PropertyValueFactory<PersonForTable, String>("sum"));
            surname.setCellValueFactory(new PropertyValueFactory<PersonForTable, String>("surname"));
            date.setCellValueFactory(new PropertyValueFactory<PersonForTable, String>("date"));
            table.setItems(data);
        });

    }
    private void initData() {
        ListOfPerson list = new ListOfPerson();
        client.write(list);
        list = (ListOfPerson) client.read();
        ListOfPremiya listOfPremiya = new ListOfPremiya();
        client.write(listOfPremiya);
        listOfPremiya = (ListOfPremiya) client.read();
        for (int i = 0; i < listOfPremiya.getListOfPremiya().size(); i++) {
            PersonForTable newperson = new PersonForTable();
            for(int j=0; j<list.getListOfPerson().size(); j++) {

                if(listOfPremiya.getListOfPremiya().get(i).getIdemployee().equals(list.getListOfPerson().get(j).getIdperson())) {
                    newperson.setSurname(list.getListOfPerson().get(j).getSurname());
                    break;
                }
            }
            newperson.setDate(listOfPremiya.getListOfPremiya().get(i).getDate());
            newperson.setSum(listOfPremiya.getListOfPremiya().get(i).getSum());
            data.add(newperson);

        }

    }
}
