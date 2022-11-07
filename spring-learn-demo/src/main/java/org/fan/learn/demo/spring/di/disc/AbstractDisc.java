package org.fan.learn.demo.spring.di.disc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.PrintStream;

public class AbstractDisc implements CompactDisc {

    @Value("${disc.title}")
    private String title;
    @Value("#{systemProperties['disc.artist']}")
    private String artist;
    @Value("#{T(System).out}")
    private PrintStream printStream;

    public AbstractDisc() {
    }

    public AbstractDisc(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public void play() {
        printStream.println("title: " + title + ", artist: " + artist);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public PrintStream getPrintStream() {
        return printStream;
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }
}
