package ua.com.alevel.controller;

import ua.com.alevel.service.SumService;
import ua.com.alevel.service.SumServiceImpl;
import ua.com.alevel.util.ConvertStringToIntegerUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class ConsoleController {

    private final SumService sumService = new SumServiceImpl();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void read() throws Exception {
        try {
            System.out.println("Please, check your operations:");
            System.out.println("if you need sum, please enter a 1");
            System.out.println("if you need multiply, please enter a 2");

            String operation;

            while ((operation = reader.readLine()) != null) {
                switchOperation(operation);
                operation = reader.readLine();
                switch (operation) {
                    case "0" : {
                        System.exit(0);
                    }
                    case "1" : {
                        switchOperation(operation);
                    }
                }
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private void switchOperation(String operator) throws Exception {
        switch (operator) {
            case "1" : {
                readSum();
                break;
            }
            case "2" : {
                readMultiply();
                break;
            }
            default: {
                throw new RuntimeException("durak");
            }
        }
        System.out.println("Your variant: if you want exit, please input 0, else, repeat logic");
    }

    public void readSum() throws Exception {
        System.out.println("Please, enter a valid value");

        String left = reader.readLine();

        System.out.println("Please, enter a valid value");

        String right = reader.readLine();

        BigInteger a = ConvertStringToIntegerUtil.convert(left);
        BigInteger b = ConvertStringToIntegerUtil.convert(right);

        System.out.println("sum = " + sumService.sum(a, b));
    }
    public void readMultiply() throws Exception {
        System.out.println("Please, enter a valid value");

        String left = reader.readLine();

        System.out.println("Please, enter a valid value");

        String right = reader.readLine();

        BigInteger a = ConvertStringToIntegerUtil.convert(left);
        BigInteger b = ConvertStringToIntegerUtil.convert(right);

        System.out.println("multi = " + sumService.multi(a, b));
    }
}
