package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Clase base para entidades del juego (se convertirá en abstract en la Etapa 3).
 */
public class Entity {

    protected int x;
    protected int y;
    protected int speed;

    protected BufferedImage up1;
    protected BufferedImage up2;
    protected BufferedImage down1;
    protected BufferedImage down2;
    protected BufferedImage left1;
    protected BufferedImage left2;
    protected BufferedImage right1;
    protected BufferedImage right2;

    protected String direction = "down";
    protected int spriteCounter = 0;
    protected int spriteNum = 1;
    protected Rectangle solidArea = new Rectangle(0, 0, 48, 48);

    public void update() {
    }

    public void draw(Graphics2D g2) {
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }

    public String getDirection() { return direction; }
    public Rectangle getSolidArea() { return solidArea; }
}
