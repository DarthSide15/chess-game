package com.company.Entities;

import com.company.Enums.PieceTypes;
import com.company.Managers.TextureManager;
import org.jsfml.graphics.Sprite;

public interface IEntity {
    PieceTypes type();
    Sprite getSprite(TextureManager textureManager, String filePath);
    void checkMovement(Piece[][] allPieces, Integer currentRow, Integer currentColumn);
}
