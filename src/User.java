import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

public class User {

    private String username;
    private String password;

    public User(String username, String password) throws NoSuchAlgorithmException {
        this.username = username;
        this.password = FileOps.getHash(password);;
    }

    public static String register(String username,String password,String confirmPassword) throws Exception {

        User user = new User(username, password);

        byte[] jsonString = FileOps.readAsByte("users.txt");
        DES des = new DES(jsonString,"KEY");
        byte[] decryptString = des.decrypt("DES");
        String string = new String(decryptString);

        JSONParser jsonParser = new JSONParser();

        Object object2 = jsonParser.parse(string);
        JSONObject jsonObject = (JSONObject) object2;

        Object object = jsonObject.get("Users");
        JSONArray userArray = (JSONArray) object;

        Iterator<JSONObject> iterator = userArray.iterator();
        while(iterator.hasNext()){
            if(iterator.next().get("Username").equals(username)){
//                System.out.println("This username is already taken");

                return "This username is already taken";
            }
        }

        if(!user.getPassword().equals(FileOps.getHash(confirmPassword))){
//            System.out.println("Password is not equal with confirmation");
            return "Password is not equal with confirmation";
        }

        userArray.add(user.toJsonObject());

        jsonObject.replace("Users",userArray);

        String finalString = jsonObject.toString();

        DES des2 = new DES(finalString.getBytes(),"KEY");
        byte[] encryptedString = des2.encrypt("DES");
        FileOps.writeAsByte(encryptedString,"users.txt");

        return "true";
    }

    public static String logIn(String username, String password) throws Exception {

        User user = new User(username, password);
        byte[] jsonString = FileOps.readAsByte("users.txt");
        DES des = new DES(jsonString,"KEY");
        byte[] decryptString = des.decrypt("DES");
        String string = new String(decryptString);

        JSONParser jsonParser = new JSONParser();

        Object object2 = jsonParser.parse(string);
        JSONObject jsonObject = (JSONObject) object2;
        Object object = jsonObject.get("Users");
        JSONArray userArray = (JSONArray) object;

        Iterator<JSONObject> iterator = userArray.iterator();
        while(iterator.hasNext()){
            JSONObject iteratorItem = iterator.next();
            if(iteratorItem.get("Username").equals(user.getUsername()) && iteratorItem.get("Password").equals(user.getPassword())){
                return "true";
            }
        }
//        System.out.println("Username or password is wrong");
        return "Username or password is wrong";
    }

    public JSONObject toJsonObject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Username", username);
        jsonObject.put("Password", password);
        return jsonObject;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
