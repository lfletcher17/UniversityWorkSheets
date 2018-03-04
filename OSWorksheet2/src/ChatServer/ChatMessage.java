package ChatServer;

public class ChatMessage {
	
	private String offset;
	private String user;
	private String time;
    private String content;

    public ChatMessage(String offset, String user, String time, String content) {
        this.offset = offset;
        this.user = user;
        this.time = time;
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public String getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public String getOffset() {
        return offset;
    }

}
