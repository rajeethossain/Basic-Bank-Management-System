import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        Account account = new Account();
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server Running");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Server Connected");
                InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                Scanner scanner = new Scanner(isr);
                OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
                PrintWriter printWriter = new PrintWriter(osw);

                while (scanner.hasNextLine()) {
                    String comand = scanner.nextLine();
                if(comand.equals("Search")){
                    String id = scanner.nextLine();
                    try {
                        Scanner filescan = new Scanner(account.file);
                        int flag = 0;
                        while (filescan.hasNextLine()) {
                            String line = filescan.nextLine();
                            if (line.contains(id)) {
                                printWriter.write(line+"\n");
                                printWriter.flush();
                                printWriter.write("<eof>\n");
                                printWriter.flush();
                                System.out.println("Found");
                                flag = 1;
                            }
                        }
                        if(flag== 0){
                            printWriter.write("Not Found\n");
                            printWriter.flush();
                        }
                        filescan.close();
                    }
                    catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if( comand.equals("Login")){
                        String idpw = scanner.nextLine();
                        try {
                            Scanner filescan = new Scanner(account.file);
                            int flag = 0;
                            while (filescan.hasNextLine()) {
                                String line = filescan.nextLine();
                                if (line.contains(idpw)) {
                                    printWriter.write("Login Successful\n");
                                    printWriter.flush();
                                    printWriter.write("<eof>\n");
                                    printWriter.flush();
                                    System.out.println("Success");
                                    flag = 1;
                                }
                            }
                            if(flag== 0){
                                printWriter.write("Login Unsuccessful\n");
                                printWriter.flush();
                                printWriter.write("<eof>\n");
                                printWriter.flush();
                            }
                            filescan.close();
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else if (comand.equals("Add")) {
                        String line = scanner.nextLine();
                        account.Add(line);
                        printWriter.write("Registration Successful\n");
                        printWriter.flush();
                        printWriter.write("<eof>\n");
                        printWriter.flush();
                    } else if (comand.equals("Remove")) {
                        String id = scanner.nextLine();
                        account.Remove(id);
                    } else if (comand.equals("Update")) {
                        String line = scanner.nextLine();
                        System.out.println(line);
                        account.Update(line);
                        printWriter.write("Account Updated\n");
                        printWriter.flush();
                        printWriter.write("<eof>\n");
                        printWriter.flush();
                    }
                }
            }
        }
        catch(IOException e){

        }
    }
}