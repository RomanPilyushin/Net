package Chat;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        String tmp = "hello world!!!";
        Socket socket = new Socket("localhost", 123);

        final OutputStream outputStream = socket.getOutputStream();
        System.out.println(outputStream.toString());
    }
}
