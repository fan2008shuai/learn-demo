package org.fan.learn.demo.spring.di.player;

import org.fan.learn.demo.spring.di.disc.CompactDisc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("player")
public class CDPlayer implements MediaPlayer {

    @Autowired
    private CompactDisc disc;

    public CDPlayer(CompactDisc disc) {
        this.disc = disc;
    }

    @Override
    public void play() {
        disc.play();
    }
}
