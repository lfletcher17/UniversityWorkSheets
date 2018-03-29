package Server;

import ClientServerMessages.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestMethods {

    public static void main (String[] args) {
        Map<String, String> onlineUsers = new HashMap<String, String>();
        onlineUsers.put("test1", "test1val");
        onlineUsers.put("test2", "test2val");
        onlineUsers.put("test3", "test3val");
        onlineUsers.put("test4", "test4val");
        ArrayList<String> usersAsList = convertOnlineUsersToList (onlineUsers);
        System.out.println(usersAsList.get(3));
    }

    public static ArrayList<String> convertOnlineUsersToList (Map<String,String> onlineUsers) {
        ArrayList<String> result = new ArrayList<String>();
        onlineUsers.keySet().forEach(e-> result.add(e));
        return result;
    }
}
