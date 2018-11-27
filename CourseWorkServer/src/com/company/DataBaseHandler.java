package com.company;
import entity.*;

import java.util.Date;
import java.sql.*;
import java.util.ArrayList;

public class DataBaseHandler extends Configs {
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException
    {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" +dbName +"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDateTimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }
    public void InsertInDB(String name, String email, String login, String password)
    {
      String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_NAME + "," + Const.USERS_EMAIL + "," + Const.USERS_LOGIN + "," + Const.USERS_PASSWORD +")" + " VALUES(?,?,?,?) ";


        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, name);
            prSt.setString(2, email);
            prSt.setString(3, login);
            prSt.setString(4, password);
            prSt.executeUpdate();
            System.out.println("Данные успешно записаны в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    public boolean SelectFromDb(String login, String password)
    {
        boolean exist = false;
        String select = "SELECT " + Const.USERS_LOGIN + "," + Const.USERS_PASSWORD + " FROM " + Const.USER_TABLE;
        ResultSet rs = null;
        try{ Connection db = getDbConnection();
            Statement st = db.createStatement();
            rs = st.executeQuery(select);
            while(rs.next()) {
                String buflogin = rs.getString(Const.USERS_LOGIN);
                String bufpassword = rs.getString(Const.USERS_PASSWORD);
              //  System.out.println(buflogin+" "+bufpassword+ "----" +login + " " + password);
                if(buflogin.equals(login) &&  bufpassword.equals(password)) { exist=true; System.out.println("Autorization accepted"); }
            }
        }
            catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return exist;
    }
    public ArrayList<Person> SelectPersons()
    {
        String select = "SELECT " + Const.PERSON_NAME + "," + Const.PERSON_SURNAME+ "," + Const.PERSON_LASTNAME + "," + Const.PERSON_DEPARTAMENT+ "," + Const.PERSON_ID + " FROM " + Const.PERSON_TABLE;
        ResultSet rs = null;
        ArrayList<Person> list = new ArrayList<>();
        try{ Connection db = getDbConnection();
            Statement st = db.createStatement();
            rs = st.executeQuery(select);
            while(rs.next()) {
                Person newperson = new Person();
                newperson.setName(rs.getString(Const.PERSON_NAME));
                newperson.setSurname(rs.getString(Const.PERSON_SURNAME));
                newperson.setOtchestvo(rs.getString(Const.PERSON_LASTNAME));
                newperson.setNumotdel(rs.getString(Const.PERSON_DEPARTAMENT));
                newperson.setIdperson(rs.getString(Const.PERSON_ID));
                list.add(newperson);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void InsertPremiya(Premiya premiya)
    {
        String insert = "INSERT INTO " + Const.PREMII_TABLE + "(" + Const.PREMII_DATE + "," + Const.PREMII_SUM + "," + Const.PREMII_IDEMPLOYEE + "," + Const.PREMII_KINDOFPREMIYA +")" + " VALUES(?,?,?,?) ";


        try {

            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, new Date().toString());
            Premii kindofpremiya = premiya.getPremiya();
            switch (premiya.getTypeOfPremiya()) {
                case 1: { prSt.setString(2, String.valueOf(MonoThreadClientHandler.findPremiya((Sdelnaya) kindofpremiya))); break; }
                case 2: { prSt.setString(2, String.valueOf(MonoThreadClientHandler.findPremiya((Akkordnaya) kindofpremiya))); break; }
                case 3: { prSt.setString(2, String.valueOf(MonoThreadClientHandler.findPremiya((Povremenaya)kindofpremiya))); break; }
            }
            prSt.setString(3, premiya.getPersonId());
            switch (premiya.getTypeOfPremiya()) {
                case 1:  { prSt.setString(4, "Сдельная"); break; }
                case 2:  {  prSt.setString(4, "Аккордная"); break;}
                case 3:  { prSt.setString(4, "Повременная"); break; }
            }

            prSt.executeUpdate();
            System.out.println("Данные успешно записаны в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    public ArrayList<PremiyaForTable> SelectPremii()
    {
        String select = "SELECT " + Const.PREMII_DATE+ "," + Const.PREMII_IDEMPLOYEE + "," + Const.PREMII_SUM +  " FROM " + Const.PREMII_TABLE;
        ResultSet rs = null;
        ArrayList<PremiyaForTable> list = new ArrayList<>();
        try{ Connection db = getDbConnection();
            Statement st = db.createStatement();
            rs = st.executeQuery(select);
            while(rs.next()) {
                PremiyaForTable premiyaForTable = new PremiyaForTable();
                premiyaForTable.setDate(rs.getString(Const.PREMII_DATE));
                premiyaForTable.setIdemployee(rs.getString(Const.PREMII_IDEMPLOYEE));
                premiyaForTable.setSum(rs.getString(Const.PREMII_SUM));

                list.add(premiyaForTable);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;


    }
    public void InsertPremiya(Person person)
    {
        String insert = "INSERT INTO " + Const.PERSON_TABLE + "("  + Const.PERSON_DEPARTAMENT + "," + Const.PERSON_LASTNAME + "," + Const.PERSON_NAME +"," + Const.PERSON_SURNAME + ")" + " VALUES(?,?,?,?) ";


        try {

            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, person.getNumotdel());
            prSt.setString(2, person.getOtchestvo());
            prSt.setString(3, person.getName());
            prSt.setString(4, person.getSurname());
            prSt.executeUpdate();
            System.out.println("Данные успешно записаны в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    public void Delete(PersonID personID)
    {
        String delete = "DELETE FROM " + Const.PERSON_TABLE + " WHERE "+Const.PERSON_ID+"=" + "?";


        try {

            PreparedStatement prSt = getDbConnection().prepareStatement(delete);
            prSt.setString(1, personID.getPersonid());
            prSt.executeUpdate();
            System.out.println("Данные успешно удалены из базы данных");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    }

