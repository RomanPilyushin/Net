package SendGetFile;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        //Message message = new Message(Message.Commands.CREATE_DIR, "D:/SDK");
        Message message = new Message(Message.Commands.GET_FILE, "D:/txt4.txt");
        Gson gson = new Gson();
        String toJson = gson.toJson(message);
        Socket socket = new Socket("localhost", 6789);
        socket.getOutputStream().write(toJson.getBytes());


    }
}
