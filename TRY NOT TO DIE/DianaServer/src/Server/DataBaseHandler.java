package Server;

import entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBaseHandler extends Configs {
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException
    {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" +dbName +"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDateTimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }
    public void InsertInDB(String login, String password)
    {
      String insert = "INSERT INTO " + Const.USER_TABLE + "("  + Const.USERS_LOGIN + "," + Const.USERS_PASSWORD +")" + " VALUES(?,?) ";


        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, login);
            prSt.setString(2, password);
            prSt.executeUpdate();
            System.out.println("Данные успешно записаны в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    public void InsertEvent(Event event)
    {
        String insert = "INSERT INTO event(name,age,type,numberofpeople) " + " VALUES(?,?, ?, ?) ";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, event.getName());
            prSt.setString(2, event.getAge());
            prSt.setString(3, event.getType());
            prSt.setString(4, event.getNumberofpeople());
            prSt.executeUpdate();
            System.out.println("Данные успешно записаны в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public boolean InsertGuest(Guest guest)
    {
        String select = "SELECT numberofpeople FROM event WHERE idevent=" +  guest.getIdevent();
        ResultSet rs = null;
        int requirednumber = 0;
        try{ Connection db = getDbConnection();
            Statement st = db.createStatement();
            rs = st.executeQuery(select);
            while(rs.next()) {
                requirednumber =Integer.parseInt(rs.getString("numberofpeople"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Event event = new Event();
        event.setId(Integer.parseInt(guest.getIdevent()));
        int numberofguests = this.SelectNumberOfGuests(event);
        if( numberofguests <  requirednumber) {
            String insert = "INSERT INTO guest (email,username,idevent,name) " + " VALUES(?,?, ?, ?) ";
            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(insert);
                prSt.setString(1, guest.getEmail());
                prSt.setString(2, guest.getIduser());
                prSt.setString(3, guest.getIdevent());
                prSt.setString(4, guest.getName());
                prSt.executeUpdate();
                System.out.println("Данные успешно записаны в базу данных");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return  true;
        }
        return false;
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
    public ArrayList<Event> SelectEvents()
    {
        ArrayList<Event> list = new ArrayList<>();
        String select = "SELECT * FROM event";
        ResultSet rs = null;
        try{ Connection db = getDbConnection();
            Statement st = db.createStatement();
            rs = st.executeQuery(select);
            while(rs.next()) {
               Event event = new Event();
               event.setId(Integer.parseInt(rs.getString("idevent")));
               event.setAge(rs.getString("age"));
                event.setType(rs.getString("type"));
                event.setName(rs.getString("name"));
                event.setNumberofpeople(rs.getString("numberofpeople"));
                list.add(event);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<Guest> SelectGuest()
    {
        ArrayList<Guest> list = new ArrayList<>();
        String select = "SELECT * FROM guest";
        ResultSet rs = null;
        try{ Connection db = getDbConnection();
            Statement st = db.createStatement();
            rs = st.executeQuery(select);
            while(rs.next()) {
                Guest guest = new Guest();
                guest.setId(Integer.parseInt(rs.getString("idguest")));
                guest.setEmail(rs.getString("email"));
                guest.setIdevent(rs.getString("idevent"));
                guest.setIduser(rs.getString("username"));
                guest.setName(rs.getString("name"));
                list.add(guest);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    public int SelectNumberOfGuests(Event event)
    {
        String select = "SELECT * FROM guest WHERE idevent=" + event.getId();
        ResultSet rs = null;
        int i = 0;
        try{ Connection db = getDbConnection();
            Statement st = db.createStatement();
            rs = st.executeQuery(select);
            while(rs.next()) {
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return i;
    }

    public void DeleteEvent(Event event)
    {
        String insert = "DELETE FROM event WHERE idevent=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, String.valueOf( event.getId()));
            prSt.executeUpdate();
            System.out.println("Данные успешно удалены в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void DeleteGuest(Guest guest)
    {
        String insert = "DELETE FROM guest WHERE idguest=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, String.valueOf( guest.getId()));
            prSt.executeUpdate();
            System.out.println("Данные успешно удалены в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

