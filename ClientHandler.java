import java.util.*;
import java.net.*;
import java.io.*;


public class ClientHandler extends Thread {

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