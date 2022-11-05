package org.fan.learn.demo.spring.di.config;

import org.fan.learn.demo.spring.di.disc.AbstractDisc;
import org.fan.learn.demo.spring.di.disc.BlankDisc;
import org.fan.learn.demo.spring.di.disc.CompactDisc;
import org.fan.learn.demo.spring.di.disc.SgtPepper;
import org.fan.learn.demo.spring.di.player.CDPlayer;
import org.fan.learn.demo.spring.di.player.MediaPlayer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.PrintStream;

@Configuration
@ComponentScan(basePackages = {"org.fan.learn.demo.spring.di.disc",
        "org.fan.learn.demo.spring.di.player"})
public class DiscConfig {

    @Bean
    public PrintStream printStream() {
        return System.out;
    }

    @Bean("disc")
    @Profile("dev")
    public CompactDisc sgtPepper() {
        String title = "hello sgtPepper";
        String artist = "dev";
        return new SgtPepper(title, artist);
    }

    @Bean("disc")
    @Profile("prod")
    public CompactDisc blankDisc() {
        String title = "hello blankDisc";
        String artist = "prod";
        return new BlankDisc(title, artist);
    }

//
//    @Bean
//    public MediaPlayer player(CompactDisc disc) {
//        return new CDPlayer(disc);
//    }
}
