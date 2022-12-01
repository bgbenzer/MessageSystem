import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Message {

    private String message_id;
    private byte[] content;
    private String password;
    private String receiver;

    public Message(String message_id, byte[] content, String password, String receiver) throws NoSuchAlgorithmException {
        this.message_id = message_id;
        this.content = content;
        this.password = FileOps.getHash(password);
        this.receiver = receiver;
    }

    public static String createMessage(String message_id, byte[] content, String password, String receiver,String confirmPassword) throws Exception {

        Message message = new Message(message_id,content,password,receiver);
        byte[] jsonString = FileOps.readAsByte("messages.txt");
        DES des2 = new DES(jsonString, "KEY");
        byte[] decryptString = des2.decrypt("DES");
        String string = new String(decryptString);

        JSONParser jsonParser = new JSONParser();
        Object object2 = jsonParser.parse(string);
        JSONObject jsonObject = (JSONObject) object2;

        Object object = jsonObject.get("Messages");
        JSONArray messagesArray = (JSONArray) object;

        Iterator<JSONObject> iterator = messagesArray.iterator();
        while(iterator.hasNext()){ // checking unique id
            if(iterator.next().get("ID").equals(message_id)){
//                System.out.println("This id is already taken");
                return "This id is already taken";
            }
        }

        DES des = new DES(content, password);

        byte[] cipherText = des.encrypt("DES");
        message.content = cipherText;

        if(!message.password.equals(FileOps.getHash(confirmPassword))){
            System.out.println("Password is not equal with confirmation");
            return "Password is not equal with confirmation";
        }

        System.out.println("Successfully messaged");
        messagesArray.add(message.toJsonObject());
        jsonObject.replace("Messages",messagesArray);

        String finalString = jsonObject.toString();
        DES des3 = new DES(finalString.getBytes(),"KEY");
        byte[] encryptedString = des3.encrypt("DES");
        FileOps.writeAsByte(encryptedString,"messages.txt");

        return  "true";
    }

    public static String readMessage(String message_id, String messagePassword, String userName, String password) throws Exception {

        byte[] jsonString = FileOps.readAsByte("messages.txt");
        DES des2 = new DES(jsonString, "KEY");
        byte[] decryptString = des2.decrypt("DES");
        String string = new String(decryptString);

        JSONParser jsonParser = new JSONParser();
        Object object2 = jsonParser.parse(string);
        JSONObject jsonObject = (JSONObject) object2;
        Object object = jsonObject.get("Messages");
        JSONArray messagesArray = (JSONArray) object;

        byte[] finalContent = new byte[0];

        Iterator<JSONObject> iterator = messagesArray.iterator();
        while(iterator.hasNext()){ // checking unique id
            JSONObject message = iterator.next();
            if(message.get("ID").equals(message_id) && message.get("Password").equals(FileOps.getHash(messagePassword)) && User.logIn(userName,password).equals("true") ){
                Object object3 = message.get("Content");
                JSONArray contentArray = (JSONArray) object3;

                finalContent = new byte[contentArray.size()];

                for (int i = 0; i < contentArray.size(); i++) {
                    Long lon = ((long) contentArray.get(i));
                    finalContent[i] = lon.byteValue();
                }

                DES des = new DES(finalContent, messagePassword);

                byte[] plaintText = des.decrypt("DES");
                String stringPlainText = new String(plaintText);

                return stringPlainText;
            }
        }
        return "Please check your information and try again";
    }

    public JSONObject toJsonObject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ID", message_id);
        jsonObject.put("Password", password);
        jsonObject.put("Receiver", receiver);

        JSONArray jsonArray = new JSONArray();
        for(byte b:content){
            jsonArray.add(b);
        }

        jsonObject.put("Content", jsonArray );
        return jsonObject;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
