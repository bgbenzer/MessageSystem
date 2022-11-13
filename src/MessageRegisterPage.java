import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageRegisterPage implements ActionListener {
    JFrame frame = new JFrame("Register Form");

    JLabel authUsername = new JLabel("Auth. Username");
    JComboBox authUsernameField = new JComboBox();

    JLabel messagePassword = new JLabel("Password");
    JTextField mgsPasswordField = new JTextField();

    JLabel messageConfirmPassword = new JLabel("Confirm Password");
    JTextField messageConfirmField = new JTextField();

    JLabel messageCodename = new JLabel("Message Codename");
    JTextField messageCodenameField = new JTextField();


    JButton returnBtn = new JButton("Return");


    MessageRegisterPage() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
