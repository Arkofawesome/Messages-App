import javax.swing.*;

public class MessageApp extends JFrame {

    MessageApp(MessagePannel messagePannel) {
        this.add(messagePannel);
        this.setTitle("Messages");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
