package com.game.main;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {

  private static final long serialVersionUID = -4740734476359052182L;

  public Window(int width, int height, String title, Game game) {

        JFrame frame = new JFrame(title); //creating the jframe

        frame.setPreferredSize(new Dimension(width, height)); //size setting
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close on x
        frame.setResizable(false); // to refrain from setting different sizes of windows/objects
        frame.setLocationRelativeTo(null); // puts the jframe to the middle of the screen
        frame.add(game); // adds the game instance to the jframs
        frame.setVisible(true); //self explanatory
        game.start(); //starts the game

    }

}