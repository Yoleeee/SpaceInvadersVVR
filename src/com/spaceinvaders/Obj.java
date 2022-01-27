package com.spaceinvaders;

import java.awt.*;
import java.awt.image.ImageObserver;

abstract class Obj {

    private Image image;
    private boolean visible = true;
    protected int x;
    protected int y;
    protected int dx = 0;

    public Obj() {
    }

    public void move() {
    }

    public void drawObject(Graphics g, ImageObserver io) {
        g.drawImage(this.image, this.x, this.y, io);
    }

    abstract void reset();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
