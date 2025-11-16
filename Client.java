public class Client {

    private Socket socket;
    
    public Client (String name, int port) throws IOException{

        socket = new Socket(host, port);
    }

    public void handshake () {

    }

    public String request(String s) {

        return null;
    }

    public Socket getSocket() {

        return socket;
    }
}