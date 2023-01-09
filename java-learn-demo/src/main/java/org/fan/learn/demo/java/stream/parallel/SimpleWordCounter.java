package org.fan.learn.demo.java.stream.parallel;

/**
 * @author author
 * @date 2023/1/4
 */
public class SimpleWordCounter {
    private String sentence;
    private long count;

    public SimpleWordCounter(String sentence) {
        this.sentence = sentence;
        this.count = 0;
    }

    public void count() {
        boolean lastSpace = true;
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (Character.isSpaceChar(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) {
                    count++;
                }
                lastSpace = false;
            }
        }
    }

    public static void main(String[] args) {
        String sentence = "  hello   world  good   ";
        SimpleWordCounter simpleWordCounter = new SimpleWordCounter(sentence);
        simpleWordCounter.count();
        System.out.println(simpleWordCounter.count);
    }
}
