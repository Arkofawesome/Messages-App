import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private static String username;
    private static String target;
    private static MessagePannel messagePannel;
    public Client(Socket socket, String username, String target) {
        try {

            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
            this.target = target;
        }
        catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessage() {
        try {
            String message = "";
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            bufferedWriter.write(target);
            bufferedWriter.newLine();
            bufferedWriter.flush();

//            Scanner scanner = new Scanner(System.in);

            while (socket.isConnected()) {
                if (messagePannel.newMessage) {
//                    System.out.println("THE WHILE LOOP WORKS!!!");
                    bufferedWriter.write(username + ": " + messagePannel.getMessage());
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    messagePannel.setMessageBool(false);
                }

//                System.out.println("THE WHILE LOOP WORKS!!!");
            }
        }
        catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;

                while (socket.isConnected()) {
                    try {
                        msgFromGroupChat = bufferedReader.readLine();
                        messagePannel.receiveMessage(msgFromGroupChat);
                        System.out.println(msgFromGroupChat);
                    }
                    catch (IOException e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getInfo() {
        messagePannel = new MessagePannel();
        new MessageApp(messagePannel);
        final Object lock = new Object();
        while (username == null || username.equals("")) {
//            System.out.println("username is " +username);
            messagePannel.repaint();
            username = messagePannel.username;
        }
        System.out.println("Username is: " + username);
        while (target == null || target.equals("")) {
//            System.out.println("target is " +target);
            messagePannel.repaint();
            target = messagePannel.target;
        }
        System.out.println("Target is: " + target);
    }
    public static void main(String[] args) throws IOException{
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter your username: ");
//        String username = scanner.nextLine();
//        System.out.println("Who is Your message directed to: ");
//        String target = scanner.nextLine();
        getInfo();
        System.out.println(username);
        System.out.println(target);
        Socket socket = new Socket("localhost", 1234);
        Client client = new Client(socket, username, target);
        client.listenForMessage();
        client.sendMessage();
    }
}
