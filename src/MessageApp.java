import javax.swing.*;

public class MessageApp extends JFrame {
    MessagePannel messagePannel = new MessagePannel();

    MessageApp() {
        this.add(messagePannel);
        this.setTitle("Messages");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
