package org.fan.learn.demo.java.stream.parallel;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author author
 * @date 2023/1/4
 */
public class StreamWordCounter {

    private long count;
    private boolean lastSpace;

    public StreamWordCounter(long count, boolean lastSpace) {
        this.count = count;
        this.lastSpace = lastSpace;
    }

    public StreamWordCounter accumulate(Character c) {
        if (Character.isSpaceChar(c)) {
            // lastSpace是space，直接返回this
            // lastSpace不是space，需要将lastSpace置为true（因为本次是space）
            return lastSpace ? this : new StreamWordCounter(count, true);
        } else {
            // lastSpace是space且本次未非space，说明遇到了新单词，count累加
            return lastSpace ? new StreamWordCounter(count + 1, false) : this;
        }
    }

    public StreamWordCounter combine(StreamWordCounter counter) {
        return new StreamWordCounter(this.count + counter.count, counter.lastSpace);
    }

    public long getCount() {
        return count;
    }

    public boolean isLastSpace() {
        return lastSpace;
    }

    private static long countWords(Stream<Character> stream) {
        StreamWordCounter streamWordCounter = stream.reduce(new StreamWordCounter(0, true), StreamWordCounter::accumulate, StreamWordCounter::combine);
        return streamWordCounter.getCount();
    }

    public static void main(String[] args) {
        String sentence = "  hello   world  good   ";
        Stream<Character> stream = IntStream.range(0, sentence.length()).mapToObj(sentence::charAt);
        System.out.println("right: " + countWords(stream));

        // parallelStream会产生错误结果，因为有可能将sentence在非space处进行拆分
        Stream<Character> parallelStream = IntStream.range(0, sentence.length()).mapToObj(sentence::charAt).parallel();
        System.out.println("error: " + countWords(parallelStream));
    }
}


