import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessagePage implements ActionListener {
    JFrame frame = new JFrame("Message");

    JTextArea messageArea = new JTextArea(10,10);



    JButton returnBtn = new JButton("Return");


    MessagePage(){
        messageArea.setBounds(25,25,335,270);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);

        returnBtn.setBounds(130,310,140,30);

        returnBtn.addActionListener(this);

        frame.add(messageArea);
        frame.add(returnBtn);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == returnBtn) {
            frame.dispose();
            HomePage homePage = new HomePage();
        }
    }
}
