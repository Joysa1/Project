package Server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class MonoThreadClientHandler implements Runnable {

    private static Socket clientDialog;





    public MonoThreadClientHandler(Socket client) {
        MonoThreadClientHandler.clientDialog = client;
    }

    @Override
    public void run() {

        try {
            // инициируем каналы общения в сокете, для сервера

            // канал записи в сокет следует инициализировать сначала канал чтения для избежания блокировки выполнения программы на ожидании заголовка в сокете
            //     DataOutputStream out = new DataOutputStream(clientDialog.getOutputStream());

// канал чтения из сокета
            //     DataInputStream in = new DataInputStream(clientDialog.getInputStream());
            ObjectInputStream in = new ObjectInputStream(clientDialog.getInputStream());
            System.out.println("DataInputStream created");
            ObjectOutputStream out = new ObjectOutputStream(clientDialog.getOutputStream());
            System.out.println("DataOutputStream  created");
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // основная рабочая часть //
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // начинаем диалог с подключенным клиентом в цикле, пока сокет не
            // закрыт клиентом
            while (!clientDialog.isClosed()) {
                System.out.println("Server reading from channel");

                // серверная нить ждёт в канале чтения (inputstream) получения
                // данных клиента после получения данных считывает их
                //  String entry = in.readUTF();
                Object object = in.readObject();
                DataBaseHandler dataBaseHandler = new DataBaseHandler();
                AutorizationData entry = new AutorizationData();
                if(object instanceof AutorizationData) {
                    entry = (AutorizationData)object;
                    out.writeBoolean(dataBaseHandler.SelectFromDb(entry.getLogin(), entry.getPassword()));
                }
                if(object instanceof  UpdateTovar)
                {
                    UpdateTovar updateTovar = (UpdateTovar) object;
                    dataBaseHandler.UpdateTovar(updateTovar.getTovar());
                }
                if(object instanceof ListOfTovar)
                {
                    ListOfTovar listOfTovar = (ListOfTovar) object;
                    listOfTovar.setTovars(dataBaseHandler.SelectTovar());
                    out.writeObject(listOfTovar);
                }
                // и выводит в консоль
                System.out.println("Trying to log in: - " + entry.getLogin());
                if(object instanceof RegistrationData) {

                    RegistrationData reqdata = (RegistrationData)object;
                    dataBaseHandler.InsertInDB(reqdata.getLogin(), reqdata.getPassword());
                }
                if(object instanceof DeleteTovar)
                {
                    DeleteTovar deleteTovar = (DeleteTovar) object;
                    dataBaseHandler.DeleteTovar(deleteTovar.getTovar());
                }
                if(object instanceof Tovar)
                {
                    Tovar tovar = (Tovar) object;
                    dataBaseHandler.InsertTovar(tovar);
                }
                if(object instanceof Pokupka)
                {
                    Pokupka pokupka = (Pokupka) object;
                    boolean answer = dataBaseHandler.InsertPokupki(pokupka);
                    out.writeBoolean(answer);
                }
                if(object instanceof DeletePokupka)
                {
                    DeletePokupka deletePokupka = (DeletePokupka) object;
                    dataBaseHandler.deletePokupka(deletePokupka.getPokupka());
                }
                if(object instanceof ListOfPokupka)
                {
                    ListOfPokupka listOfPokupka = (ListOfPokupka) object;
                    listOfPokupka.setPokupkas(dataBaseHandler.SelectPokupka());
                    out.writeObject(listOfPokupka);
                }

                System.out.println("Server try writing to channel");

                System.out.println("Server Wrote message to clientDialog.");

                // освобождаем буфер сетевых сообщений
                out.flush();

                // возвращаемся в началло для считывания нового сообщения
            }

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // основная рабочая часть //
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // если условие выхода - верно выключаем соединения
            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");

            // закрываем сначала каналы сокета !
            in.close();
            out.close();

            // потом закрываем сокет общения с клиентом в нити моносервера
            clientDialog.close();

            System.out.println("Closing connections & channels - DONE.");
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}