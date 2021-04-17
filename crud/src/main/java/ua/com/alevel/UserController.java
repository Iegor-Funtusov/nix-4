package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserController {

    private final UserService userService = new UserService();

    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hello!");
        System.out.println("Select you event");
        String position;
        while ((position = reader.readLine()) != null) {
            switch (position) {
                case "0" : {
                    System.exit(0);
                }
                case "1" : create(reader); break;
                case "2" : read(); break;
                case "3" : update(reader); break;
                case "4" : delete(reader); break;
            }
            System.out.println("Your variant: if you want exit, please input 0, else, repeat logic");
        }
        reader.close();
    }

    private void create(BufferedReader reader) throws IOException {
        System.out.println("create");
        System.out.println("please enter your name ...");
        User user = new User();
        String name = reader.readLine();
        System.out.println("please enter your email ...");
        String email = reader.readLine();
        System.out.println("please enter your age ...");
        String s = reader.readLine();
        int age = Integer.parseInt(s);
        user.setName(name);
        user.setEmail(email);
        user.setAge(age);
        userService.create(user);
    }

    private void update(BufferedReader reader) throws IOException {
        System.out.println("update");
        System.out.println("please enter your id ...");
        String idS = reader.readLine();
        int id = Integer.parseInt(idS);
        User user = userService.read(id);
        System.out.println("please enter your name ...");
        String name = reader.readLine();
        System.out.println("please enter your email ...");
        String email = reader.readLine();
        System.out.println("please enter your age ...");
        String s = reader.readLine();
        int age = Integer.parseInt(s);
        user.setName(name);
        user.setEmail(email);
        user.setAge(age);
        userService.update(user);
    }

    private void delete(BufferedReader reader) throws IOException {
        System.out.println("update");
        System.out.println("please enter your id ...");
        String idS = reader.readLine();
        int id = Integer.parseInt(idS);
        userService.delete(id);
    }

    private void read() {
        System.out.println("read all");
        System.out.println(userService.read());
    }
}
