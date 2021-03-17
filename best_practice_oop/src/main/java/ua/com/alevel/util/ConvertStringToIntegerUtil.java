package ua.com.alevel.util;

import java.math.BigInteger;

public class ConvertStringToIntegerUtil {

    public static BigInteger convert(String value) {
        try {
            return new BigInteger(value);
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
