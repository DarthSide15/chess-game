package com.company.Entities;

import com.company.Enums.PieceTypes;
import com.company.Managers.TextureManager;
import com.company.Utility.Movement;
import com.company.Utility.Pair;
import org.jsfml.graphics.Sprite;

import java.util.ArrayList;

public class Queen extends Piece implements IEntity{

    private Sprite sprite;
    private Movement movement;


    public Queen(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        super.setStartPosX(0);
        super.setPointValue(9);
        sprite = new Sprite();
        movement = new Movement();
    }

    @Override
    public PieceTypes type() {
        return PieceTypes.QUEEN;
    }
    
    @Override
    public Sprite getSprite(TextureManager textureManager, String filePath) {
        sprite.setTexture(textureManager.getTexture(filePath));
        sprite.setTextureRect(getIntRect());
        return sprite;
    }
    
    @Override
    public void checkMovement(Piece[][] allPieces, Integer currentRow, Integer currentColumn) {
        movement.queenCheckMovement(allPieces, super.pair, currentRow, currentColumn);
    }
}