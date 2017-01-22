package SendGetFile;

public class Message {
    private Commands command;
    private String path;

    private byte[] bytes; // Пересылать в нем файл
    private int sizeOfFile;

    enum Commands {
        GET_FILE, CREATE_DIR;
    }

    public Message() {}

    public Message(Commands command, String path, int sizeOfFile) {
        this.command = command;
        this.path = path;
        this.sizeOfFile = sizeOfFile;
    }

    public Message(Commands command, String path) {
        this.command = command;
        this.path = path;
    }

    public Commands getCommand() {
        return command;
    }

    public void setCommand(Commands command) {
        this.command = command;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSizeOfFile() {
        return sizeOfFile;
    }

    public void setSizeOfFile(int sizeOfFile) {
        this.sizeOfFile = sizeOfFile;
    }

    @Override
    public String toString() {
        return "Message{" +
                "command=" + command +
                ", path='" + path + '\'' +
                ", sizeOfFile=" + sizeOfFile +
                '}';
    }
}
