package com.spaceinvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Board extends JPanel implements Constants, KeyListener, ActionListener {

    private final Player player = new Player();
    private Shot shot = new Shot();
    ArrayList<Alien> aliens = new ArrayList<>();

    private int time = 0;
    private boolean ingame = true;
    private int direction = ALIEN_SPEED;
    private int deaths = 0;
    private String message;

    public Board() {
        setBackground(Color.BLACK);
        Timer timer = new Timer(DELAY, this);
        timer.start();
        setFocusable(true);
        addKeyListener(this);
        initAliens();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawProjectiles(g);
        drawAliens(g);
        player.drawObject(g, this);
        shot.drawObject(g, this);
        drawGround(g);

        if (!ingame) {
            gameOver(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (ingame) {
            playerDefeated();
            aliensAttack();
            alienDefeated();
            changeAliensDirection();
            moveAliens();
            shot.move();
            player.move();
            repaint();
        }
    }

    private void playerDefeated() {

        for (Alien alien : aliens) {
            if (alien.isVisible() && alien.getY() >= GROUND) {
                message = "Game Over";
                ingame = false;
            }

            int projectileX = alien.getProjectile().getX();
            int projectileY = alien.getProjectile().getY();
            int playerX = player.getX();
            int playerY = player.getY();

            if (projectileX >= (playerX) &&
                    projectileX <= (playerX + PLAYER_WIDTH) &&
                    projectileY >= (playerY) &&
                    projectileY <= (playerY + PLAYER_HEIGHT)) {
                message = "Game Over";
                ingame = false;
            }
        }
    }

    private void aliensAttack() {
        Random rand = new Random();

        for (Alien alien : aliens) {
            int randomNum = rand.nextInt((200 - 1) + 1) + 1;

            Alien.Projectile projectile = alien.getProjectile();

            if (!projectile.isVisible() && randomNum == 5 && alien.isVisible()) {
                alien.setProjectile(projectile = new Alien.Projectile(alien.getX(), alien.getY()));
                projectile.drawProjectile();
                projectile.setVisible(true);
            }

            if (projectile.isVisible() && projectile.getY() >= WINDOW_HEIGHT) {
                projectile.setVisible(false);
            }

            projectile.move();
        }
    }

    private void changeAliensDirection() {

        for (Alien alien : aliens) {
            int x = alien.getX();

            if (x >= WINDOW_WIDTH - BORDER_RIGHT && direction != -ALIEN_SPEED) {
                direction = -ALIEN_SPEED;
                for (Alien a : aliens) {
                    a.setY(a.getY() + GO_DOWN);
                }
            }

            if (x <= BORDER_LEFT && direction != ALIEN_SPEED) {
                direction = ALIEN_SPEED;
                for (Alien a : aliens) {
                    a.setY(a.getY() + GO_DOWN);
                }
            }
        }
    }

    private void moveAliens() {

        for (Alien alien : aliens)
            alien.move(direction);
    }

    private void alienDefeated() {

        if (shot.isVisible()) {
            Iterator<Alien> it = aliens.iterator();
            int shotX = shot.getX();
            int shotY = shot.getY();

            while (it.hasNext()) {
                Alien alien = it.next();
                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.isVisible() && shot.isVisible()) {
                    if (shotX >= (alienX) &&
                            shotX <= (alienX + ALIEN_WIDTH) &&
                            shotY >= (alienY) &&
                            shotY <= (alienY + ALIEN_HEIGHT)) {
                        alien.setImage(null);
                        alien.setVisible(false);
                        shot.setVisible(false);
                        shot.setImage(null);
                        deaths++;
                        System.out.println(deaths);
                    }
                }
            }

            if (shot.getY() < 0) shot.setVisible(false);
        }

        if (deaths == 24) {
            message = "You Won!";
            ingame = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            player.setDx(-PLAYER_SPEED);

        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            player.setDx(PLAYER_SPEED);

        if (e.getKeyCode() == KeyEvent.VK_R) {
            reset();
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            if (!shot.isVisible())
                shot = new Shot(player.getX(), player.getY());
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            player.setDx(0);

        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            player.setDx(0);

    }

    public void drawAliens(Graphics g) {

        for (Alien alien : aliens)
            alien.drawObject(g, this);
    }

    public void drawProjectiles(Graphics g) {

        for (Alien alien : aliens) {
            Alien.Projectile p = alien.getProjectile();
            p.drawObject(g, this);
        }
    }

    public void initAliens() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                Alien alien = new Alien(+ 150 + (ALIEN_WIDTH + 10) * j, 5 + (ALIEN_HEIGHT + 10) * i, "Sprites/alien" + i + ".png");
                aliens.add(alien);
            }
        }
    }

    public void drawGround(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawLine(0, GROUND, WINDOW_WIDTH, GROUND);
    }

    public void gameOver(Graphics g) {
        Font small = new Font("Helvetica", Font.BOLD, 26);
        FontMetrics fm = this.getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (WINDOW_WIDTH - fm.stringWidth(message)) / 2, (WINDOW_HEIGHT / 2 - 26));
    }

    public void reset() {
        player.reset();
        aliens.clear();
        initAliens();
        deaths = 0;
        ingame = true;
    }
}
