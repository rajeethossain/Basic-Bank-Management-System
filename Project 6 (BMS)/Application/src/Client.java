import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    String recievedLine;
    OutputStreamWriter osw;
    PrintWriter writer;
    InputStreamReader isr;
    Scanner reader;
    Client(){
//        try {
//            Socket socket = new Socket("127.0.0.1", 5000);
//
//            osw = new OutputStreamWriter(socket.getOutputStream());
//            writer = new PrintWriter(osw);
//            isr = new InputStreamReader(socket.getInputStream());
//            reader = new Scanner(isr);
//
//        }
//        catch (IOException e){
//            System.out.println(e.getMessage());
//        }
    }

    public void run(){
        try {
            Socket socket = new Socket("127.0.0.1", 5000);

            osw = new OutputStreamWriter(socket.getOutputStream());
            writer = new PrintWriter(osw);
            isr = new InputStreamReader(socket.getInputStream());
            reader = new Scanner(isr);

        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void send(String line){
        writer.write(line);
        writer.flush();
        System.out.println(line);
    }

    public void recieve(){
        while (reader.hasNextLine()){
            String line = reader.nextLine();
            if(line.equals("<eof>")){
                break;
            }
            recievedLine = line;
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
        Login login = new Login(client);
//        Application application = new Application();
    }
}

