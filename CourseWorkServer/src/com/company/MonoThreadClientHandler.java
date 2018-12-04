package com.company;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.*;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;
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
            File file = new File("otchet.txt");

            // Создание файла
            file.createNewFile();
            // Создание объекта FileWriter
            FileWriter writer = new FileWriter(file);
            String lineSeparator = System.getProperty("line.separator");
            DataBaseHandler database = new DataBaseHandler();
            writer.write("Фамилия" +" " + "Дата" +" " + "Сумма" +lineSeparator);
            // запись всей строки
            ListOfPerson list = new ListOfPerson();
            list.setListOfPerson(database.SelectPersons());

            ListOfPremiya listOfPremiya = new ListOfPremiya();
            listOfPremiya.setListOfPremiya(database.SelectPremii());
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
                    ListOfPremiya listOfPremiya2 = (ListOfPremiya) object;
                    listOfPremiya2.setListOfPremiya(dataBaseHandler.SelectPremii());
                    out.writeObject(listOfPremiya2);
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