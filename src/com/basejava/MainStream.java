package com.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainStream {

    private static int[] array = {1, 3, 2, 6, 1, 5, 4, 9, 7, 8, 8};
    private static List<Integer> list = new ArrayList<>(Arrays.asList(2, 3, 1, 4));

    public static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce((x, y) -> (10 * x + y)).getAsInt();
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        final Map<Boolean, List<Integer>> oddsAndEvens = integers.stream()
                .collect(Collectors.partitioningBy(i -> i % 2 == 0));
        return oddsAndEvens.get(oddsAndEvens.get(false).size() % 2 != 0);
    }

    public static void main(String[] args) {
        System.out.println(minValue(array));
        System.out.println(oddOrEven(list));
    }
}
