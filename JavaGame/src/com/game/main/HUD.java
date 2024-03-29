package com.game.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public int bounds = 0;
	public static float HEALTH = 100;
	
	private float greenValue = 255;
	
	private int score = 0;
	private int level = 1;
	
	public int B1 = 500;
	public int B2 = 500;
	public int B3 = 500;
	
	
	public void tick() {
	
		HEALTH = Game.clamp(HEALTH, 0, 100 + (bounds/2));
		
		greenValue = HEALTH*2;
		greenValue = Game.clamp(greenValue, 0, 255);
		
		score++;
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200 + bounds, 32);
		
		g.setColor(new Color(75, (int)greenValue, 0));
		g.fillRect(15, 15, (int)HEALTH*2, 32);
		
		g.setColor(Color.white);
		g.drawRect(15, 15, 200 + bounds, 32);
		
		g.drawString("Score " + score, 10, 64);
		g.drawString("Level " + level, 10, 80);
		g.drawString("Space for Shop", 10, 95);
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}

}
