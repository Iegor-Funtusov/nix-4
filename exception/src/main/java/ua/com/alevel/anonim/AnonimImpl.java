package ua.com.alevel.anonim;

public class AnonimImpl implements Anonim {

    private static final String H = "kjflsaufd";

    @Override
    public int sum(int a, int b) {
        System.clearProperty(H);
        return a + b;
    }
}
