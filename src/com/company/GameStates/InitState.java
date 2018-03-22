package com.company.GameStates;

import com.company.Engine.GameEngine;
import com.company.Engine.GameState;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;

import java.io.IOException;

public class InitState extends GameState {
    private static InitState initState = null;
    
    private InitState() throws IOException {
    }
    
    public static InitState getInstance() throws IOException {
        if (initState == null) {
            initState = new InitState();
        }
        return initState;
    }
    
    @Override
    public void init(RenderWindow window) throws IOException {
        /*window = new RenderWindow();
        window.create(new VideoMode(640, 480), "The Ultimate Chess Game!");
    
        window.setFramerateLimit(30);*/
    }
    
    @Override
    public void handleEvents(GameEngine game, RenderWindow window, float deltaTime) throws IOException {
    
    }
    
    @Override
    public void update(GameEngine game, RenderWindow window, float deltaTime) throws IOException {
        changeState(game, MenuState.getInstance(), window);
    }
    
    @Override
    public void draw(GameEngine game, RenderWindow window) throws IOException {
        window.display();
    }
}
