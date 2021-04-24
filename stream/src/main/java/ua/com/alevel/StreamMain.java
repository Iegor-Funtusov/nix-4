package ua.com.alevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMain {

    public static void main(String[] args) {

//        UserTest userTest = new UserTest();
//        userTest.test();



//        System.out.println("StreamMain.main");

//        List<Integer> integers = new ArrayList<>();
//        List<Integer> integers1 = new ArrayList<>();
//        for (int i = 0; i < 2_000_000_000; i++) {
//            integers.add(i * new Random(i).nextInt(10));
//            integers1.add(i * new Random(i).nextInt(10));
//        }
//        System.out.println("integers = " + integers);

//        long start = System.currentTimeMillis();
//        int sum = integers
//                .stream()
//                .reduce(Integer::sum)
//                .get();
//
//        long end = System.currentTimeMillis() - start;
//
//        System.out.println("stream");
//        System.out.println("end = " + end);

//        start = System.currentTimeMillis();
//        sum = integers
//                .parallelStream()
//                .reduce(Integer::sum)
//                .get();
//
//        end = System.currentTimeMillis() - start;
//        System.out.println("parallelStream");
//        System.out.println("end = " + end);

//        start = System.currentTimeMillis();
//        sum = integers
//                .stream().parallel()
//                .reduce(Integer::sum)
//                .get();

//        end = System.currentTimeMillis() - start;
//        System.out.println("stream().parallel()");
//        System.out.println("end = " + end);
//        System.out.println("sum = " + sum);

//        System.out.println("integers1 = " + integers1);
//
//
//        List<Integer> resInt = Stream
//                .concat(integers.stream(), integers1.stream())
//                .sorted()
//                .collect(Collectors.toList());
//
//        System.out.println("resInt = " + resInt);
//
//
//        System.out.println(integers.stream().anyMatch(integer -> integer == 5));
//
//        integers = integers
//                .stream()
//                .distinct()
//                .filter(integer -> integer != 5)
//                .sorted()
//                .collect(Collectors.toList());
//
//        String res = integers
//                .stream()
//                .map(Object::toString)
//                .collect(Collectors.joining());
//
//        System.out.println("list = " + integers);
//        System.out.println("res = " + res);
//
//        res = res
//                .chars()
//                .skip(25)
//                .mapToObj(value -> (char) value)
//                .filter(character -> !character.equals('2'))
//                .map(Objects::toString)
//                .collect(Collectors.joining());
//
//        System.out.println("res = " + res);
//
//        Map<Integer, String> map = new HashMap<>();
//
//        for (int i = 0; i < 100; i++) {
//            map.put(i, UUID.randomUUID().toString());
//        }

//        map.forEach((key, value) -> {
//            System.out.println("key = " + key);
//            System.out.println("value = " + value);
//        });


    }
}
