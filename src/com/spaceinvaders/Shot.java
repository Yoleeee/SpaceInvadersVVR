package com.spaceinvaders;

import javax.swing.*;
import java.util.Objects;

public class Shot extends Obj implements Constants {

    public Shot() {
    }

    public Shot(int x, int y) {
        this.x = x;
        this.y = y;
        String shotImg = "Sprites/shot.png";
        ImageIcon ii = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(shotImg)));
        setImage(ii.getImage());
    }


    @Override
    public void move() {
        setY(getY() - SHOT_SPEED);
    }

    @Override
    void reset() {
    }
}
