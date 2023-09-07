import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.Set;

public class Client {
    public static void main(String[] args) throws Exception {


        if (args.length != 1) {
            System.err.println("give ip");
            return;
        }
        try (var socket = new Socket(args[0], 9999)) {
            var scanner = new Scanner(System.in);
            var in = new Scanner(socket.getInputStream());
            Thread thread = new Thread(() -> {
                while (true) {
                    if (in.hasNextLine())
                        System.out.println(in.nextLine());
                }
            });
            thread.start();
            var out = new PrintWriter(socket.getOutputStream(), true);
            while (scanner.hasNextLine()) {
                out.println(scanner.nextLine());
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}

