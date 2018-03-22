package com.company.AI;

import com.company.Engine.GameEngine;
import com.company.Engine.GameState;
import com.company.Entities.King;
import com.company.Entities.Pawn;
import com.company.Entities.Piece;
import com.company.Enums.PieceTypes;
import com.company.GameStates.MenuState;
import com.company.GameStates.PlayState;
import com.company.Utility.DrawLine;
import com.company.Utility.Pair;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.awt.SystemColor.window;

public class AI {

    private List<Piece> existingPieces;
    private ArrayList<List<Pair<Integer>>> possibleMovementPair;
    private Random random;
    private int newPosRow;
    private int newPosColumn;
    private int oldPosRow;
    private int oldPosColumn;
    
    private List<List<DrawLine>> line;
    private DrawLine moveLine;
    private int classPieceToMove;
    private int classToMove;
    
    private int count;


    public AI(List<Piece> existingPieces) {
        this.existingPieces = existingPieces;
        possibleMovementPair = new ArrayList<>();
        random = new Random();
        line = new ArrayList<>();
        count = 0;
        classPieceToMove = 0;
    }

    public void handleEvents(Piece[][] allPieces, Sprite[][] sprites, AI ai, GameEngine game, RenderWindow window) throws IOException {
        switch(count) {
            case 0:
                clearMoveLine();
                while(!line.isEmpty()) {
                    line.clear();
                }
                while(!possibleMovementPair.isEmpty()) {
                    possibleMovementPair.clear();
                }
                existingPieces.forEach(Piece::clearPair);
                for (Piece piece : existingPieces) {
                    piece.checkMovement(allPieces, piece.getY(), piece.getX());
                    possibleMovementPair.add(piece.getPair());
                    line.add(piece.getLines());
                }
                ++count;
                break;
            case 1:
                clearLines();
                checkForCheck(allPieces, sprites, ai, game, window);
                count = 0;
                break;
            case 2:
                count = 0;
                break;
        }
    }
    
    private void checkForCheck(Piece[][] allPieces, Sprite[][] sprites, AI ai, GameEngine game, RenderWindow window) throws IOException {
        try {
            King kingPiece = (King) existingPieces.stream()
                    .filter(p -> p.type() == PieceTypes.KING)
                    .findFirst()
                    .get();
            
            ArrayList<List<Pair<Integer>>> kingsPossibleMovementPair = new ArrayList<>();
            if(kingPiece.getIsChecked()) {
                kingPiece.checkMovement(allPieces, kingPiece.getY(), kingPiece.getX());
                kingsPossibleMovementPair.add(kingPiece.getPair());
                kingsPossibleMovementPair.forEach(System.out::println);
                moveKing(allPieces, sprites, kingsPossibleMovementPair, kingPiece, ai, game, window);
            } else {
                move(sprites, allPieces, ai);
            }
        } catch (NoSuchElementException e) {
            System.out.println("There is no king!!! Fatal error!");
            game.changeState(MenuState.getInstance(), window);
        }
    }
    
    private void moveKing(Piece[][] allPieces, Sprite[][] sprites, ArrayList<List<Pair<Integer>>> pairList, King kingPiece, AI ai, GameEngine game, RenderWindow window) throws IOException {
        // Checking kings list
        boolean breakLoop = false;
        
        for(List<Pair<Integer>> pair : pairList) {
            for(Pair<Integer> p : pair) {
                
                // Checking every enemy piece list
                for(List<Pair<Integer>> possPair : ai.possibleMovementPair) {
                    for(Pair<Integer> pPair : possPair) {
                        if(!p.getRow().equals(pPair.getRow()) && !p.getColumn().equals(pPair.getColumn())) {
                            //Piece tempPiece = kingPiece;
                            oldPosRow = kingPiece.getY();
                            oldPosColumn = kingPiece.getX();
                            
                            Sprite tempSprite = sprites[oldPosRow][oldPosColumn];
                            Sprite emptySprite = new Sprite();
                            
                            for(int i = 0; i < pairList.size(); ++i) {
                                for(int j = 0; j < pairList.get(i).size(); ++j) {
                                    newPosRow = pairList.get(i).get(j).getRow();
                                    newPosColumn = pairList.get(i).get(j).getColumn();
                                }
                            }
                            Vector2f vector2f = new Vector2f(newPosColumn * 128 + 19, newPosRow * 128 + 19);
                            sprites[kingPiece.getY()][kingPiece.getX()].setPosition(vector2f.x, vector2f.y);
                            
                            allPieces[newPosRow][newPosColumn] = kingPiece;
                            allPieces[kingPiece.getY()][kingPiece.getX()] = null;
                            sprites[newPosRow][newPosColumn] = tempSprite;
                            sprites[oldPosRow][oldPosColumn] = emptySprite;
                            King[] kingArray = {(King) allPieces[newPosRow][newPosColumn]};
                            kingArray[0].setIsChecked(false);
    
                            for(int i = 0; i < ai.existingPieces.size(); ++i) {
                                if(ai.existingPieces.get(i).getX() == kingPiece.getX() && ai.existingPieces.get(i).getY() == kingPiece.getY()) {
                                    System.out.println("Removed from list : " + ai.existingPieces.get(i));
                                    ai.existingPieces.remove(i);
                                }
                            }
                            allPieces[newPosRow][newPosColumn].setX(newPosColumn);
                            allPieces[newPosRow][newPosColumn].setY(newPosRow);
                            
                            breakLoop = true;
                            break;
                        } else {
                            String color = this.existingPieces.stream().findFirst().get().isWhite() ? "white" : "black";
                            System.out.println("Game over, player " + color);
                            game.changeState(MenuState.getInstance(), window);
                        }
                    }
                    if(breakLoop)
                        break;
                }
                if(breakLoop)
                    break;
            }
            if(breakLoop)
                break;
        }
    }


