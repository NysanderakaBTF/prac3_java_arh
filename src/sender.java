import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class sender implements Runnable {

    private Socket socket;

    sender(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        MessageBuffer mb = new MessageBuffer(socket);
        while (true) {
            if (mb.messages.size() > 0) {
                PrintWriter out = null;
                try {
                    out = new PrintWriter(socket.getOutputStream(), true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                while (mb.messages.size() > 0) {
                    out.println(mb.messages.get(0));
                    mb.messages.remove(0);
                }
            }
            try {
                Thread.currentThread().wait(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

