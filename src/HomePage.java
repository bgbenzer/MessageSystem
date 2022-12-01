import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Iterator;

public class HomePage implements ActionListener{
    JFrame frame = new JFrame("Main Page");
    JLabel mainText = new JLabel("Welcome To MessageBox");
    JButton accessButton = new JButton("Access");
    JButton leaveAMessageButton = new JButton("Leave a message");

    JButton userRegisterButton = new JButton("User Register");

    HomePage(){
        mainText.setBounds(50,20,300,50);
        mainText.setFont(new Font("Arial", Font.BOLD, 23));
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
            try {

                byte[] jsonString = FileOps.readAsByte("users.txt");
                DES des = new DES(jsonString,"KEY");
                byte[] decryptString = des.decrypt("DES");
                String string = new String(decryptString);

                JSONParser jsonParser = new JSONParser();

                Object object2 = jsonParser.parse(string);
                JSONObject jsonObject = (JSONObject) object2;

                Object object = jsonObject.get("Users");
                JSONArray userArray = (JSONArray) object;
                String[] users = new String[userArray.size()];

                Iterator<JSONObject> iterator = userArray.iterator();
                int counter =0;
                while(iterator.hasNext()){
                   users[counter] = (String) iterator.next().get("Username");
                   counter++;
                }


                MessageRegisterPage messageRegisterPage = new MessageRegisterPage(users);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ParseException ex) {
                ex.printStackTrace();
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                noSuchAlgorithmException.printStackTrace();
            } catch (InvalidKeySpecException invalidKeySpecException) {
                invalidKeySpecException.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        else if(e.getSource() == userRegisterButton) {
            frame.dispose();
            UserRegisterPage userRegisterPage = new UserRegisterPage();
        }
    }
}
