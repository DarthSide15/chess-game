package com.company.Engine;

import org.jsfml.graphics.RenderWindow;

import java.io.IOException;

public abstract class GameState {
    public abstract void init(RenderWindow window) throws IOException;
    
    public abstract void handleEvents(GameEngine game, RenderWindow window, float deltaTime) throws IOException;
    
    public abstract void update(GameEngine game, RenderWindow window, float deltaTime) throws IOException;
    
    public abstract void draw(GameEngine game, RenderWindow window) throws IOException;
    
    public void changeState(GameEngine game, GameState state, RenderWindow window) throws IOException {
        game.changeState(state, window);
    }
    
    protected GameState() {
    }
}
