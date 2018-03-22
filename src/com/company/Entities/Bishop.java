package com.company.Entities;

import com.company.Enums.PieceTypes;
import com.company.Managers.TextureManager;
import com.company.Utility.Movement;
import com.company.Utility.Pair;
import org.jsfml.graphics.Sprite;

import java.util.ArrayList;

public class Bishop extends Piece implements IEntity {

    private Sprite sprite;
    private Movement movement;


    public Bishop(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        super.setStartPosX(240);
        super.setPointValue(3);
        sprite = new Sprite();
        movement = new Movement();
    }

    @Override
    public PieceTypes type() {
        return PieceTypes.BISHOP;
    }

    @Override
    public Sprite getSprite(TextureManager textureManager, String filePath) {
        sprite.setTexture(textureManager.getTexture(filePath));
        sprite.setTextureRect(getIntRect());
        return sprite;
    }

    @Override
    public void checkMovement(Piece[][] allPieces, Integer currentRow, Integer currentColumn) {
        movement.bishopCheckMovement(allPieces, super.pair, currentRow, currentColumn);
    }
}
