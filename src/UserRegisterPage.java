import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegisterPage implements ActionListener {
    JFrame frame = new JFrame("User Register");

    JLabel username = new JLabel("Username");
    JTextField usernameField = new JTextField();

    JLabel password = new JLabel("Password");
    JTextField passwordField = new JTextField();

    JLabel confirmPassword = new JLabel("Confirm Password");
    JTextField confirmPasswordField = new JTextField();

    JButton register = new JButton("Register");

    JButton homePageButton = new JButton("Home");

    UserRegisterPage(){
        username.setBounds(30,40,150,30);
        usernameField.setBounds(190,40,150,30);

        password.setBounds(30,90,150,30);
        passwordField.setBounds(190,90,150,30);

        confirmPassword.setBounds(30,140,150,30);
        confirmPasswordField.setBounds(190,140,150,30);

        register.setBounds(150, 200, 100, 30);
        register.addActionListener(this);

        homePageButton.setBounds(150, 250, 100, 30);
        homePageButton.addActionListener(this);

        frame.add(username);
        frame.add(usernameField);

        frame.add(password);
        frame.add(passwordField);

        frame.add(confirmPassword);
        frame.add(confirmPasswordField);

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

        }
        else if(e.getSource() == homePageButton) {
            frame.dispose();
            HomePage homePage = new HomePage();
        }
    }
}
