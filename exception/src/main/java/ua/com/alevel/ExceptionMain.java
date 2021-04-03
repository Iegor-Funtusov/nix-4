package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class ExceptionMain {

    public static void main(String[] args) {

        ThreadExc threadExc = new ThreadExc();
        threadExc.test();

//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            String s = reader.readLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                reader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        try(BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in))) {
//            String s = reader1.readLine();
//        } catch (IOException e) {
//
//        }
//
//        ExceptionClass exceptionClass = new ExceptionClass();
//        try {
//            System.out.println(test());
//            exceptionClass.test("10");
//            System.out.println(10 / 0);
//        } catch (NumberFormatException exception) {
//            throw new RuntimeException(exception.getMessage());
//        } catch (ArithmeticException exception) {
//            throw new RuntimeException(exception.getMessage());
//        } catch (Exception exception) {
//            throw new RuntimeException(exception.getMessage());
//        } finally {
//            System.out.println("finally");
//        }
//        System.out.println("ExceptionMain.main");
    }

    public static int test() {
        try {
            System.exit(12);
            System.out.println(10/0);
        } catch (ArithmeticException e) {
            return 10;
        } finally {
            System.out.println("ExceptionMain.test");
            return 1;
        }
//        return 10;
    }
}
