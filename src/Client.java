import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class Client {

    public static void main(String[] args) throws Exception {


        try (Socket socket = new Socket("localhost", 5001)) {
            Scanner scanner = new Scanner(System.in);
//            while (true) {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            CompletableFuture.runAsync(()->{
                while (true)
                    System.out.println(in.nextLine());
            });
            while (true) {
                try {
                    out.println(scanner.nextLine());

                } catch (Exception e) {
                    System.err.println(e);
                }
            }

        }
    }
}