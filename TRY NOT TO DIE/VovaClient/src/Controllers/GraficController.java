package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import entity.ListOfPokupka;
import entity.ListOfTovar;
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
    private Button back;

    @FXML
    void initialize() {
        back.setOnMouseClicked(event -> {
            MenuController menuController = new MenuController();
            menuController.showWindowMenu(client);
            Stage thisstage = (Stage) back.getScene().getWindow();
            thisstage.hide();
        });

        button.setOnMouseClicked(event -> {
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            ListOfTovar list = new ListOfTovar();
            client.write(list);
            list = (ListOfTovar) client.read();
            ListOfPokupka listOfPokupka = new ListOfPokupka();
            client.write(listOfPokupka);
            listOfPokupka = (ListOfPokupka) client.read();

            for(int i = 0; i<list.getTovars().size(); i++) {
                int sum = 0;
                for(int j=0; j<listOfPokupka.getPokupkas().size(); j++)
                {
                    if(list.getTovars().get(i).getId()== Integer.parseInt(listOfPokupka.getPokupkas().get(j).getIdtovara()))
                    {
                        sum+= Integer.parseInt(listOfPokupka.getPokupkas().get(j).getSumma());
                    }
                }

                if (sum != 0) {
                    PieChart.Data newdata = new PieChart.Data(list.getTovars().get(i).getName(), sum);
                    pieChartData.add(newdata);
                }

            }
            chart.setData(pieChartData);
        });


    }
}
