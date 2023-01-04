package org.fan.learn.demo.java.stream.practice;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author author
 * @date 2022/11/22
 */
public class Tester {

    private List<Trader> traders;
    private List<Transaction> transactions;

    @Before
    public void before() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        traders = Arrays.asList(raoul, mario, alan, brian);
        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    public void test1() {
        List<Transaction> list = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void test2() {
        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(cities);

        Set<String> set = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .collect(Collectors.toSet());
        System.out.println(set);
    }

    @Test
    public void test3() {
        List<Trader> list = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void test4() {
        String names = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
        System.out.println(names);
    }

    @Test
    public void test5() {
        boolean found = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .anyMatch("Milan"::equals);
        System.out.println(found);
    }

    @Test
    public void test6() {
        transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    @Test
    public void test7() {
        int max = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .orElse(0);
        System.out.println(max);
    }

    @Test
    public void test8() {
        Transaction minTransaction = transactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue))
                .orElse(null);
        System.out.println(minTransaction);
    }
}
