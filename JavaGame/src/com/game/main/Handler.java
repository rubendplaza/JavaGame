package com.game.main;

import java.awt.Graphics;
import java.util.ArrayList;

//import com.game.main.Game.STATE;

public class Handler {

	public int speed = 5;
	
	ArrayList <GameObject> object = new ArrayList<GameObject>();
	// changed to arraylist because linkedlist was causing null pointer exceptions
	// if causing problems later try implementing a try catch inside the render for loop

  public void tick(){
    for(int i = 0; i < object.size(); i++){
      GameObject tempObject = object.get(i);
      

      tempObject.tick();

    }

  }

  public void render(Graphics g){
    for(int i = 0; i < object.size(); i++){

    	GameObject tempObject = object.get(i);
    	tempObject.render(g);
     
    }

  }
  
  public void clearEnemys() {
	  
	  
	  for(int i = 0; i < object.size(); i++){
	      GameObject tempObject = object.get(i);

	      if(tempObject.getId() == ID.Player) {
	    	  object.clear();
	    	  if(Game.gameState != Game.STATE.End)
	    	  addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
	      }
	      

	    }
  }

  public void addObject(GameObject object){
    this.object.add(object);
  }

  public void removeObject(GameObject object){
    this.object.remove(object);
  }


}