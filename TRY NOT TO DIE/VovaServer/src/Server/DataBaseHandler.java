package Server;

import entity.*;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseHandler extends DannieBD {
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
        String insert = "INSERT INTO " + DannieTablic.USER_TABLE + "("  + DannieTablic.USERS_LOGIN + "," + DannieTablic.USERS_PASSWORD +")" + " VALUES(?,?) ";


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
    public void InsertTovar(Tovar tovar)
    {
        String insert = "INSERT INTO tovar(name,kind,cena,kolvo) " + " VALUES(?,?, ?, ?) ";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, tovar.getName());
            prSt.setString(2, tovar.getKind());
            prSt.setString(3, tovar.getCena());
            prSt.setString(4, tovar.getKolvo());
            prSt.executeUpdate();
            System.out.println("Данные успешно записаны в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void UpdateTovar(Tovar tovar)
    {
        String select = "SELECT cena, kolvo FROM tovar WHERE idtovar =" + tovar.getId();
        ResultSet rs = null;
        Connection db = null;
        int kolvo = 0;
        int cena =0;
        try {
            db = getDbConnection();
            Statement st = db.createStatement();
            rs = st.executeQuery(select);
            while(rs.next()) {
                cena = Integer.parseInt(rs.getString("cena"));
                kolvo = Integer.parseInt(rs.getString("kolvo"));
            }
            String query = "update tovar set kolvo = ?, cena = ? where idtovar = ?";
            PreparedStatement preparedStmt = getDbConnection().prepareStatement(query);
            preparedStmt.setString   (3, String.valueOf(tovar.getId()));
            if(tovar.getCena()!=null) {
                preparedStmt.setString(2, tovar.getCena());
            }
            else preparedStmt.setString(2, String.valueOf(cena));
            if(tovar.getKolvo()!=null) {
                String newkolvo = String.valueOf(kolvo + Integer.parseInt(tovar.getKolvo()));
                preparedStmt.setString(1, newkolvo);
            }
            else  preparedStmt.setString(1, String.valueOf(kolvo));
            preparedStmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Tovar> SelectTovar()
    {
        ArrayList<Tovar> list = new ArrayList<>();
        String select = "SELECT * FROM tovar";
        ResultSet rs = null;
        try{ Connection db = getDbConnection();
            Statement st = db.createStatement();
            rs = st.executeQuery(select);
            while(rs.next()) {
                Tovar tovar = new Tovar();
                tovar.setId(Integer.parseInt(rs.getString("idtovar")));
                tovar.setKolvo(rs.getString("kolvo"));
                tovar.setKind(rs.getString("kind"));
                tovar.setName(rs.getString("name"));
                tovar.setCena(rs.getString("cena"));
                list.add(tovar);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean InsertPokupki(Pokupka pokupka)
    {

        String select = "SELECT kolvo FROM tovar WHERE idtovar =" + pokupka.getIdtovara();
        ResultSet rs = null;
        Connection db = null;
        int kolvo = 0;
        try {
            db = getDbConnection();
            Statement st = db.createStatement();
            rs = st.executeQuery(select);
            while(rs.next()) {
             kolvo = Integer.parseInt(rs.getString("kolvo"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

       if(kolvo >= Integer.parseInt(pokupka.getKolvo()))
        {
            String insert = "INSERT INTO pokupki(idtovara,iduser,kolvo,summa) " + " VALUES(?,?, ?, ?) ";
            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(insert);
                prSt.setString(1, pokupka.getIdtovara());
                prSt.setString(2, pokupka.getIduser());
                prSt.setString(3, pokupka.getKolvo());
                prSt.setString(4, pokupka.getSumma());
                prSt.executeUpdate();

              String query = "update tovar set kolvo = ? where idtovar = ?";
        PreparedStatement preparedStmt = getDbConnection().prepareStatement(query);
         preparedStmt.setString   (2, pokupka.getIdtovara());
        String newkolvo = String.valueOf(kolvo - Integer.parseInt(pokupka.getKolvo()));
        preparedStmt.setString(1, newkolvo);
        preparedStmt.executeUpdate();
                System.out.println("Данные успешно записаны в базу данных");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    return false;
    }
    public void deletePokupka(Pokupka pokupka)
    {
        String select = "SELECT kolvo FROM tovar WHERE idtovar =" + pokupka.getIdtovara();
        String select2 = "SELECT kolvo FROM pokupki WHERE idpokupki =" + pokupka.getId();
        ResultSet rs = null;
        ResultSet rs2 = null;
        Connection db = null;
        int kolvotovar = 0;
        int kolvopokupki = 0;
        try {
            db = getDbConnection();
            Statement st = db.createStatement();
            Statement st2 = db.createStatement();
            rs = st.executeQuery(select);
            while(rs.next()) {
                kolvotovar = Integer.parseInt(rs.getString("kolvo"));
            }
            rs2 =st2.executeQuery(select2);
            while(rs2.next()) {
                kolvopokupki = Integer.parseInt(rs2.getString("kolvo"));
            }
            String query = "update tovar set kolvo = ? where idtovar = ?";
            PreparedStatement preparedStmt = getDbConnection().prepareStatement(query);
            preparedStmt.setString   (2, pokupka.getIdtovara());
            String newkolvo = String.valueOf(kolvopokupki + kolvotovar);
            preparedStmt.setString(1, newkolvo);
            preparedStmt.executeUpdate();
            String delete = "DELETE FROM pokupki WHERE idpokupki = " + "?";
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);
            prSt.setString(1, String.valueOf(pokupka.getId()));
            prSt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Pokupka> SelectPokupka()
    {
        ArrayList<Pokupka> list = new ArrayList<>();
        String select = "SELECT * FROM pokupki";
        ResultSet rs = null;
        try{ Connection db = getDbConnection();
            Statement st = db.createStatement();
            rs = st.executeQuery(select);
            while(rs.next()) {
                Pokupka pokupka = new Pokupka();
                pokupka.setId(Integer.parseInt(rs.getString("idpokupki")));
                pokupka.setKolvo(rs.getString("kolvo"));
                pokupka.setIdtovara(rs.getString("idtovara"));
                pokupka.setIduser(rs.getString("iduser"));
                pokupka.setSumma(rs.getString("summa"));
                list.add(pokupka);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }


    public boolean SelectFromDb(String login, String password)
    {
        boolean exist = false;
        String select = "SELECT " + DannieTablic.USERS_LOGIN + "," + DannieTablic.USERS_PASSWORD + " FROM " + DannieTablic.USER_TABLE;
        ResultSet rs = null;
        try{ Connection db = getDbConnection();
            Statement st = db.createStatement();
            rs = st.executeQuery(select);
            while(rs.next()) {
                String buflogin = rs.getString(DannieTablic.USERS_LOGIN);
                String bufpassword = rs.getString(DannieTablic.USERS_PASSWORD);
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

    public void DeleteTovar(Tovar tovar) {

        try {
        String delete = "DELETE FROM tovar WHERE idtovar = " + "?";
        PreparedStatement prSt = getDbConnection().prepareStatement(delete);
        prSt.setString(1, String.valueOf(tovar.getId()));
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}

