package ua.com.alevel;

import java.util.Random;

public class T implements Runnable {
    @Override
    public void run() {
        Random random = new Random();
        int i = random.nextInt(2);
        System.out.println("i = " + i);
        try {
            System.out.println(10 / i);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        System.out.println("finish");
    }
}
