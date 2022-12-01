import org.json.simple.parser.ParseException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class MessageRegisterPage implements ActionListener {
    JFrame frame = new JFrame("Register Form");

    JLabel authUsername = new JLabel("Auth. Username*");
    JComboBox authUsernameField ;

    JLabel messagePassword = new JLabel("Password*");
    JTextField messagePasswordField = new JTextField();

    JLabel messageConfirmPassword = new JLabel("Confirm Password*");
    JTextField messageConfirmField = new JTextField();

    JLabel messageCodename = new JLabel("Message Codename*");
    JTextField messageCodenameField = new JTextField();

    JLabel enterMessage = new JLabel("ENTER YOUR MESSAGE*");
    JTextArea message = new JTextArea();


    JButton createMessageButton = new JButton("Create Message");
    JButton homePageButton = new JButton("Home");


    MessageRegisterPage(String[] users) throws IOException, ParseException {

        authUsernameField = new JComboBox(users);

        authUsername.setBounds(30,50,100,30);
        authUsernameField.setBounds(200,50,100,30);

        messagePassword.setBounds(30, 100, 150,30);
        messagePasswordField.setBounds(200,100,100, 30);

        messageConfirmPassword.setBounds(325,100, 150,30);
        messageConfirmField.setBounds(450,100,100,30);

        messageCodename.setBounds(30, 150, 150,30);
        messageCodenameField.setBounds(200,150, 100,30);

        enterMessage.setBounds(30, 230, 150,30);
        message.setBounds(200, 200, 300,200);
        message.setLineWrap(true);
        message.setWrapStyleWord(true);

        createMessageButton.setBounds(75,450, 150,30);
        createMessageButton.addActionListener(this);

        homePageButton.setBounds(375,450,150,30);
        homePageButton.addActionListener(this);

        frame.add(authUsername);
        frame.add(authUsernameField);

        frame.add(messagePassword);
        frame.add(messagePasswordField);

        frame.add(messageConfirmPassword);
        frame.add(messageConfirmField);

        frame.add(messageCodename);
        frame.add(messageCodenameField);

        frame.add(enterMessage);
        frame.add(message);

        frame.add(createMessageButton);
        frame.add(homePageButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createMessageButton) {

            try {
                String messageId = messageCodenameField.getText();
                String content = message.getText();
                String password = messagePasswordField.getText();
                String confirmPassword = messageConfirmField.getText();
                String receiver = String.valueOf(authUsernameField.getSelectedItem());

                String string = Message.createMessage(messageId, content.getBytes(), password,receiver, confirmPassword);
                if(string.equals("true")){
                    frame.dispose();
                    HomePage homePage = new HomePage();
                }else{
                    frame.dispose();
                    ErrorPage errorPage = new ErrorPage(string);
                }



            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        else if (e.getSource() == homePageButton) {
            frame.dispose();
            HomePage homePage = new HomePage();
        }
    }
}
