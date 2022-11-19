package org.fan.learn.demo.spring.di.config;

import org.fan.learn.demo.spring.di.condition.DiscCondition;
import org.fan.learn.demo.spring.di.disc.BlankDisc;
import org.fan.learn.demo.spring.di.disc.CompactDisc;
import org.fan.learn.demo.spring.di.disc.SgtPepper;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"org.fan.learn.demo.spring.di.disc",
        "org.fan.learn.demo.spring.di.player"})
@PropertySource("classpath:app.properties")
public class DiscConfig {

    /*
     * 也可以使用SpEL进行注入
     */
//    @Bean
//    public PrintStream printStream() {
//        return System.out;
//    }

    @Bean("disc")
    @Profile("dev")
    public CompactDisc sgtPepper() {
//        String title = "hello sgtPepper";
//        String artist = "dev";
//        return new SgtPepper(title, artist);
        return new SgtPepper();
    }

    @Bean("disc")
    // @Profile("prod")
    @Conditional(DiscCondition.class)
    public CompactDisc blankDisc() {
        String title = "hello blankDisc";
        String artist = "prod";
        // 下面的两种写法：title、artist会被自动注入的覆盖掉
        // return new BlankDisc(title, artist);
        BlankDisc disc = new BlankDisc();
        disc.setTitle(title);
        disc.setArtist(artist);
        return disc;
    }

//
//    @Bean
//    public MediaPlayer player(CompactDisc disc) {
//        return new CDPlayer(disc);
//    }
}
