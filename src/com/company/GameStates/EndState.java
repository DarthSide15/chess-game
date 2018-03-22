package com.company.GameStates;

import com.company.Engine.GameEngine;
import com.company.Engine.GameState;
import org.jsfml.graphics.RenderWindow;

import java.io.IOException;

public class EndState extends GameState {
    private static EndState endState = null;
    
    private EndState() throws IOException {
    
    }
    
    public static EndState getInstance() throws IOException {
        if (endState == null) {
            endState = new EndState();
        }
        return endState;
    }
    
    @Override
    public void init(RenderWindow window) throws IOException {
    
    }
    
    @Override
    public void handleEvents(GameEngine game, RenderWindow window, float deltaTime) throws IOException {
    
    }
    
    @Override
    public void update(GameEngine game, RenderWindow window, float deltaTime) throws IOException {
    
    }
    
    @Override
    public void draw(GameEngine game, RenderWindow window) throws IOException {
    
    }
}
