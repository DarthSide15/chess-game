package com.company.GameStates;

import com.company.Engine.GameEngine;
import com.company.Engine.GameState;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.window.event.Event;

import java.io.IOException;
import java.nio.file.Paths;

public class MenuState extends GameState {
    private static MenuState menuState = null;
    private Texture[] texture;
    private Sprite[] sprite;
    private Image[] image;
    
    
    private MenuState() throws IOException {
        this.texture = new Texture[2];
        this.sprite = new Sprite[2];
        this.image = new Image[2];
    }
    
    public static MenuState getInstance() throws IOException {
        if (menuState == null) {
            menuState = new MenuState();
        }
        return menuState;
    }
    
    @Override
    public void init(RenderWindow window) throws IOException {
        try {
            image[0] = new Image();
            texture[0] = new Texture();
            image[0].loadFromFile(Paths.get("src/com/company/Images/StartButton_NotClicked.png"));
            texture[0].loadFromImage(image[0]);
        } catch(IOException|TextureCreationException ex) {
            System.err.println("Something went wrong:");
            ex.printStackTrace();
        }
    
        float startButtonx = window.getView().getSize().x / 2 - (image[0].getSize().x / 2);
        float startButtony = window.getView().getSize().y / 2 - (image[0].getSize().y / 2);
        Vector2f startButtonPos = new Vector2f(startButtonx, startButtony);
        
        sprite[0] = new Sprite();
        sprite[0].setTexture(texture[0]);
        sprite[0].setPosition(startButtonPos);
    }
    
    @Override
    public void handleEvents(GameEngine game, RenderWindow window, float deltaTime) throws IOException {
        for(Event event : window.pollEvents()) {
            switch(event.type) {
                case CLOSED:
                    window.close();
                    game.quit();
                    break;
                case KEY_RELEASED:
                    switch (event.asKeyEvent().key) {
                        case SPACE:
                            changeState(game, PlayState.getInstance(), window);
                            System.out.println("Changing states to PlayState");
                            break;
                    }
                case MOUSE_ENTERED:
                    break;
            }
        }
    }
    
    @Override
    public void update(GameEngine game, RenderWindow window, float deltaTime) throws IOException {
        window.clear(Color.BLUE);
    }
    
    @Override
    public void draw(GameEngine game, RenderWindow window) throws IOException {
        window.draw(sprite[0]);
        window.display();
    }
}
