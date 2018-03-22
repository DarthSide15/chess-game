package com.company.GameStates;

import com.company.ChessBoard;
import com.company.Engine.GameEngine;
import com.company.Engine.GameState;
import com.company.AI.AI;
import com.company.Entities.Piece;
import com.company.Managers.TextureManager;
import com.company.Utility.DrawLine;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.window.event.Event;

import java.io.IOException;

public class PlayState extends GameState {
    private static PlayState playState = null;
    private AI[] ai;
    private ChessBoard chessBoard;
    private Sprite[][] boardSprite;
    private Sprite[][] pieceSprite;
    private TextureManager textureManager;
    
    private String chessFilePath;
    private Piece[][] allpieces;
    
    private float movementSpeed;
    private boolean pressedButton;
    private float newPosY;
    
    //TEST
    private RectangleShape[] lines = new RectangleShape[2];
    private DrawLine line;
    private int count = 0;
    
    private PlayState() throws IOException {
    
    }
    
    public static PlayState getInstance() throws IOException {
        if (playState == null) {
            playState = new PlayState();
        }
        return playState;
    }
    
    @Override
    public void init(RenderWindow window) throws IOException {
        window.clear();

        ai = new AI[2];
        movementSpeed = 0.2f;
        pressedButton = false;
        newPosY = 19 + 128;
        
        System.out.println("Inside PlayState");
        chessBoard = new ChessBoard();
        boardSprite = chessBoard.createBoard();
        
        for(int i = 0; i < boardSprite.length; ++i) {
            for(int j = 0; j < boardSprite.length; ++j) {
                boardSprite[i][j].setPosition(new Vector2f(i * 128, j * 128));
            }
        }
        
        chessBoard.fillLists(false);
        chessBoard.fillLists(true);
        
        chessBoard.populateChessboardList();
    
        textureManager = new TextureManager();
        chessFilePath = "src/com/company/Images/ChessPiecesArray.png";
        allpieces = chessBoard.getAllPieces();
        
        
        pieceSprite = new Sprite[8][8];
        
        for(int i = 0; i < allpieces.length; ++i ) {
            for(int j = 0; j < allpieces.length; ++j) {
                pieceSprite[i][j] = new Sprite();
                
                if(chessBoard.getAllPieces()[i][j] != null) {
                    pieceSprite[i][j] = allpieces[i][j].getSprite(textureManager, chessFilePath);
                    pieceSprite[i][j].setPosition(new Vector2f(j * 128 + 19, i * 128 + 19));
                    pieceSprite[i][j].setScale(new Vector2f(1.5f, 1.5f));
                }
            }
        }
        ai[0] = new AI(chessBoard.getWhitePieces());
        ai[1] = new AI(chessBoard.getBlackPieces());
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
                            if(count >= 0 && count < 2) {
                                ai[0].handleEvents(allpieces, pieceSprite, ai[1], game, window);
                                ++count;
                            } else if(count >= 2) {
                                ai[1].handleEvents(allpieces, pieceSprite, ai[0], game, window);
                                ++count;
                                if(count >= 4) {
                                    count = 0;
                                }
                            }
                            break;
                    }
                case MOUSE_ENTERED:
                    break;
            }
        }
    }
    
    @Override
    public void update(GameEngine game, RenderWindow window, float deltaTime) throws IOException {
        window.clear(Color.BLACK);
    }
    
    @Override
    public void draw(GameEngine game, RenderWindow window) throws IOException {
        for (Sprite[] boardSpriteI : boardSprite) {
            for (Sprite boardSpriteJ : boardSpriteI) {
                window.draw(boardSpriteJ);
            }
        }
    
        for (Sprite[] PieceSpriteI : pieceSprite) {
            for (Sprite PieceSpriteJ : PieceSpriteI) {
                if(PieceSpriteJ != null) {
                    window.draw(PieceSpriteJ);
                }
            }
        }
        ai[0].draw(window);
        ai[1].draw(window);
        
        window.display();
    }
}