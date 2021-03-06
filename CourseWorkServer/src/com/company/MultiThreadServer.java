package com.company;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.lang.ClassNotFoundException;

public class MultiThreadServer {

    static ExecutorService executeIt = Executors.newCachedThreadPool();

    /**
     * @param args
     */
    public static void main(String[] args) {

        // стартуем сервер на порту 3345 и инициализируем переменную для обработки консольных команд с самого сервера
        FileInputStream fis;
        Properties properties = new Properties();
        int thisport = 0;
        File file = new File("stats.txt");
        try {
            fis = new FileInputStream("C:\\Users\\Sten\\IdeaProjects\\CourseWorkServer\\src\\resources.properties");
            properties.load(fis);
            String port = properties.getProperty("port");
            thisport = Integer.parseInt(port);
            // Создание файла
            file.createNewFile();
            // Создание объекта FileWriter


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ServerSocket server = new ServerSocket(thisport);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Server socket created, command console reader for listen to server commands");

            // стартуем цикл при условии что серверный сокет не закрыт
            while (!server.isClosed()) {

                // проверяем поступившие комманды из консоли сервера если такие
                // были
                System.out.println("Server is open");
                if (br.ready()) {
                    //         System.out.println("Main Server found any messages in channel, let's look at them.");

                    // если команда - quit то инициализируем закрытие сервера и
                    // выход из цикла раздачии нитей монопоточных серверов
                    String serverCommand = br.readLine();
                    if (serverCommand.equalsIgnoreCase("quit")) {
                        System.out.println("Main Server initiate exiting...");
                        server.close();
                        break;
                    }
                    else System.out.println("Unkonown command");
                }

                // если комманд от сервера нет то становимся в ожидание
                // подключения к сокету общения под именем - "clientDialog" на
                // серверной стороне
                Socket client = server.accept();

                // после получения запроса на подключение сервер создаёт сокет
                // для общения с клиентом и отправляет его в отдельную нить
                // в Runnable(при необходимости можно создать Callable)
                // монопоточную нить = сервер - MonoThreadClientHandler и тот
                // продолжает общение от лица сервера
                executeIt.execute(new MonoThreadClientHandler(client));
                System.out.print("Connection accepted.");

                String lineSeparator = System.getProperty("line.separator");
                Date date = new Date();
                FileWriter writer = new FileWriter(file);
                writer.write(date +" "+client.getInetAddress().toString()+":"+ client.getPort() +lineSeparator);
                writer.flush();
            }

            // закрытие пула нитей после завершения работы всех нитей
            executeIt.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
