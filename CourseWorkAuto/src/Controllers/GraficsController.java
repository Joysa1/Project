package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.ListOfPerson;
import entity.ListOfPremiya;
import entity.PersonForTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Client;

public class GraficsController {

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
    private Button button_choose;

    @FXML
    private PieChart chart;
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
        button_choose.setOnMouseClicked(event -> {
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            ListOfPerson list = new ListOfPerson();
            client.write(list);
            list = (ListOfPerson) client.read();
            ListOfPremiya listOfPremiya = new ListOfPremiya();
            client.write(listOfPremiya);
            listOfPremiya = (ListOfPremiya) client.read();
            int maxotdel = 0;
            for (int k = 0; k < list.getListOfPerson().size(); k++) {
                if (maxotdel < Integer.parseInt(list.getListOfPerson().get(k).getNumotdel())) {
                    maxotdel = Integer.parseInt(list.getListOfPerson().get(k).getNumotdel());
                }
            }
            for (int f = 0; f <= maxotdel; f++) {
                double sum = 0;
                for (int i = 0; i < listOfPremiya.getListOfPremiya().size(); i++) {
                    for (int j = 0; j < list.getListOfPerson().size(); j++) {
                        if (Integer.parseInt(list.getListOfPerson().get(j).getNumotdel()) == f) {
                            if (listOfPremiya.getListOfPremiya().get(i).getIdemployee().equals(list.getListOfPerson().get(j).getIdperson()))
                                sum = sum + Double.parseDouble(listOfPremiya.getListOfPremiya().get(j).getSum());
                        }
                    }
                }

                if (sum != 0) {
                    PieChart.Data newdata = new PieChart.Data(String.valueOf(f), sum);
                    pieChartData.add(newdata);
                }

            }
            chart.setData(pieChartData);
        });
        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY());
                            caption.setText(String.valueOf(data.getPieValue()) + "%");
                        }
                    });

        }
    }
}
