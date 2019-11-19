package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.game.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu) {
			
			//play button
			if(mouseOver(mx, my, 210, 150, 200, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2 -32, Game.HEIGHT/2 -32, ID.Player, handler));
				handler.clearEnemys();
	            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-64), r.nextInt(Game.HEIGHT-64), ID.BasicEnemy, handler));
			}
			
			//help button
			if(mouseOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Help;
			}
			
			//quit button
			if(mouseOver(mx, my, 210, 350, 200, 64)) {
				System.exit(1);
			}
		}

		//back button for help
		if(game.gameState == STATE.Help) {
			if(mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Menu;
				return;
			}
		}
		
		//try again button for game over
				if(game.gameState == STATE.End) {
					if(mouseOver(mx, my, 210, 350, 200, 64)) {
						hud.setLevel(1);
		        		hud.setScore(0);
						game.gameState = STATE.Menu;
						return;
					}
				}
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}else return false;
		}else return false;
		
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(game.gameState == STATE.Menu) {
			Font fontOne = new Font("arial", 1, 50);
			Font fontTwo = new Font("arial", 1, 30);
			
			g.setFont(fontOne);
			g.setColor(Color.white);
			g.drawString("DodgeAlot", 185, 90);
			
			g.setFont(fontTwo);
			g.drawString("Play", 280, 190);
			g.drawRect(210, 150, 200, 64);
			
			g.drawString("Help", 280, 290);
			g.drawRect(210, 250, 200, 64);
			
			g.drawString("Quit", 280, 390);
			g.drawRect(210, 350, 200, 64);
		}
		else if(game.gameState == STATE.Help) {
			Font fontOne = new Font("arial", 1, 50);
			Font fontTwo = new Font("arial", 1, 30);
			Font fontThree = new Font("arial", 1, 14);
			
			g.setFont(fontOne);
			g.setColor(Color.white);
			g.drawString("Help", 245, 80);
			
			g.setFont(fontThree);
			g.drawString("Use WASD keys to move player and dodge enemies.", 130, 200);
			
			g.setFont(fontTwo);
			g.drawString("Back", 280, 390);
			g.drawRect(210, 350, 200, 64);
		}
		else if(game.gameState == STATE.End) {
			Font fontOne = new Font("arial", 1, 50);
			Font fontTwo = new Font("arial", 1, 30);
			Font fontThree = new Font("arial", 1, 14);
			
			g.setFont(fontOne);
			g.setColor(Color.white);
			g.drawString("Game Over", 180, 80);
			
			g.setFont(fontThree);
			g.drawString("You lost with a score of: " + hud.getScore(), 210, 200);
			
			g.setFont(fontTwo);
			g.drawString("Try Again", 242, 392);
			g.drawRect(210, 350, 200, 64);
		}
		
		
	}

}
