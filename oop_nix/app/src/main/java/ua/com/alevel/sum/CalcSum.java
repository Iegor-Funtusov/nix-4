package ua.com.alevel.sum;

import ua.com.alevel.service.CalcService;
import ua.com.alevel.service.factory.CalcFactory;

import java.math.BigInteger;

public class CalcSum {

    private final CalcService calcService = CalcFactory.getInstance().getCalcService();

    public void run() {
        BigInteger a = new BigInteger(String.valueOf(Long.MAX_VALUE));
        BigInteger b = new BigInteger(String.valueOf(Long.MAX_VALUE));

        BigInteger res = calcService.sum(a, b);

        System.out.println("res = " + res);
    }
}
