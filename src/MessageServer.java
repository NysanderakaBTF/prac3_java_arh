import java.net.ServerSocket;
import java.util.concurrent.Executors;

public class MessageServer {


    public static void main(String[] args) throws Exception {
        try (var listener = new ServerSocket(9999)) {
            System.out.println("Ready...");
            var pool = Executors.newFixedThreadPool(20);
            var buffer_pool = Executors.newFixedThreadPool(1);
            while (true) {
                buffer_pool.execute(new MessageBuffer(listener.accept()));
                pool.execute(new sender(listener.accept()));
            }
        }
    }


}
