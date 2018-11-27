package com.company;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;


public class MonoThreadClientHandler implements Runnable {

    private static Socket clientDialog;


    static double findPremiya(Sdelnaya premiya)
    {
        double sum = 0;
        sum = ( premiya.getZarplata()*(1+((premiya.getProcentzaplan()+premiya.getProcentpereplan()*premiya.getProcentzapereplan())/100)) ) - premiya.getZarplata() ;
     return sum;
    }
   static double findPremiya(Povremenaya premiya)
    {
        double sum = 0;
        sum =(premiya.getZarplata() * (1 + premiya.getProcpremii()/100)) - premiya.getZarplata();
        return sum;
    }
    static    double findPremiya(Akkordnaya premiya)
    {
        double sum = 0;
        sum=premiya.getPrirabotok() / ( premiya.getKaefuch()*premiya.getTime()*premiya.getTarifstavka() );
        return  sum;
    }


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
                // и выводит в консоль
                System.out.println("Trying to log in: - " + entry.getLogin());
                if(object instanceof RegistrationData) {

                    RegistrationData reqdata = (RegistrationData)object;
                    dataBaseHandler.InsertInDB(reqdata.getName(), reqdata.getEmail(), reqdata.getLogin(), reqdata.getPassword());
                }
                if(object instanceof Person)
                {
                    Person newperson = (Person) object;
                    dataBaseHandler.InsertPremiya(newperson);
                }
                if(object instanceof PersonID)
                {
                    PersonID personID = (PersonID) object;
                    dataBaseHandler.Delete(personID);
                }
                if(object instanceof Premiya)
                {
                    Premiya newpremiya = (Premiya) object;
                    dataBaseHandler.InsertPremiya(newpremiya);
                }
                if(object instanceof ListOfPerson)
                {
                    ListOfPerson newlist = (ListOfPerson) object;
                    newlist.setListOfPerson(dataBaseHandler.SelectPersons());
                    out.writeObject(newlist);
                    System.out.println("Отправлен список работников");
                }
                if(object instanceof ListOfPremiya)
                {
                    ListOfPremiya listOfPremiya = (ListOfPremiya) object;
                    listOfPremiya.setListOfPremiya(dataBaseHandler.SelectPremii());
                    out.writeObject(listOfPremiya);
                    System.out.println("Отправлен список премий");
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