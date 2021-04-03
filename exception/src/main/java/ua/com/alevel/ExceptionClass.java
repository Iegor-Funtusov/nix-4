package ua.com.alevel;

public class ExceptionClass {

    public void test(String number) throws NumberFormatException {
        System.out.println("number = " + number);
        Integer integer = Integer.parseInt(number);
        System.out.println("integer = " + integer);
    }
}
