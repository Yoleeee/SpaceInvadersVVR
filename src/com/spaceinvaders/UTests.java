package com.spaceinvaders;


import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class UTests {

    @Test
    void getPlayer() {
        Player player = new Player();
        Image image = player.getImage();
        assertEquals(image, player.getImage());
    }

    @Test
    void getPlayerMoveOnReset() {
        Player player = new Player();
        player.reset();
        assertEquals(0, player.getDx());
    }

    @Test
    void checkBoard() {
        Board board = new Board();
        assertEquals(0, board.getTime());
        assertTrue(board.isIngame());
    }
}
