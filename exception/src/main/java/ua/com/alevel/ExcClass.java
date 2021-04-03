package ua.com.alevel;

import java.util.HashMap;
import java.util.Map;

public class ExcClass {

    static Map<Integer, Integer> parseNumber(String s) {
        try {
            Integer integer = Integer.parseInt(s);
            return new HashMap<>(0, integer);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return new HashMap<>(1, 0);
        }
    }
}
