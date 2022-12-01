import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class UserRegisterPage implements ActionListener {
    JFrame frame = new JFrame("User Register");

    JLabel username = new JLabel("Username");
    JTextField usernameField = new JTextField();

    JLabel password = new JLabel("Password");
    JPasswordField passwordField = new JPasswordField();

    JCheckBox showPassword = new JCheckBox("Show Password");

    JLabel confirmPassword = new JLabel("Confirm Password");
    JPasswordField confirmPasswordField = new JPasswordField();

    JCheckBox checkShowPassword = new JCheckBox("Show Password");

    JButton register = new JButton("Register");

    JButton homePageButton = new JButton("Home");

    UserRegisterPage(){
        username.setBounds(30,40,150,30);
        usernameField.setBounds(190,40,150,30);

        password.setBounds(30,90,150,30);
        passwordField.setBounds(190,90,150,30);
        showPassword.setBounds(190, 115, 150,30);

        confirmPassword.setBounds(30,165,150,30);
        confirmPasswordField.setBounds(190,165,150,30);
        checkShowPassword.setBounds(190,190,150,30);

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

        checkShowPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkShowPassword.isSelected()) {
                    confirmPasswordField.setEchoChar((char) 0);
                }
                else if(!checkShowPassword.isSelected()){
                    confirmPasswordField.setEchoChar('•');
                }
            }
        });

        register.setBounds(150, 250, 100, 30);
        register.addActionListener(this);

        homePageButton.setBounds(150, 300, 100, 30);
        homePageButton.addActionListener(this);

        frame.add(username);
        frame.add(usernameField);

        frame.add(password);
        frame.add(passwordField);
        frame.add(showPassword);

        frame.add(confirmPassword);
        frame.add(confirmPasswordField);
        frame.add(checkShowPassword);

        frame.add(register);
        frame.add(homePageButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == register) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            try {
                String string = User.register(username,password,confirmPassword);
                if(string.equals("true")) {
                    frame.dispose();
                    HomePage homePage = new HomePage();
                }else{
                    frame.dispose();
                    ErrorPage errorPage = new ErrorPage(string);
                }



            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        else if(e.getSource() == homePageButton) {
            frame.dispose();
            HomePage homePage = new HomePage();
        }
    }
}
