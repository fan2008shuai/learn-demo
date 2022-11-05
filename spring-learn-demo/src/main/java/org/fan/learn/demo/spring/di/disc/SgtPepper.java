package org.fan.learn.demo.spring.di.disc;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.PrintStream;

public class SgtPepper implements CompactDisc {

    private String title;
    private String artist;
    @Autowired
    private PrintStream printStream;

    public SgtPepper(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public void play() {
        printStream.println("title: " + title + ", artist: " + artist);
    }
}
