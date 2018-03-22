package com.company;
import com.company.Entities.Pawn;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
        Application app = new Application();
        app.run();
    }
}
