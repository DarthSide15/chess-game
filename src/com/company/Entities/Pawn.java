package com.company.Entities;

import com.company.Enums.PieceTypes;
import com.company.Managers.TextureManager;
import com.company.Utility.DrawLine;
import com.company.Utility.Movement;
import com.company.Utility.Pair;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece implements IEntity {

    private Sprite sprite;
    private Movement movement;

    
    public Pawn(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        super.setStartPosX(300);
        super.setPointValue(1);
        sprite = new Sprite();
        
        // Movement
        super.setHasMoved(false);
        movement = new Movement();
    }

    @Override
    public PieceTypes type() {
        return PieceTypes.PAWN;
    }
    
    @Override
    public Sprite getSprite(TextureManager textureManager, String filePath) {
        sprite.setTexture(textureManager.getTexture(filePath));
        sprite.setTextureRect(getIntRect());
        return sprite;
    }
    
    public void checkMovement(Piece[][] allPieces, Integer currentRow, Integer currentColumn) {
        movement.pawnCheckMovement(allPieces, super.pair, currentRow, currentColumn);
    }
}