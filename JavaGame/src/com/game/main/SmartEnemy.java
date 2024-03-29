package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{
	
	private Handler handler;
	private GameObject player;

	public SmartEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player)
				player = handler.object.get(i);
		}
		
		velX = 1;
		velY = 1;
		
		
	}

	public Rectangle getBounds() {
		  return new Rectangle((int)x, (int)y, 16, 16);
	  }
	
	public void tick() {
		
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 16;
		float diffY = y - player.getY() - 16;
		float distance = (float)Math.sqrt(Math.pow(x-player.getX(), 2) + Math.pow(y-player.getY(), 2));
		
		velX = ((-1/distance) * diffX * 2);
		velY = ((-1/distance) * diffY * 2);
		
		if(Float.isNaN(velX) || Float.isNaN(velY)) {
			velX = 1;
			velY = 1;
		}
		
		//if(y <= 0 || y >= Game.HEIGHT - 16) velY *= -1;
		
		//if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.02f, handler));
		
	}

	
	public void render(Graphics g) {
		
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}
	
	

}
