package com.company.Managers;

import org.jsfml.graphics.*;

import java.io.IOException;
import java.nio.file.Paths;

public class TextureManager {
    private Texture texture;
    private Image image;
    //private Sprite sprite;
    
    public TextureManager() {
        texture = new Texture();
        image = new Image();
        //sprite = new Sprite();
    }
    
    private void loadImage(String filePath) {
        try {
            image.loadFromFile(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void loadTexture(String filePath) {
        loadImage(filePath);
        try {
            texture.loadFromImage(image);
        } catch (TextureCreationException e) {
            e.printStackTrace();
        }
    }
    
    public Texture getTexture(String filePath) {
        loadTexture(filePath);
        texture.setSmooth(true);
        //sprite.setTexture(texture);
        return texture;
    }
}
