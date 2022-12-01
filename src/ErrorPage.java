import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorPage implements ActionListener {
    JFrame frame = new JFrame("Register Form");

    JLabel mainText;

    JButton returnBtn = new JButton("Return");

    ErrorPage(String errorContent){
        mainText = new JLabel(errorContent);
        mainText.setBounds(50,20,300,200);

        returnBtn.setBounds(130,310,140,30);
        returnBtn.addActionListener(this);

        frame.add(mainText);
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