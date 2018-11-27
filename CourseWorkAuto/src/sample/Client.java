package sample;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import entity.AutorizationData;
public class Client {
    Socket socket;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    AutorizationData autorizationData;

    public AutorizationData getAutorizationData() {
        return autorizationData;
    }

    public void setAutorizationData(AutorizationData autorizationData) {
        this.autorizationData = autorizationData;
    }

    Client()
    {
        try
        {

            socket = new Socket("localhost", 3345);
            System.out.println("Client connected to socket.");
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("Client writing channel = oos & reading channel = ois initialized.");

        }

        catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public Object read()
    {
        try
        {

            Object in = ois.readObject();

            return in;
        }

        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new Object();
    }
    public boolean readAnswer()
    {
        boolean in = false;
        try
        {

            in = ois.readBoolean();

        }



        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return in;
    }
    public void write(Object obj)
    {
        try {

            oos.writeObject(obj);
            oos.flush();
            System.out.println("Client sent message to server.");

        }
        catch (IOException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }

    }
    public double readDouble()
    {
        double in=0;
        try
        {

            in = ois.readDouble();

        }



        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return in;
    }



}
