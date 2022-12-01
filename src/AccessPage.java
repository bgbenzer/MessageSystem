import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AccessPage implements ActionListener {
    JFrame frame = new JFrame("Message View");

    JLabel messageCodename = new JLabel("Message Codename");
    JTextField msgCodenameField = new JTextField();

    JLabel messagePassword = new JLabel("Message Password");
    JTextField msgPasswordField = new JTextField();

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
        msgPasswordField.setBounds(190,70,150,30);

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
        frame.add(msgPasswordField);

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

            String messageId = msgCodenameField.getText();
            String messagePassword = msgPasswordField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();

            try {

                String content = (String) Message.readMessage(messageId, messagePassword, username, password);
                if(!content.equals("Please check your information and try again")){
                    MessagePage messagePage = new MessagePage(content);
                }else{
                    frame.dispose();
                    ErrorPage errorPage = new ErrorPage(content);
                }




            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ParseException ex) {
                ex.printStackTrace();
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }


        }
        else if(e.getSource() == reset){
            msgCodenameField.setText("");
            msgPasswordField.setText("");
            usernameField.setText("");
            passwordField.setText("");
        }
    }
}
