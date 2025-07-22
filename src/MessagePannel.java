import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MessagePannel extends JPanel implements ActionListener {
    final int SCREEN_WIDTH = 600;
    final int SCREEN_HEIGHT = 800;
    final int CONTACT_WIDTH = 60;
    final int CONTACT_HEIGHT = 30;
    final int MSG_BOX_WIDTH = 400;
    final int MSG_BOX_HEIGHT = 50;
    final int NAME_BOX_WIDTH = 200;
    final int NAME_BOX_HEIGHT = 50;
    int lastMessageHeight = 50;
    ArrayList<ClientHandler> clients = new ArrayList<>();
    ArrayList<JLabel> messages = new ArrayList<>();
    JTextField usernameEnter = new JTextField(10);
    JTextField messageField = new JTextField(25);
    static String username;
    static String target;
    String currentMessage = "";
    boolean newMessage = false;


    MessagePannel() {
//        this.addKeyListener(new MyKeyAdapter());
        this.setLayout(null);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        start();
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){

    }
    public void start() {
        initAccount();

    }

    public void drawClients() {
        clients = ClientHandler.clientHandlers;
        System.out.println(clients.size());
        int x = 0;
        int y = 100;
        for (int i = 0; i < clients.size() ; i++) {
            JButton button;
            if (!(x + CONTACT_WIDTH <= SCREEN_WIDTH)) {
                x = 0;
                y+= CONTACT_HEIGHT;
            }
            button = new JButton(clients.get(i).getClientUsername());
            button.setBounds(x,y,CONTACT_WIDTH,CONTACT_HEIGHT);
            button.setVisible(true);
            int finalI = i;
            button.addActionListener(event -> {
                System.out.println(clients.get(finalI).getClientUsername());
            });
            add(button);
            x+=CONTACT_WIDTH;

        }
    }
//    public void setCurrentMessage(String currentMessage) {
//        this.currentMessage = currentMessage;
//    }

    public void setMessageBool(boolean bool) {
        newMessage = bool;
    }
    public String getMessage() {
        return currentMessage;
    }

    public void initAccount() {
        //JLabel to direct the user
        JLabel usernameText = new JLabel("Enter your username: ");
        usernameText.setBounds(100,200, 300, NAME_BOX_HEIGHT);
        usernameText.setFont(new Font("Arial", Font.BOLD, 16));
        usernameText.setVisible(true);
        this.add(usernameText);
        usernameEnter.setBounds(300,200,NAME_BOX_WIDTH, NAME_BOX_HEIGHT);
        usernameEnter.setVisible(true);
        usernameEnter.setFont(new Font("Arial", Font.BOLD, 16));
        usernameEnter.setFocusable(true);
        usernameEnter.setMargin(new Insets(5,10,5,10));
        this.add(usernameEnter);
        usernameEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Checking what text box it was pressed for
                if (usernameEnter.isVisible()) {
                    if(username == null) {
                        username = usernameEnter.getText();
                        System.out.println(username);
                        usernameEnter.setText("");
                        usernameText.setText("Who is Your message directed to: ");
                        usernameText.setBounds(25,200, 300, NAME_BOX_HEIGHT);
                    }
                    else if (target == null) {
                        target = usernameEnter.getText();
                        usernameEnter.setVisible(false);
                        usernameText.setVisible(false);
                        System.out.println(target);
                        msg_Box();

                    }
                }
                repaint();

            }
        });
    }

    public void msg_Box() {
        drawClients();
        messageField.setBounds(SCREEN_WIDTH / 2 - MSG_BOX_WIDTH / 2, 500, MSG_BOX_WIDTH, MSG_BOX_HEIGHT);
        messageField.setVisible(true);
        messageField.setFont(new Font("Arial", Font.BOLD, 16));
        messageField.setFocusable(true);
        messageField.setMargin(new Insets(5,10,5,10));
        this.add(messageField);
        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel msg = new JLabel(messageField.getText());
                currentMessage = messageField.getText();
                newMessage = true;
//                System.out.println("msg: " + messageField.getText());
                messageField.setText("");
                msg.setFont(new Font("Arial", Font.BOLD, 16));

                // Get Dimensions of the msg
                Dimension size = msg.getPreferredSize();
                msg.setBounds(SCREEN_WIDTH - (int) size.getWidth() - 10, lastMessageHeight, (int) size.getWidth(), (int) size.getHeight());
                lastMessageHeight += size.height + 10;

                msg.setVisible(true);
                add(msg);
                repaint();
            }
        });
    }

    public void receiveMessage(String message) {
        JLabel msg = new JLabel(message);
        msg.setFont(new Font("Arial", Font.BOLD, 16));

        // Get Dimensions of the msg
        Dimension size = msg.getPreferredSize();
        msg.setBounds(10, lastMessageHeight, (int) size.getWidth(), (int) size.getHeight());
        lastMessageHeight += size.height + 10;

        msg.setVisible(true);
        add(msg);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

//    public class MyKeyAdapter extends KeyAdapter {
//        @Override
//        public void keyPressed(KeyEvent e){
//            System.out.println("Hello!");
//            // Checking if the enter key has been pressed
//
//
//
//        }
//    }
}
