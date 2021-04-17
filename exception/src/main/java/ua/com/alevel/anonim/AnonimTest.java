package ua.com.alevel.anonim;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AnonimTest {

    static AnonimMinimal anonimMinimal = () -> { };

    static AnonimSumAndPrint anonimSumAndPrint = (a, b) -> {
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        int sum = a + b;
        System.out.println("sum = " + sum);
        return sum;
    };

    static AnonimSumAndPrint anonimSumAndPrint1 = new AnonimSumAndPrint() {
        @Override
        public int sumAndPrint(int a, int b) {
            System.out.println("a = " + a);
            System.out.println("b = " + b);
            int sum = a + b;
            System.out.println("sum = " + sum);
            return sum;
        }
    };

    static AnonimPrint anonimPrint = (s) -> System.out.println("s = " + s);

    static AnonimPrint anonimPrint1 = new AnonimPrint() {
        @Override
        public void print(String s) {
            System.out.println("s = " + s);
        }
    };

    static Anonim anonim1 = (a, b) -> a + b;

    static Anonim anonim2 = new Anonim() {
        @Override
        public int sum(int a, int b) {
            return a + b;
        }
    };

    static Anonim anonim3 = new AnonimImpl();

    public static void main(String[] args) {
        System.out.println("anonim1 = " + anonim1.sum(4,8));
        System.out.println("anonim2 = " + anonim2.sum(4,8));


        int sum = anonimSumAndPrint.sumAndPrint(7, 9);


        List<Integer> integers = new ArrayList<>();







        List<Integer> collect = integers.stream()
                .filter(Objects::nonNull)
                .distinct()
                .skip(10)
                .filter(integer -> integer % 2 == 0)
                .sorted()
                .collect(Collectors.toList());


        List<String> strings = new ArrayList<>();

        boolean existStr = strings.stream().allMatch(s -> s.contains("gfjso"));







        int sum1 = integers.stream().reduce(0,Integer::sum);
        int sum2 = integers.stream().parallel().reduce(0,Integer::sum);
        int sum3 = integers.parallelStream().reduce(0,Integer::sum);
    }
}
