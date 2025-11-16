public class Client {

    private Socket socket;
    private PrintWriter out;

    public Client (String name, int port) throws IOException{

        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream());
    }

    public void handshake () {

        out.println("12345");
        out.flush();
    }

    public String request(String s) {

        out.println(s);
        return null;
    }

    public Socket getSocket() {

        return socket;
    }
}