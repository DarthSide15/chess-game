package com.company.Engine;

import org.jsfml.graphics.RenderWindow;

import java.io.IOException;
import java.util.ArrayList;

import static java.awt.SystemColor.window;

public class GameEngine {
    private ArrayList<GameState> states;
    private boolean running;
    
    // Initialiserar Array och running
    public void init() {
        states = new ArrayList<>();
        running = true;
    }
    
    // Byter state från ett till ett annat och rensar det gamla statet och lägger till det nya
    public void changeState(GameState state, RenderWindow window) throws IOException {
        while (!states.isEmpty()) {
            states.clear();
        }
        
        states.add(state);
        states.get(states.size() - 1).init(window);
        
    }
    
    // Lägger till statet som skickas in i arrayn
    public void pushState(GameState state, RenderWindow window) throws IOException {
        states.add(state);
        states.get(states.size() - 1).init(window);
    }
    
    // Tömmer arrayn på states
    public void popState() {
        if (!states.isEmpty()) {
            states.clear();
        }
    }
    
    // Kallar på nuvarande states handleEvents
    public void handleEvents(RenderWindow window, float deltaTime) throws IOException {
        states.get(states.size() - 1).handleEvents(this, window, deltaTime);
    }
    
    // Kallar på nuvarande states update
    public void update(RenderWindow window, float deltaTime) throws IOException {
        states.get(states.size() - 1).update(this, window, deltaTime);
    }
    
    // Kallar på nuvarande states draw
    public void draw(RenderWindow window) throws IOException {
        states.get(states.size() - 1).draw(this, window);
    }
    
    // Returnerar running
    public boolean running() {
        return running;
    }
    
    // Sätter running till false så spelet avslutas
    public void quit() {
        running = false;
    }
    
}
