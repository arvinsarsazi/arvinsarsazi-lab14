import java.util.*;
import java.net.*;
import java.io.*;
import java.time.*;

public class Server {

    private ServerSocket serverSocket;
    private ArrayList<LocalDateTime> time_count;
    
    public Server(int port) {

        time_count = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void serve(int a) {

        for (int i = 0; i < a; i++) {
            try {

                Socket clientSocket = serverSocket.accept();
                time_count.add(LocalDateTime.now());
                new ClientHandler(clientSocket).start();

            } catch (Exception e) {
                
                return;
            }
        }
    }

    public void disconnect() {
        
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (Exception e) {
            
        }
    }

    public ArrayList<LocalDateTime> getConnectedTimes() {

        Collections.sort(time_count);
        return time_count;
    }
}