    private void move(Sprite[][] sprites, Piece[][] allPieces, AI ai) {
        int min = 0;
        int max = possibleMovementPair.size() - 1;
        int pieceToMove;
        int toMove;

        do {
            pieceToMove = random.nextInt((max - min) + 1) + min;

        } while (possibleMovementPair.get(pieceToMove).size() <= 0);

        classPieceToMove = pieceToMove;
        
        max = possibleMovementPair.get(pieceToMove).size() - 1;
        
        toMove = random.nextInt((max - min) + 1) + min;
        classToMove = toMove;
        newPosRow = possibleMovementPair.get(pieceToMove).get(toMove).getRow();
        newPosColumn = possibleMovementPair.get(pieceToMove).get(toMove).getColumn();

        // Sätter en vector till nya positionen på brädet
        Vector2f vector2f = new Vector2f(newPosColumn * 128 + 19, newPosRow * 128 + 19);
        // Flyttar spriten till den nya positionen via vectorns x och y värden
        sprites[existingPieces.get(pieceToMove).getY()][existingPieces.get(pieceToMove).getX()].setPosition(vector2f.x, vector2f.y);
        
        oldPosRow = existingPieces.get(pieceToMove).getY();
        oldPosColumn = existingPieces.get(pieceToMove).getX();
        
        Piece tempPiece = allPieces[existingPieces.get(pieceToMove).getY()][existingPieces.get(pieceToMove).getX()];
        tempPiece.setHasMoved(true);
        
        Sprite tempSprite = sprites[oldPosRow][oldPosColumn];
        Sprite emptySprite = new Sprite();
        
        // Sätter pjäsen som rörde sig till nytt y-värde via newPosColumn
        existingPieces.get(pieceToMove).setX(newPosColumn);
        
        // Sätter pjäsen som rörde sig till nytt y-värde via newPosColumn
        existingPieces.get(pieceToMove).setY(newPosRow);
        
        allPieces[newPosRow][newPosColumn] = tempPiece;
        
        for(int i = 0; i < ai.existingPieces.size(); ++i) {
            if(ai.existingPieces.get(i).getX() == tempPiece.getX() && ai.existingPieces.get(i).getY() == tempPiece.getY()) {
                System.out.println("Removed from list : " + ai.existingPieces.get(i));
                ai.existingPieces.remove(i);
            }
        }
        
        sprites[newPosRow][newPosColumn] = tempSprite;
        sprites[oldPosRow][oldPosColumn] = emptySprite;
        allPieces[oldPosRow][oldPosColumn] = null;
    }
    
    public void draw(RenderWindow window) {
        for(List<DrawLine> l : line) {
            for(DrawLine dl : l) {
                dl.draw(window);
            }
        }
        if(moveLine != null) {
            moveLine.draw(window);
        }
    }
    
    public void clearLines() {
        line.clear();
        for (Piece piece : existingPieces) {
            piece.clearLines();
        }
    }
    
    private void clearMoveLine() {
        moveLine = null;
    }
    
    public List<Piece> getExistingPieces() {
        return existingPieces;
    }
}
