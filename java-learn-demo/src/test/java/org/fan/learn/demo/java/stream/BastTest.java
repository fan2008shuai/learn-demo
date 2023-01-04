package org.fan.learn.demo.java.stream;

import org.fan.learn.demo.java.stream.bean.Dish;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author author
 * @date 2022/11/19
 */
public class BastTest {

    private List<Dish> menu;

    @Before
    public void before() {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                // 炸薯条
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                // 对虾
                new Dish("prawns", false, 300, Dish.Type.FISH),
                // 三文鱼
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
    }

    @Test
    public void getHighCalories() {
        List<String> highDishes = menu.stream().filter(dish -> dish.getCalories() > 500)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName).collect(Collectors.toList());
        highDishes.forEach(System.out::println);
        System.out.println(highDishes);

        List<String> threeHighDishes = menu.stream().filter(dish -> dish.getCalories() > 300)
                .sorted(Comparator.comparing(Dish::getCalories, Comparator.reverseOrder()))
                .map(Dish::getName).limit(3).collect(Collectors.toList());
        System.out.println(threeHighDishes);
    }

    @Test
    public void testPrint() {
        List<String> threeHighDishes = menu.stream().filter(dish -> {
            System.out.println("in filter:" + dish.getName());
            return dish.getCalories() > 500;
        }).sorted((dish1, dish2) -> {
            System.out.println("in sorted:" + dish1.getName() + " vs " + dish2.getName());
            return dish2.getCalories() - dish1.getCalories();
        }).map(dish -> {
            System.out.println("in map:" + dish.getName());
            return dish.getName();
        }).limit(3).collect(Collectors.toList());
        threeHighDishes.forEach(System.out::println);
        System.out.println(threeHighDishes);
    }

    @Test
    public void testDistinct() {
        List<Integer> list = Arrays.asList(1, 2, 3, 3, 5, 2, 4);
        long count = list.stream().filter(i -> {
            System.out.println("in filter: " + i);
            return i % 2 == 0;
        } ).distinct().count();
        Assert.assertEquals(2, count);
    }

    @Test
    public void testFlatMap() {
        List<String> words = Arrays.asList("hello", "world");
        List<String> distinct = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct().collect(Collectors.toList());
        System.out.println("distinct: " + distinct);

        String[] wordArray = new String[] {"hello", "word"};
        Stream<String> stream = Arrays.stream(wordArray);
        stream.forEach(System.out::println);
    }

    @Test
    public void testArraysStream() {
        List<String> words = Arrays.asList("hello", "world");
        List<Stream<String>> debug = words.stream()
                .filter(word -> {
                    System.out.println("in filter: " + word);
                    return true;
                })
                .map(x -> {
                    System.out.println("in first map: " + x);
                    String[] temp = x.split("");
                    for (int i = 0; i < temp.length; i++) {
                        System.out.println("in first map for each: " + temp[i]);
                    }
                    return temp;
                }).map(word -> {
                    word[word.length - 1] = "X";
                    System.out.println("in map : " + Arrays.asList(word));
                    return Arrays.stream(word);
                }).collect(Collectors.toList());
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        for (Stream<String> stream : debug) {
            stream.forEach(System.out::println);
        }
        System.out.println("debug: " + debug);
    }

    @Test
    public void testCount() {
        List<String> words = Arrays.asList("hello", "world");
        long debug = words.stream()
                .filter(word -> {
                    System.out.println("in filter: " + word);
                    return true;
                })
                .map(x -> {
                    System.out.println("in first map: " + x);
                    String[] temp = x.split("");
                    for (int i = 0; i < temp.length; i++) {
                        System.out.println("in first map for each: " + temp[i]);
                    }
                    return temp;
                }).map(word -> {
                    word[word.length - 1] = "X";
                    System.out.println("in map : " + Arrays.asList(word));
                    return Arrays.stream(word);
                }).distinct().count();

        Assert.assertEquals(2, debug);
    }

    @Test
    public void testForPractice1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Object[] res = list.stream().map(i -> i * i).toArray();
        Assert.assertArrayEquals(new Integer[] {1, 4, 9, 16, 25}, res);

        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5);
        Object[] pairs = list1.stream().flatMap(i -> list2.stream().map(j -> new int[] {i, j})).toArray();
        Integer[][] expected = new Integer[][] {new Integer[]{1, 4}, new Integer[]{1, 5},
                new Integer[]{2, 4}, new Integer[]{2, 5}, new Integer[]{3, 4}, new Integer[]{3, 5}};
        Assert.assertArrayEquals(expected, pairs);
    }

    @Test
    public void testForPracticePairs() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5);
        Object[] pairs = list1.stream()
                .flatMap(i -> list2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[] {i, j}))
                .toArray();
        Integer[][] expected = new Integer[][] {new Integer[]{1, 5},
                new Integer[]{2, 4}};
        Assert.assertArrayEquals(expected, pairs);
    }

    @Test
    public void testFindAny() {
        menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(dish -> System.out.println(dish.getName()));
    }

    @Test
    public void testFindFirst() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        // 只会遍历1, 2, 3
        list.stream().map(n -> {
            System.out.println("in map: " + n);
            return n * n;
        }).filter(n -> {
            System.out.println("in filter: " + n);
            return n % 3 == 0;
        }).findFirst().ifPresent(System.out::println);
    }

    @Test
    public void testReduce() {
        int actual = menu.stream().map(dish -> 1).reduce(Integer::sum).orElse(0);
        long expected = menu.size();
        Assert.assertEquals(expected, actual);
    }
}
