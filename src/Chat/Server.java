package Chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(123);
        final Socket socket = serverSocket.accept();
        final String string = socket.getInputStream().toString();
        final String newString = string.toUpperCase();


    }
}
