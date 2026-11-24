package entity;

import main.KeyHandler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    
    public Player (GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
    }

    public void setDefaultValues(){
        // Establecemos los valores predeterminados del jugador

        x = 100;
        y = 100;
        speed = 4;
    }

   public void getPlayerImage(){

        try{

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/FrogBackside1"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/FrogBackside2"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/FrogFrontside1"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/FrogFrontside2"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/FrogLeftside1"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/FrogLeftside2"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/FrogRightside1"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/FrogRightside2"));



        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        // Actualiza el movimiento
		
        if (keyH.upPressed) {
			y -= speed;
			
		} else if (keyH.downPressed) {
			y += speed;
			
		} else if (keyH.leftPressed) {
			x -= speed;
			
		} else if (keyH.rightPressed) {
			x += speed;
			
		}

    }

    public void draw(Graphics2D g2){
        // Dibujamos al jugador en la pantalla
		
        g2.setColor(Color.white);
		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    
    }

}
