package com.company;

import java.sql.*;

public class DataBaseHandler extends Configs {
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException
    {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" +dbName +"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDateTimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }
    public void InsertInUser(String name, String surname, String lastname, int age, String number, String login, String password, boolean admin)
    {
      String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_LOGIN + "," + Const.USERS_PASSWORD + "," + Const.USERS_ADMIN + "," + Const.USERS_NAME + "," + Const.USERS_SURNAME + "," + Const.USERS_LASTNAME + "," + Const.USERS_AGE + "," + Const.USERS_NUMBER +")" + " VALUES(?,?,?,?, ?, ?, ?, ?) ";


        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, login);
            prSt.setString(2, password);
            prSt.setBoolean(3, admin);
            prSt.setString(4, name);
            prSt.setString(5, surname);
            prSt.setString(6, lastname);
            prSt.setInt(7,  age);
            prSt.setString(8, number);
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


    }

