import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccessPage implements ActionListener {
    JFrame frame = new JFrame("Message View");

    JLabel messageCodename = new JLabel("Message Codename");
    JTextField msgCodenameField = new JTextField();

    JLabel messagePassword = new JLabel("Message Password");
    JTextField mgsPasswordField = new JTextField();

    JLabel username = new JLabel("Username");
    JTextField usernameField = new JTextField();

    JLabel password = new JLabel("Password");
    JPasswordField passwordField = new JPasswordField();
    JCheckBox showPassword = new JCheckBox("Show Password");

    JButton view = new JButton("View");
    JButton reset = new JButton("Reset");
    JButton home = new JButton("Home");


    AccessPage(){
        messageCodename.setBounds(30,20,150,30);
        msgCodenameField.setBounds(190,20,150,30);

        messagePassword.setBounds(30,70,150,30);
        mgsPasswordField.setBounds(190,70,150,30);

        username.setBounds(30,120,150,30);
        usernameField.setBounds(190,120,150,30);

        password.setBounds(30,170,150,30);
        passwordField.setBounds(190,170,150,30);
        showPassword.setBounds(190,220,150,30);

        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(showPassword.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                }
                else if(!showPassword.isSelected()){
                    passwordField.setEchoChar('•');
                }
            }
        });

        view.setBounds(50,270,100,30);
        reset.setBounds(250,270,100,30);
        home.setBounds(150,320,100,30);

        view.addActionListener(this);
        reset.addActionListener(this);
        home.addActionListener(this);

        frame.add(messageCodename);
        frame.add(msgCodenameField);

        frame.add(messagePassword);
        frame.add(mgsPasswordField);

        frame.add(username);
        frame.add(usernameField);

        frame.add(password);
        frame.add(passwordField);
        frame.add(showPassword);

        frame.add(view);
        frame.add(reset);
        frame.add(home);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == home) {
            frame.dispose();
            HomePage homePage = new HomePage();
        }
        else if(e.getSource() == view) {
            frame.dispose();
            MessagePage messagePage = new MessagePage();
        }
        else if(e.getSource() == reset){
            msgCodenameField.setText("");
            mgsPasswordField.setText("");
            usernameField.setText("");
            passwordField.setText("");
        }
    }
}
