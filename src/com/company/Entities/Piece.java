package com.company.Entities;

import com.company.Enums.PieceTypes;
import com.company.Managers.TextureManager;
import com.company.Utility.DrawLine;
import com.company.Utility.Pair;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece implements IEntity {

    private int x;
    private int y;
    private int startPosX;
    private int startPosY;
    private int endPosX;
    private int endPosY;
    private int pointValue;
    
    private boolean isWhite;
    private boolean hasMoved;
    
    protected ArrayList<Pair<Integer>> pair;
    
    private List<DrawLine> line = new ArrayList<>();
    
    

    public Piece() { }

    public Piece(int x, int y, boolean isWhite) {

        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
        startPosY = 0;
        endPosX = 60;
        endPosY = 60;
        pair = new ArrayList<>();
        hasMoved = false;
    }

    public ArrayList<Pair<Integer>> getPair() {
        return pair;
    }
    
    public void clearPair() {
        pair.clear();
    }
    
    public boolean isWhite() {
        return isWhite;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setStartPosX(int startPosX) {
        this.startPosX = startPosX;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public List<DrawLine> getLines() {
        return line;
    }
    
    public void setLine(float thickness, float startingPosX, float startingPosY, float endPosY, Color color) {
        line.add(new DrawLine(thickness, startingPosX, startingPosY, endPosY, color));
    }
    
    public void clearLines() {
        line.clear();
    }
    
    public boolean getHasMoved() {
        return hasMoved;
    }
    
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
    
    public IntRect getIntRect() {
        if (isWhite) {
            startPosY = 60;
        }
        return new IntRect(startPosX, startPosY, endPosX, endPosY);
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    public int getPointValue() {
        return pointValue;
    }
}