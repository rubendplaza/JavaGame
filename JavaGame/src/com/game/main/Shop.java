package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter {
	
	Handler handler;
	HUD hud;
	
	public Shop(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial", 0, 48));
		g.drawString("SHOP", Game.WIDTH/2-70, 50);
		
		//b1 - box 1
		g.setFont(new Font("arial", 0, 12));
		g.drawString("Upgrade Health", 80, 120);
		g.drawString("Cost: " + hud.B1, 80, 140);
		g.drawRect(50, 100, 150, 80);
		
		//b2 - box 2
		g.drawString("Upgrade Speed", 280, 120);
		g.drawString("Cost: " + hud.B2, 280, 140);
		g.drawRect(245, 100, 150, 80);
		
		//b3 - box 3
		g.drawString("Refill Health", 480, 120);
		g.drawString("Cost: " + hud.B3, 480, 140);
		g.drawRect(440, 100, 150, 80);
		
		g.drawString("SCORE: "+hud.getScore(), Game.WIDTH/2-50, 300);
		g.drawString("Press Space to go back: ", Game.WIDTH/2-50, 320);
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//box 1 select
		if(mx >= 50 && mx <= 200) {
			if(my >= 100 && my <= 180) {
				
				if(hud.getScore() >= hud.B1) {
					
					hud.setScore(hud.getScore() - hud.B1);
					hud.B1 += 1000;
					hud.bounds += 20;
					hud.HEALTH = (100 + (hud.bounds/2));
					
				}
				
				
			}
		}
		//box 2 select
		if(mx >= 245 && mx <= 395) {
			if(my >= 100 && my <= 180) {
				
				if(hud.getScore() >= hud.B2) {
					
					hud.setScore(hud.getScore() - hud.B2);
					hud.B2 += 1000;
					handler.speed++;
					
				}
				
			}
		}
		//box 3 select
		if(mx >= 440 && mx <= 590) {
			if(my >= 100 && my <= 180) {
				
				if(hud.getScore() >= hud.B3) {
					
					hud.setScore(hud.getScore() - hud.B3);
					hud.HEALTH = (100 + (hud.bounds/2));
					
				}
				
			}
		}
		
	}

}
