package com.spaceinvaders;

import javax.swing.*;
import java.util.Objects;

public class Player extends Obj {

    private final int START_X = 388;
    private final int START_Y = 525;

    public Player() {
        String playerImg = "Sprites/player.png";
        ImageIcon ii = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(playerImg)));
        setImage(ii.getImage());
        this.x = START_X;
        this.y = START_Y;
        setVisible(true);
    }

    @Override
    public void move() {
        setX(getX() + getDx());
    }

    @Override
    void reset() {
        this.x = START_X;
        this.y = START_Y;
        this.dx = 0;
    }


}
