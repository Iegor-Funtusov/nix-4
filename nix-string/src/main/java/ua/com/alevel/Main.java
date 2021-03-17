package ua.com.alevel;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        String s = null;

        if (s == null) {

        }

        if (Objects.isNull(s)) {

        }

        String s1 = "fkjds;fdsf ds;kjfhdsahfsa ljdf;s fd;usahf;s";

        String[] strings = s1.split(" ");

        Arrays.stream(strings).filter(s2 -> StringUtils.containsIgnoreCase(s2, "gg")).collect(Collectors.toList());


        List<String> list1 = Arrays.stream(strings).filter(StringUtils::isNotBlank).collect(Collectors.toList());

        List<String> list2 = Arrays.stream(strings).filter(s2 -> !s2.isBlank()).collect(Collectors.toList());

        List<String> list3 = Arrays
                .stream(strings)
                .filter(Objects::nonNull)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toList());

        List<String> list4 = Arrays
                .stream(strings)
                .filter(s2 -> s2 != null)
                .filter(s2 -> !s2.isBlank())
                .collect(Collectors.toList());

    }
}
