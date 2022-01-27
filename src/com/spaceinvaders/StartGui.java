package com.spaceinvaders;

import javax.swing.*;

public class StartGui extends JFrame implements Constants {

    public StartGui() {
        add(new Board());
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Space Invaders");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new StartGui();
    }
}
