package org.fan.learn.demo.java.stream.parallel.spliter;

import org.fan.learn.demo.java.stream.parallel.StreamWordCounter;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author author
 * @date 2023/1/4
 */
public class StreamWordSpliter implements Spliterator<Character> {

    private String sentence;
    private int position = 0;

    public StreamWordSpliter(String sentence) {
        this.sentence = sentence;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        // position别忘了累加
        action.accept(sentence.charAt(position++));
        return position < sentence.length();
    }

    @Override
    public Spliterator<Character> trySplit() {

        if (sentence.length() - position < 10) {
            return null;
        }

        // 二分法
        for (int p = (position + sentence.length()) / 2; p < sentence.length(); p++) {
            if (Character.isWhitespace(sentence.charAt(p))) {
                Spliterator<Character> spliterator =
                        new StreamWordSpliter(sentence.substring(position, p));

                position = p + 1;
                return spliterator;
            }
        }

        return null;
    }

    @Override
    public long estimateSize() {
        return sentence.length() - position;
    }

    @Override
    public int characteristics() {
        return 0;
    }

    private static long countWords(Stream<Character> stream) {
        StreamWordCounter streamWordCounter = stream.reduce(new StreamWordCounter(0, true), StreamWordCounter::accumulate, StreamWordCounter::combine);
        return streamWordCounter.getCount();
    }

    public static void main(String[] args) {
        String sentence = "  hello   world  good   ";
        Stream<Character> stream = StreamSupport.stream(new StreamWordSpliter(sentence), true);
        System.out.println(countWords(stream));
    }
}
