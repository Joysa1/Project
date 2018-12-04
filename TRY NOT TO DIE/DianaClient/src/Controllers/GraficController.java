package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.ListOfEvents;
import entity.ListOfGuest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Client;

public class GraficController {
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
    private PieChart chart;

    @FXML
    private ImageView back;

    @FXML
    void initialize() {
        back.setOnMouseClicked(event ->
        {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/analizemenu.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AnalizeMenuController controller = loader.getController();
            controller.setClient(client);
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage thisstage = (Stage) back.getScene().getWindow();
            thisstage.hide();

        });
        button.setOnMouseClicked(event -> {
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            ListOfGuest list = new ListOfGuest();
            client.write(list);
            list = (ListOfGuest) client.read();
            ListOfEvents listOfEvents = new ListOfEvents();
            client.write(listOfEvents);
            listOfEvents = (ListOfEvents) client.read();

            for(int i = 0; i<listOfEvents.getEvents().size(); i++) {
                int sum = 0;
                for(int j=0; j<list.getGuests().size(); j++)
                {
                    if(Integer.parseInt(list.getGuests().get(j).getIdevent())==listOfEvents.getEvents().get(i).getId())
                    {
                        sum++;
                    }
                }

                if (sum != 0) {
                    PieChart.Data newdata = new PieChart.Data(listOfEvents.getEvents().get(i).getName(), sum);
                    pieChartData.add(newdata);
                }

            }
            chart.setData(pieChartData);
        });


    }
}
