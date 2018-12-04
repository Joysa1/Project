package Controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.ListOfEvents;
import entity.ListOfGuest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Client;

public class AnalizeMenuController {
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
    private Button button1;

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
        button.setOnMouseClicked(event -> {
            try {
                File file = new File("otchet.txt");

                // Создание файла
                file.createNewFile();
                // Создание объекта FileWriter
                FileWriter writer = new FileWriter(file);
                String lineSeparator = System.getProperty("line.separator");
                writer.write("Имя"+" "+ "Email"+" "+"ID event"+" "+"Username"
                        +lineSeparator);
                // запись всей строки
                ListOfGuest list = new ListOfGuest();
                client.write(list);
                list = (ListOfGuest) client.read();
                for(int i=0; i<list.getGuests().size(); i++)
                {

                    writer.write(list.getGuests().get(i).getName()+" "+ list.getGuests().get(i).getEmail()+" "+list.getGuests().get(i).getIdevent()+" "+list.getGuests().get(i).getIduser()+lineSeparator);
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
       button1.setOnMouseClicked(event -> {
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("/FXML/grafic.fxml"));
           try {
               loader.load();
           } catch (IOException e) {
               e.printStackTrace();
           }
           GraficController controller = loader.getController();
           controller.setClient(client);
           Parent root = loader.getRoot();
           Stage stage = new Stage();
           stage.setScene(new Scene(root));
           stage.show();
           Stage thisstage = (Stage) button1.getScene().getWindow();
           thisstage.hide();

       });
    }
}
