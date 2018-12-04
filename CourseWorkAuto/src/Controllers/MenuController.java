package Controllers;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.ListOfPerson;
import entity.ListOfPremiya;
import entity.PersonForTable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
            try
            {
                File file = new File("otchet.txt");

                // Создание файла
                file.createNewFile();
                // Создание объекта FileWriter
                FileWriter writer = new FileWriter(file);
                String lineSeparator = System.getProperty("line.separator");
                writer.write("Фамилия" +" " + "Дата" +" " + "Сумма" +lineSeparator);
                // запись всей строки
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

                    writer.write(newperson.getSurname() +" " + newperson.getDate() +" " + newperson.getSum() +lineSeparator);
                    writer.flush();
                }
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Отчёт!");
            alert.setHeaderText(null);
            alert.setContentText("Отчёт успешно создан!");
            alert.showAndWait();
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
