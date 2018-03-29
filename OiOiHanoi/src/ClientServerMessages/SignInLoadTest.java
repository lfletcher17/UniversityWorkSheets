package ClientServerMessages;

import Server.*;
import Client.*;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SignInLoadTest {

    public static void main(String[] args) {
        Server server = new Server("localhost", 8080, 100);
        ArrayList<Client> clients = new ArrayList<Client>();
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < 100; i++) {
            try {
                Client client = new Client("localhost", 8080);
                clients.add(client);
                System.out.println(i + " clients started");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int counter = 0;
        int avatar = 1;
        for(Client client: clients) {
            String emailAddress = "test" + counter + "@test" + counter + ".com";
            String password = "password";
            client.signIn(emailAddress, password);
            counter ++;
            avatar ++;
            System.out.println(counter + " users signed in");
        }
    }


}

