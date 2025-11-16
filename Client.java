import java.util.*;
import java.net.*;
import java.io.*;

public class Client {

    private Socket sock;
    private PrintWriter out;
    private BufferedReader in;

    public Client (String name, int port) throws IOException{

        sock = new Socket(name, port);
        out = new PrintWriter(sock.getOutputStream());
        in  = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    }

    public void handshake () {

        out.println("12345");
        out.flush();
    }

    public String request(String s) throws IOException{

        out.println(s);
        out.flush();
        return in.readLine();
    }

    public Socket getSocket() {

        return sock;
    }

    public void disconnect() {

        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (sock != null) {
                sock.close();
            }
        }
        catch (IOException e) {

        }
    }
}