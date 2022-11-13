import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage implements ActionListener{
    JFrame frame = new JFrame("Main Page");
    JLabel mainText = new JLabel("Welcome To MessageBox");
    JButton accessButton = new JButton("Access");
    JButton leaveAMessageButton = new JButton("Leave a message");

    JButton userRegisterButton = new JButton("User Register");

    HomePage(){
        mainText.setBounds(50,20,200,50);
        accessButton.setBounds(150,150,100,30);

        accessButton.addActionListener(this);

        leaveAMessageButton.setBounds(130, 200, 140, 50);
        leaveAMessageButton.addActionListener(this);

        userRegisterButton.setBounds(130,270,140,50);
        userRegisterButton.addActionListener(this);


        frame.add(mainText);
        frame.add(accessButton);
        frame.add(leaveAMessageButton);
        frame.add(userRegisterButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == accessButton) {
            frame.dispose();
            AccessPage accessPage = new AccessPage();
        }
        else if(e.getSource() == leaveAMessageButton) {
            frame.dispose();
            MessageRegisterPage messageRegisterPage = new MessageRegisterPage();
        }
        else if(e.getSource() == userRegisterButton) {
            frame.dispose();
            UserRegisterPage userRegisterPage = new UserRegisterPage();
        }
    }
}
