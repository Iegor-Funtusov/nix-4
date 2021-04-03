package ua.com.alevel;

public class ThreadExc {

    public void test() {
        for (int i = 0; i < 10; i++) {
            T t = new T();
            try {
                t.run();
            } catch (Exception e) {
                System.out.println("e = " + e.getMessage());
            }

        }
    }
}
