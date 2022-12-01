import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class FileOps {

    public static byte[] readAsByte(String fileName) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(fileName));
        return bytes;
    }

    public static void writeAsByte(byte[] data, String outputFileName) throws IOException { //TODO
        Files.write(Paths.get(outputFileName),data);
    }

    public static String[] readFile(String path) {

        try {
            int i = 0;
            int length = Files.readAllLines(Paths.get(path)).size();
            String[] results = new String[length];								//Reading files.
            for (String line : Files.readAllLines(Paths.get(path))) {
                results[i++] = line;
            }
            return results;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void writeToFile(String str1, String outputFile){

        File file = new File(outputFile);

        try{

            file.createNewFile();
            FileWriter writer = new FileWriter(outputFile,true);

            writer.write(str1);
            writer.write("\n");

            writer.close();
        }
        catch(IOException e){
            System.out.println("error");
        }

    }

    public static void jsonWriter(JSONObject object, String outputFile) throws IOException {

        FileWriter fileWriter = new FileWriter(outputFile);
        fileWriter.write(object.toString());
        fileWriter.close();
    }

    public static JSONObject jsonReader(String outputFile) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader fileReader = new FileReader(outputFile);

        Object object = jsonParser.parse(fileReader);
        JSONObject jsonObject = (JSONObject) object;
        return jsonObject;
    }


    public static String getHash(String originalString) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = digest.digest(
                originalString.getBytes(StandardCharsets.UTF_8));
        String shaHex = bytesToHex(hashedBytes);

        return shaHex;
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
