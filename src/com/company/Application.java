package com.company;

import com.company.Engine.GameEngine;
import com.company.GameStates.InitState;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;

import java.io.IOException;
import java.sql.Time;

public class Application {
    public void run() throws IOException {
        
        //DeltaTime testing
        long lastTime = System.nanoTime();
        
        RenderWindow window = new RenderWindow();
    
        window.create(new VideoMode(1600, 1024), "The Ultimate Chess Game!");
    
        window.setFramerateLimit(120);
        GameEngine game = new GameEngine();
        
        game.init();
        
        game.changeState(InitState.getInstance(), window);
    
        while(window.isOpen()) {
            while (game.running()) {
                long time = System.nanoTime();
                float deltaTime = (time - lastTime) / 1000000;
                lastTime = time;
                
                game.handleEvents(window, deltaTime);
                game.update(window, deltaTime);
                game.draw(window);
    
            }
        }
        System.out.println(game.running());
    }
}
