package com.company.Entities;

import com.company.Enums.PieceTypes;
import com.company.Managers.TextureManager;
import com.company.Utility.Movement;
import com.company.Utility.Pair;
import org.jsfml.graphics.Sprite;

import java.util.ArrayList;

public class Rook extends Piece implements IEntity {

    private Sprite sprite;
    private boolean hasMoved;
    private Movement movement;
    
    
    public Rook(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        super.setStartPosX(120);
        super.setPointValue(5);
        sprite = new Sprite();
        movement = new Movement();
    }

    @Override
    public PieceTypes type() {
        return PieceTypes.ROOK;
    }
    
    @Override
    public Sprite getSprite(TextureManager textureManager, String filePath) {
        sprite.setTexture(textureManager.getTexture(filePath));
        sprite.setTextureRect(getIntRect());
        return sprite;
    }
    
    public void checkMovement(Piece[][] allPieces, Integer currentRow, Integer currentColumn) {
        movement.rookCheckMovement(allPieces, super.pair, currentRow, currentColumn);
    }
}