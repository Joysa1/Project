package Controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.ListOfTovar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Client;

public class MenuController {
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
    private Button newtovar;

    @FXML
    private Button redaktovar;

    @FXML
    private Button deltovar;

    @FXML
    private Button registrtovar;

    @FXML
    private Button spisoktovara;

    @FXML
    private Button newpokupka;

    @FXML
    private Button delpokupka;

    @FXML
    private Button spisokpokupok;

    @FXML
    private Button otchet;

    @FXML
    private Button grafic;

    @FXML
    void initialize() {
        newtovar.setOnMouseClicked(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/newtovar.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            NewTovarController controller = loader.getController();
            controller.setClient(client);
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage thisstage = (Stage) newtovar.getScene().getWindow();
            thisstage.hide();
        });

        redaktovar.setOnMouseClicked(event -> {
            if(client.getAutorizationData().getLogin().equals("Vova")) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/FXML/redakttovar.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                RedaktTovarController controller = loader.getController();
                controller.setClient(client);
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                Stage thisstage = (Stage) newtovar.getScene().getWindow();
                thisstage.hide();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Требуются права администратора!");
                alert.showAndWait();
            }
        });

        deltovar.setOnMouseClicked(event -> {
            if(client.getAutorizationData().getLogin().equals("Vova")) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/FXML/deltovar.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                DelTovarController controller = loader.getController();
                controller.setClient(client);
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                Stage thisstage = (Stage) newtovar.getScene().getWindow();
                thisstage.hide();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Требуются права администратора!");
                alert.showAndWait();
            }
        });
        registrtovar.setOnMouseClicked(event -> {
            if(client.getAutorizationData().getLogin().equals("Vova")) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/FXML/registrtovar.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                RegistrTovarController controller = loader.getController();
                controller.setClient(client);
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                Stage thisstage = (Stage) newtovar.getScene().getWindow();
                thisstage.hide();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Требуются права администратора!");
                alert.showAndWait();

            }
        });
        spisoktovara.setOnMouseClicked(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/spisoktovara.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SpisokTovaraController controller = loader.getController();
            controller.setClient(client);
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage thisstage = (Stage) newtovar.getScene().getWindow();
            thisstage.hide();
        });
        newpokupka.setOnMouseClicked(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/newpokupka.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            NewPokupkaController controller = loader.getController();
            controller.setClient(client);
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage thisstage = (Stage) newtovar.getScene().getWindow();
            thisstage.hide();
        });
        delpokupka.setOnMouseClicked(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/delpokupka.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            DelPokupkaController controller = loader.getController();
            controller.setClient(client);
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage thisstage = (Stage) newtovar.getScene().getWindow();
            thisstage.hide();

        });
        spisokpokupok.setOnMouseClicked(event ->{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/spisokpokupok.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SpisokPokupokController controller = loader.getController();
            controller.setClient(client);
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage thisstage = (Stage) newtovar.getScene().getWindow();
            thisstage.hide();
        });
        otchet.setOnMouseClicked(event->{
            try {
                File file = new File("spisoktovarov.txt");


                file.createNewFile();

                FileWriter writer = new FileWriter(file);
                String lineSeparator = System.getProperty("line.separator");
                writer.write("Имя"+" "+ "Email"+" "+"ID event"+" "+"Username"
                        +lineSeparator);

                ListOfTovar listOfTovar = new ListOfTovar();
                client.write(listOfTovar);
                listOfTovar = (ListOfTovar) client.read();
                for(int i=0; i<listOfTovar.getTovars().size(); i++)
                {

                    writer.write(listOfTovar.getTovars().get(i).getName()+" "+ listOfTovar.getTovars().get(i).getKind()+" "+listOfTovar.getTovars().get(i).getKolvo()+" "+listOfTovar.getTovars().get(i).getCena()+lineSeparator);
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
        grafic.setOnMouseClicked(event -> {
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
            Stage thisstage = (Stage) newtovar.getScene().getWindow();
            thisstage.hide();

        });


    }
    public void showWindowMenu(Client client)
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
    }
}
