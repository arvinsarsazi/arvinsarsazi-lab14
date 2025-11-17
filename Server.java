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


    private class ClientHandler extends Thread {

        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream());

                String Password = in.readLine();

                if (!"12345".equals(Password)) {

                    out.println("couldn't handshake");
                    out.flush();
                    clientSocket.close();
                    return;
                }

                String number = in.readLine();

                String result;

                try {

                    int value = Integer.parseInt(number);
                    int factors = countFactors(value);
                    result = "The number " + value + " has " + factors + " factors";

                } catch (Exception e) {
                    
                    result = "There was an exception on the server";
                }

                out.println(result);
                out.flush();
                clientSocket.close();

            } catch (Exception e) {
                
                try {

                    clientSocket.close();

                } catch (Exception f) {
                    
                }
            }
        }


        private int countFactors(int n) {

            int num = n;
            int result = 1;

            for (int i = 2; i * i <= num; i++) {

                if (num % i == 0) {
                    int exponent = 0;
                    while (num % i == 0) {
                        num = num / i;
                        exponent++;
                    }
                    result *= (exponent + 1);
                }
            }

            if (num > 1) {
                result *= 2;
            }

            return result;
        }
    }
}