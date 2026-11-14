package main;
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	// CONFIGURACIÓN DE PANTALLA
	
	final int originalTileSize = 16; 					// 16x16 tile
	final int scale = 3;
	
	final int tileSize = originalTileSize * scale; 		// 48x48 tile 
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol;	// 768 px
	final int screenHeight = tileSize * maxScreenRow; 	// 576 px
	
	// FPS
	int FPS = 60;

	KeyHandler keyH = new KeyHandler(); 				// Instanciamos el controlador de teclado
	
	Thread gameThread; 				// Thread es el hilo especial de Java Swing que se encarga exclusivamente de dibujar interfaz gráfica 
									// y responder a los eventos
	

	// Establecemos la posicipon predeterminada del jugador
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Se establace el tamaño de la pantalla con las dimensiones establecidas
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	


	public void startGameThread() {
		
		gameThread = new Thread(this); // Con esto instanciamos el hilo con GamePanel como argumento del constructor
		gameThread.start();
	}
	
	@Override
	public void run() { 
		// Aquí se almacena el GameLoop
		
		while(gameThread != null) {
			
			double drawInterval = 1000000000/FPS; // 0.01666 segundos por cada dibujo para generar 60 FPS
			
			double nextDrawTime = System.nanoTime() + drawInterval; // El próximo momento en el que se actualice la pantalla es igual al 
																	// tiempo actual más el intervalo de actualización
			
			//1. UPDATE: Actualiza información tal como laa posición del personaje
			update();
			
			//2. DRAW: Dibuja en la pantalla con la información actualizada
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime(); 	// El tiempo restante para la proxima actualización
				remainingTime /= 1000000; 									// Convertir nanosegundos a microsegundos para el Thread.sleep
				
				if(remainingTime < 0 ) {									// Si la resta es negativa, significa que el momento de dibujo ya pasó,
					remainingTime = 0; 										// se establece en 0 el tiempo restante
				}
				
				Thread.sleep((long)remainingTime); 							// Detiene la ejecución del juego por la cantidad de tiempo restante para generar 
																			// los cuadros por segundo
				
				nextDrawTime += drawInterval;  								// Se resetea el proximo tiempo de dibujo
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		
	}
	
	public void update() {
		
		if (keyH.upPressed) {
			playerY -= playerSpeed;
			
		} else if (keyH.downPressed) {
			playerY += playerSpeed;
			
		} else if (keyH.leftPressed) {
			playerX -= playerSpeed;
			
		} else if (keyH.rightPressed) {
			playerX += playerSpeed;
			
		}
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); 							// Ya que GamePanel es una subclase de Jpanel, utilizamos el método de pintar 
															// de la superclase JPanel
		
		Graphics2D g2 = (Graphics2D)g;
		
		// Dibujamos al jugador en la pantalla
		g2.setColor(Color.white);
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		g2.dispose();
		
	}

}
