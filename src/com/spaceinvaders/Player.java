package com.spaceinvaders;

import javax.swing.*;
import java.util.Objects;

public class Player extends Obj {


    public Player() {
        String playerImg = "Sprites/player.png";
        ImageIcon ii = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(playerImg)));
        setImage(ii.getImage());
        this.x = 388;
        this.y = 525;
        setVisible(true);
    }

    @Override
    public void move() {
        setX(getX() + getDx());
    }

    @Override
    void reset() {
        this.x = 388;
        this.y = 525;
        this.dx = 0;
    }


}
