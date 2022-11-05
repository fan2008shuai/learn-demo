package org.fan.learn.demo.spring.di;

import org.fan.learn.demo.spring.di.config.DiscConfig;
import org.fan.learn.demo.spring.di.player.MediaPlayer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "dev");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiscConfig.class);
        MediaPlayer player = (MediaPlayer) context.getBean("player");
        player.play();
    }
}
