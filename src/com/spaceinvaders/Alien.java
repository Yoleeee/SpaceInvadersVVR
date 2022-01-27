package com.spaceinvaders;

import javax.swing.*;
import java.util.Objects;

public class Alien extends Obj implements Constants {

    private Projectile projectile;

    public Alien(int x, int y, String sprite) {
        this.x = x;
        this.y = y;
        this.dx = 2;

        ImageIcon ii = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(sprite)));
        setImage(ii.getImage());

        projectile = new Projectile(x, y);
        projectile.setVisible(false);
    }

    public static class Projectile extends Obj {

        public Projectile(int x, int y) {
            this.x = x;
            this.y = y;
            setVisible(false);
            setImage(null);
        }

        public void drawProjectile() {
            String projectile = "Sprites/alienshot.png";
            ImageIcon ii = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(projectile)));
            setImage(ii.getImage());
        }

        @Override
        public void move() {
            this.y += ALIEN_SPEED;
        }

        @Override
        void reset() {
        }
    }

    public void move(int direction) {
        this.x += direction;
    }

    @Override
    void reset() {
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
    }
}
