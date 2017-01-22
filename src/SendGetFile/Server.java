package SendGetFile;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;

/**
 * Date: 24.11.2016
 *
 * @author Michael Lang
 * @version 1.0
 */
public class Server implements Runnable {
    private Gson gson;
    private int port;
    private boolean flag;
    private Selector selector;
    public static final int BUFFER_SIZE = 2048;

    public Server(int port) {
        this.gson = new Gson();
        this.port = port;
    }

    public static void main(String[] args) {
        new Server(6789).run();
    }

    @Override
    public void run() {
        try {
            init();
        } catch (IOException e) {
            System.out.println("Ошибка при инициализации сервера!");
            return;
        }

        try {
            this.flag = true;
            while (flag) {
                selector.select(); // Выделяем ключи и получаем их количество
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    iterator.remove();

                    if (next.isReadable()) {
                        read(next);
                    } else if (next.isAcceptable()) {
                        accept(next);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        this.selector = Selector.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
        socketChannel.bind(inetSocketAddress);
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    private void accept(SelectionKey next) throws IOException {
        ServerSocketChannel channel = (ServerSocketChannel) next.channel();
        SocketChannel accept = channel.accept();
        accept.configureBlocking(false);
        accept.register(selector, SelectionKey.OP_READ);
    }

    private void read(SelectionKey next) throws IOException {
        SocketChannel channel = (SocketChannel) next.channel();
        ByteBuffer bb = ByteBuffer.allocate(BUFFER_SIZE);
        int len = channel.read(bb);
        String json = new String(bb.array(), 0, len);
        Message message = gson.fromJson(json, Message.class);
        readMessage(message, next);

    }

    private void readMessage(Message message, SelectionKey next) throws IOException {
        String path = message.getPath();

        switch (message.getCommand()) {
            case GET_FILE:
                sendFile(path, next);
                break;
            case CREATE_DIR:
                System.out.printf("Create dir %s\n", path);
                Files.createDirectories(Paths.get(path));
                break;
        }
        next.channel().close();
        next.cancel();
    }

    private void sendFile(String path, SelectionKey next) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        StringBuilder builder = new StringBuilder();
        byte[] bytes = new byte[BUFFER_SIZE];
        int len;
        while ((len = fis.read(bytes)) > 0) {
            fis.read(bytes);
            builder.append((char)len);
        }
        System.out.println(builder.toString());
    }
}
