package com.game.main;

import java.awt.Canvas;	
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 4547863571793263642L;

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12*9; // to create 16:9 ratio
    
    private boolean running = false;
    private Thread thread;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;
    private Shop shop;
    
    private Random r;
    
    public static boolean paused = false;
    public int diff = 0;
    
    //0 = normal
    //1 = hard
    
    public enum STATE{
    	
    	Menu,
    	Select,
    	Game,
    	Shop,
    	Help,
    	End
    	
    };
    
    public static STATE gameState = STATE.Menu;
    

    public Game(){
        
    	handler = new Handler();
    	hud = new HUD();
    	shop = new Shop(handler, hud);
    	menu = new Menu(this, handler, hud);
    	this.addKeyListener(new KeyInput(handler, this));
    	this.addMouseListener(menu);
    	this.addMouseListener(shop);
    	
    	new Window(WIDTH, HEIGHT, "GAME", this);
    	
    	spawner = new Spawn(handler, hud, this);
    	
    	r = new Random();
    	
    	if(gameState == STATE.Game) {
    		
    		handler.addObject(new Player(WIDTH/2 -32, HEIGHT/2 -32, ID.Player, handler));
            
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-64), r.nextInt(Game.HEIGHT-64), ID.BasicEnemy, handler));
    		
    	}
    	else {
    		for(int i = 0; i < 10; i++) {
    			handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
    		}
    	}

    }

    public synchronized void start() {
        thread = new Thread(this); // creates a new thread with the game object
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try{
            thread.join(); //stops the thread
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){ //game loop
    	this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick(); // updates our objects 
                delta--;
            }
            if(running){
                render(); // renders our objects
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
//                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        
        if(gameState == STATE.Game) {
        	
        	if(!paused) {
        		hud.tick();
            	spawner.tick();
            	handler.tick();
            	
            	if(hud.HEALTH <= 0) {
            		
            		hud.HEALTH = 100;
            		gameState = STATE.End;
            		handler.clearEnemys();
            		
            		for(int i = 0; i < 10; i++) {
            			handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
            		}
            	}	
        	}
        	
        }
        else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select) {
        	menu.tick();
        	handler.tick();
        }
    }

    private void render(){
        
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        if(paused) {
        	g.setColor(Color.white);
        	g.drawString("PAUSED", 100, 100);
        }
        
        if(gameState == STATE.Game) {
        
        	hud.render(g);
        	handler.render(g);
        	
        }
        else if(gameState == STATE.Shop) {
        	
        	shop.render(g);
        	
        }
        else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select) {
        	
        	menu.render(g);
        	handler.render(g);
        	
        }
        
        g.dispose();
        bs.show();
        
    }
    
    public static float clamp(float var, float min, float max) {
    	
    	
    	if(var >= max)
    		return var = max;
    	else if(var <= min)
    		return var = min;
    	else
    		return var;
    }
    

    public static void main(String[] args) throws Exception {

        new Game();
        
    }
}