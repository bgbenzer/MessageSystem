import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessagePage implements ActionListener {
    JFrame frame = new JFrame("Message");

    JTextArea messageArea = new JTextArea();


    JButton returnBtn = new JButton("Return");


    MessagePage(){
        messageArea.setBounds(50,50,250,250);

        JScrollPane messageScroll = new JScrollPane(messageArea);
        messageScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        messageScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        returnBtn.setBounds(130,330,140,30);

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
