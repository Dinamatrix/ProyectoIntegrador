package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

/**
 * Rana protagonista con animación en 4 direcciones y límites de pantalla.
 */
public class Player extends Entity {

    private final GamePanel gp;
    private final KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.solidArea = new Rectangle(8, 16, 32, 28);
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        this.x = (gp.getScreenWidth() - gp.getTileSize()) / 2;
        this.y = (gp.getScreenHeight() - gp.getTileSize()) / 2;
        this.speed = 4;
        this.direction = "down";
        this.spriteCounter = 0;
        this.spriteNum = 1;
    }

    public void getPlayerImage() {
        up1 = loadSprite("/res/player/FrogBackside1.png");
        up2 = loadSprite("/res/player/FrogBackside2.png");
        down1 = loadSprite("/res/player/FrogFrontside1.png");
        down2 = loadSprite("/res/player/FrogFrontside2.png");
        left1 = loadSprite("/res/player/FrogLeftside1.png");
        left2 = loadSprite("/res/player/FrogLeftside2.png");
        right1 = loadSprite("/res/player/FrogRightside1.png");
        right2 = loadSprite("/res/player/FrogRightside2.png");
    }

    private BufferedImage loadSprite(String path) {
        try (InputStream is = getClass().getResourceAsStream(path)) {
            if (is == null) {
                System.err.println("No se encontro el sprite: " + path);
                return null;
            }
            return ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update() {
        boolean moving = false;

        if (keyH.isUpPressed()) {
            direction = "up";
            y -= speed;
            moving = true;
        } else if (keyH.isDownPressed()) {
            direction = "down";
            y += speed;
            moving = true;
        } else if (keyH.isLeftPressed()) {
            direction = "left";
            x -= speed;
            moving = true;
        } else if (keyH.isRightPressed()) {
            direction = "right";
            x += speed;
            moving = true;
        }

        int maxX = gp.getScreenWidth() - gp.getTileSize();
        int maxY = gp.getScreenHeight() - gp.getTileSize();
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > maxX) x = maxX;
        if (y > maxY) y = maxY;

        if (moving) {
            spriteCounter++;
            if (spriteCounter > 10) {
                spriteNum = (spriteNum == 1) ? 2 : 1;
                spriteCounter = 0;
            }
        } else {
            spriteNum = 1;
            spriteCounter = 0;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage image = switch (direction) {
            case "up" -> (spriteNum == 1) ? up1 : up2;
            case "down" -> (spriteNum == 1) ? down1 : down2;
            case "left" -> (spriteNum == 1) ? left1 : left2;
            case "right" -> (spriteNum == 1) ? right1 : right2;
            default -> down1;
        };

        if (image != null) {
            g2.drawImage(image, x, y, gp.getTileSize(), gp.getTileSize(), null);
        } else {
            g2.setColor(Color.GREEN);
            g2.fillRect(x, y, gp.getTileSize(), gp.getTileSize());
        }
    }
}
