package com.game.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

  protected float x, y; // can only be accessed by classes that inherit this class
  protected ID id;
  protected float velX, velY;

  public GameObject(float x, float y, ID id){

	  
      this.x = x;
      this.y = y;
      this.id = id;

  }

  public abstract void tick();

  public abstract void render(Graphics g);
  
  public abstract Rectangle getBounds(); // rectangle has an intersect method for two rectangles, true if colliding 

  public void setX(float x){
    this.x = x;
  }

  public void setY(float y){
    this.y = y;
  }

  public float getX(){
    return x;
  }

  public float getY(){
    return y;
  }

  public void setId(ID id){
    this.id = id;
  }

  public ID getId(){
    return id;
  }

  public void setVelX(float velX){
    this.velX = velX;
  }

  public void setVelY(float velY){
    this.velY = velY;
  }

  public float getVelX(){
    return velX;
  }

  public float getVelY(){
    return velY;
  }



}