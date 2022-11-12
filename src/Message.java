public class Message {

    private String message_id;
    private String content;
    private String password;
    private User receiver;

    private Message(String message_id, String content, String password, User receiver) {
        this.message_id = message_id;
        this.content = content;
        this.password = password;
        this.receiver = receiver;
    }

    //visitors can only createMessage not read
    //users can do both


    public void createMessage(){
        //While creating a message, the visitor must provide a unique message id for the message
        //(it should be validated by the system) and declare an authorized user (receiver). Only the
        //authorized user can view the message.



    }

    public void readMessage(){
        //A user can access a message if s/he knows the password of the message and is authorized
        //by the sender(visitor) of the message.
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
