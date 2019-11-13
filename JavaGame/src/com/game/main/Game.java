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
    
    private Random r;
    

    public Game(){
        
    	handler = new Handler();
    	this.addKeyListener(new KeyInput(handler));
    	
    	new Window(WIDTH, HEIGHT, "GAME", this);
    	
    	hud = new HUD();
    	spawner = new Spawn(handler, hud);
    	
    	r = new Random();

        handler.addObject(new Player(WIDTH/2 -32, HEIGHT/2 -32, ID.Player, handler));
        
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
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
        hud.tick();
        spawner.tick();
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

        handler.render(g);
        
        hud.render(g);

        g.dispose();
        bs.show();
    }
    
    public static int clamp(int var, int min, int max) {
    	
    	
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