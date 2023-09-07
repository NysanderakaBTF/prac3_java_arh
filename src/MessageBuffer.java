import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MessageBuffer implements Runnable{
    public static List<String> messages = Collections.synchronizedList(new ArrayList<String>());
    private Socket socket;

    MessageBuffer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Conn: " + socket);
        try {
            var in = new Scanner(socket.getInputStream());
            while (in.hasNextLine()) {
                messages.add(in.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Err:" + socket);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
            System.out.println("Closed: " + socket);
        }
    }
}
