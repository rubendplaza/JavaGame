package com.game.main;

import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;

public class Player extends GameObject {

  Random r = new Random();

  public Player(int x, int y, ID id) {
    super(x, y, id);
  }
  
  public void tick() {
    x += velX;
    y += velY;
    
    x = Game.clamp(x, 0, Game.WIDTH-48);
    y = Game.clamp(y, 0, Game.HEIGHT-72);
    
  }

  
  public void render(Graphics g) {
	  
	  g.setColor(Color.white);
    
    g.fillRect(x, y, 32, 32);

  }

  


}

//my way of not allowing the player to go off the screen instead of library
//if(y <= 0) {
//velY = 0;
//y = 0;
//}
//if(y > Game.HEIGHT-72) {
//velY = 0;
//y = Game.HEIGHT-72;
//}
//if(x <= 0) {
//velX = 0;
//x = 0;
//}
//if(x > Game.WIDTH-48) {
//velX = 0;
//x = Game.WIDTH-48;
//}
