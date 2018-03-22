package com.company.Entities;

import com.company.AI.AI;
import com.company.Enums.PieceTypes;
import com.company.Managers.TextureManager;
import com.company.Utility.Movement;
import com.company.Utility.Pair;
import org.jsfml.graphics.Sprite;

import java.util.ArrayList;

public class King extends Piece implements IEntity {

    private Sprite sprite;
    private Movement movement;
    private boolean isChecked;


    public King(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        super.setStartPosX(60);
        super.setPointValue(100);
        sprite = new Sprite();
        movement = new Movement();
        isChecked = false;
    }

    @Override
    public PieceTypes type() {
        return PieceTypes.KING;
    }

    @Override
    public Sprite getSprite(TextureManager textureManager, String filePath) {
        sprite.setTexture(textureManager.getTexture(filePath));
        sprite.setTextureRect(getIntRect());
        return sprite;
    }

    public void checkMovement(Piece[][] allPieces, Integer currentRow, Integer currentColumn) {
        super.pair.clear();
        movement.kingCheckMovement(allPieces, super.pair, currentRow, currentColumn);
    }
    
    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
    
    public boolean getIsChecked() {
        return isChecked;
    }
}
