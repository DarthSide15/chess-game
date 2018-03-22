package com.company.Utility;

import com.company.AI.AI;
import com.company.Entities.King;
import com.company.Entities.Piece;
import com.company.Enums.PieceTypes;
import org.jsfml.graphics.Color;
import java.util.ArrayList;

public class Movement {
    
    private int squareSize;
    private int squareHypotamus;
    private int horseDiagonal;
    private int offset;
    
    public Movement() {
        squareSize = 128;
        squareHypotamus = 181;
        horseDiagonal = 152;
        offset = 64;
    }
    
    public void rookCheckMovement(Piece[][] allPieces, ArrayList<Pair<Integer>> pair, int currentRow, Integer currentColumn) {
        ArrayList<Pair<Integer>> tempPair = pair;
        // UP
        if(currentRow > 0) {
            for (int row = currentRow - 1; row >= 0; --row) {
                if(allPieces[row][currentColumn] != null && allPieces[row][currentColumn].isWhite() == allPieces[currentRow][currentColumn].isWhite()) {
                    break;
                } else if(allPieces[row][currentColumn] != null && allPieces[row][currentColumn].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
                    setPairs(allPieces, tempPair, row, currentColumn, currentRow, currentColumn);
                    setLinesRed(allPieces[currentRow][currentColumn], currentRow, currentColumn, (currentRow - row) * squareSize, 180);
                    isKingChecked(allPieces, row, currentColumn);
                    /*if(allPieces[row][currentColumn].type() == PieceTypes.KING) {
                        King king = (King) allPieces[row][currentColumn];
                        king.setIsChecked(true);
                        System.out.println("King is checked");
                    }*/
                    break;
                } else {
                    setPairs(allPieces, tempPair, row, currentColumn, currentRow, currentColumn);
                    setLinesGreen(allPieces[currentRow][currentColumn], currentRow, currentColumn, (currentRow - row) * squareSize, 180);
                }
            }
        }

        // DOWN
        if(currentRow < 7) {
            for (int row = currentRow + 1; row < 7; ++row) {
                if(allPieces[row][currentColumn] != null && allPieces[row][currentColumn].isWhite() == allPieces[currentRow][currentColumn].isWhite()) {
                    break;
                } else if(allPieces[row][currentColumn] != null && allPieces[row][currentColumn].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
                    setPairs(allPieces, tempPair, row, currentColumn, currentRow, currentColumn);
                    setLinesRed(allPieces[currentRow][currentColumn], currentRow, currentColumn, (row - currentRow) * squareSize, 0);
                    isKingChecked(allPieces, row, currentColumn);
                    break;
                } else {
                    setPairs(allPieces, tempPair, row, currentColumn, currentRow, currentColumn);
                    setLinesGreen(allPieces[currentRow][currentColumn], currentRow, currentColumn, (row - currentRow) * squareSize, 0);
                }
            }
        }

        // LEFT
        if(currentColumn > 0) {
            for (int column = currentColumn - 1; column > 0; --column) {
                if(allPieces[currentRow][column] != null && allPieces[currentRow][column].isWhite() == allPieces[currentRow][currentColumn].isWhite()) {
                    break;
                } else if(allPieces[currentRow][column] != null && allPieces[currentRow][column].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
                    setPairs(allPieces, tempPair, currentRow, column, currentRow, currentColumn);
                    setLinesRed(allPieces[currentRow][currentColumn], currentRow, currentColumn, (currentColumn - column) * squareSize, 90);
                    isKingChecked(allPieces, currentRow, column);
                    break;
                } else {
                    setPairs(allPieces, tempPair, currentRow, column, currentRow, currentColumn);
                    setLinesGreen(allPieces[currentRow][currentColumn], currentRow, currentColumn, (currentColumn - column) * squareSize, 90);
                }
            }
        }

        // RIGHT
        if(currentColumn < 7) {
            for (int column = currentColumn + 1; column < 7; ++column) {
                if(allPieces[currentRow][column] != null && allPieces[currentRow][column].isWhite() == allPieces[currentRow][currentColumn].isWhite()) {
                    break;
                } else if(allPieces[currentRow][column] != null && allPieces[currentRow][column].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
                    setPairs(allPieces, tempPair, currentRow, column, currentRow, currentColumn);
                    allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, (column - currentColumn) * squareSize, Color.RED);
                    allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(270);
                    isKingChecked(allPieces, currentRow, column);
                    break;
                } else {
                    setPairs(allPieces, tempPair, currentRow, column, currentRow, currentColumn);
                    allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, (column - currentColumn) * squareSize, Color.GREEN);
                    allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(270);
                }
            }
        }
    }
    
    public void queenCheckMovement(Piece[][] allPieces, ArrayList<Pair<Integer>> pair, int currentRow, Integer currentColumn) {
        rookCheckMovement(allPieces, pair, currentRow, currentColumn);
        bishopCheckMovement(allPieces, pair, currentRow, currentColumn);
    }

    public void kingCheckMovement(Piece[][] allPieces, ArrayList<Pair<Integer>> pair, int currentRow, Integer currentColumn) {
        int column = currentColumn;
        int row = currentRow;
        final int finalRow = row;
        ArrayList<Pair<Integer>> tempPair = pair;

        // UP
        if(currentRow > 0 && allPieces[currentRow - 1][currentColumn] != null &&
                allPieces[currentRow - 1][currentColumn].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
            --row;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            row = currentRow;
            setLinesRed(allPieces[currentRow][currentColumn], currentRow, currentColumn, squareSize, 180);
        } else if(currentRow > 0 && allPieces[currentRow - 1][currentColumn] == null){
            --row;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            row = currentRow;
            setLinesGreen(allPieces[currentRow][currentColumn], currentRow, currentColumn, squareSize, 180);
        }

        // UP LEFT
        if(currentRow > 0 && currentColumn > 0 && allPieces[currentRow - 1][currentColumn - 1] != null && allPieces[currentRow - 1][currentColumn - 1].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
            --column;
            --row;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            column = currentColumn;
            row = currentRow;
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, squareSize, Color.RED);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(135);
        } else if(currentRow > 0 && currentColumn > 0 && allPieces[currentRow - 1][currentColumn - 1] == null) {
            --column;
            --row;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            column = currentColumn;
            row = currentRow;
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, squareSize, Color.GREEN);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(135);
        }

        // UP RIGHT
        if(currentRow > 0 && currentColumn < 7 && allPieces[currentRow - 1][currentColumn + 1] != null && allPieces[currentRow - 1][currentColumn + 1].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
            ++column;
            --row;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            column = currentColumn;
            row = currentRow;
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, squareSize, Color.RED);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(225);
        } else if(currentRow > 0 && currentColumn < 7 && allPieces[currentRow - 1][currentColumn + 1] == null) {
            ++column;
            --row;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            column = currentColumn;
            row = currentRow;
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, squareSize, Color.GREEN);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(225);
        }

        //DOWN
        if(currentRow < 7 && allPieces[currentRow + 1][currentColumn] != null && allPieces[currentRow + 1][currentColumn].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
            ++row;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            row = currentRow;
            setLinesRed(allPieces[currentRow][currentColumn], currentRow, currentColumn, squareSize, 0);
        }
        else if (currentRow < 7 && allPieces[currentRow + 1][currentColumn] == null) {
            ++row;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            row = currentRow;
            setLinesGreen(allPieces[currentRow][currentColumn], currentRow, currentColumn, squareSize, 0);
        }

        // DOWN RIGHT
        if(currentRow < 7 && currentColumn < 7 && allPieces[currentRow + 1][currentColumn + 1] != null && allPieces[currentRow + 1][currentColumn + 1].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
            ++column;
            ++row;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            column = currentColumn;
            row = currentRow;
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, squareSize, Color.RED);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(315);
        }
        else if(currentRow < 7 && currentColumn < 7 && allPieces[currentRow + 1][currentColumn + 1] == null) {
            ++column;
            ++row;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            column = currentColumn;
            row = currentRow;
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, squareSize, Color.GREEN);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(315);
        }

        // DOWN LEFT
        if(currentRow < 7 && currentColumn > 0 && allPieces[currentRow + 1][currentColumn - 1] != null && allPieces[currentRow + 1][currentColumn - 1].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
            --column;
            ++row;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            column = currentColumn;
            row = currentRow;
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, squareSize, Color.RED);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(45);
        }
        else if(currentRow < 7 && currentColumn > 0 && allPieces[currentRow + 1][currentColumn - 1] == null) {
            --column;
            ++row;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            column = currentColumn;
            row = currentRow;
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, squareSize, Color.GREEN);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(45);
        }

        // LEFT
        if (currentColumn > 0 && allPieces[currentRow][currentColumn - 1] != null && allPieces[currentRow][currentColumn - 1].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
            --column;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            column = currentColumn;
            setLinesRed(allPieces[currentRow][currentColumn], currentRow, currentColumn, squareSize, 90);
        } else if (currentColumn > 0 && allPieces[currentRow][currentColumn - 1] == null) {
            --column;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            column = currentColumn;
            setLinesGreen(allPieces[currentRow][currentColumn], currentRow, currentColumn, squareSize, 90);
        }

        // RIGHT
        if(currentColumn < 7 && allPieces[currentRow][currentColumn + 1] != null && currentColumn < 7 && allPieces[currentRow][currentColumn + 1].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
            ++column;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, squareSize, Color.RED);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(270);
        }
        else if (currentColumn < 7 && allPieces[currentRow][currentColumn + 1] == null) {
            ++column;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, squareSize, Color.GREEN);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(270);
        }
    }

    public void bishopCheckMovement(Piece[][] allPieces, ArrayList<Pair<Integer>> pair, int currentRow, Integer currentColumn) {
        ArrayList<Pair<Integer>> tempPair = pair;

        // LEFT UP
        if(currentRow > 0 && currentColumn > 0) {
            for (int row = currentRow - 1, column = currentColumn - 1; row >= 0 && column >= 0; --row, --column) {
                if(allPieces[row][column] != null && allPieces[row][column].isWhite() == allPieces[currentRow][currentColumn].isWhite()) {
                    break;
                } else if(allPieces[row][column] != null && allPieces[row][column].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
                    setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
                    allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset,  (currentRow - row) * squareHypotamus, Color.RED);
                    allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(135);
                    isKingChecked(allPieces, row, column);
                    break;
                } else {
                    setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
                    allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, (currentRow - row) * squareHypotamus, Color.GREEN);
                    allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(135);
                }
            }
        }

        // RIGHT UP
        if(currentRow > 0 && currentColumn < 7) {
            for (int row = currentRow - 1, column = currentColumn + 1; row >= 0 && column <= 7; --row, ++column) {
                if(allPieces[row][column] != null && allPieces[row][column].isWhite() == allPieces[currentRow][currentColumn].isWhite()) {
                    break;
                } else if(allPieces[row][column] != null && allPieces[row][column].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
                    setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
                    allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset,  (currentRow - row) * squareHypotamus, Color.RED);
                    allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(225);
                    isKingChecked(allPieces, row, column);
                    break;
                } else {
                    setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
                    allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, (currentRow - row) * squareHypotamus, Color.GREEN);
                    allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(225);
                }
            }
        }

        // LEFT DOWN
        if(currentRow < 7 && currentColumn > 0) {
            for (int row = currentRow + 1, column = currentColumn - 1; row <= 7 && column >= 0; ++row, --column) {
                if(allPieces[row][column] != null && allPieces[row][column].isWhite() == allPieces[currentRow][currentColumn].isWhite()) {
                    break;
                } else if(allPieces[row][column] != null && allPieces[row][column].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
                    setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
                    allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, (row - currentRow) * squareHypotamus, Color.RED);
                    allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(45);
                    isKingChecked(allPieces, row, column);
                    break;
                } else {
                    setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
                    allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, (row - currentRow) * squareHypotamus, Color.GREEN);
                    allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(45);
                }
            }
        }

        // RIGHT DOWN
        if(currentRow < 7  && currentColumn < 7) {
            for (int row = currentRow + 1, column = currentColumn + 1; row <= 7 && column <= 7; ++row, ++column) {
                if(allPieces[row][column] != null && allPieces[row][column].isWhite() == allPieces[currentRow][currentColumn].isWhite()) {
                    break;
                } else if(allPieces[row][column] != null && allPieces[row][column].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
                    setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
                    allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset,  (row - currentRow) * squareHypotamus, Color.RED);
                    allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(315);
                    isKingChecked(allPieces, row, column);
                    break;
                } else {
                    setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
                    allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, (row - currentRow) * squareHypotamus, Color.GREEN);
                    allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(315);
                }
            }
        }
    }

    public void knightCheckMovement(Piece[][] allPieces, ArrayList<Pair<Integer>> pair, int currentRow, int currentColumn) {
        ArrayList<Pair<Integer>> tempPair = pair;

        int column = currentColumn;
        int row = currentRow;

        // UP LEFT
        if(currentRow > 1 && currentColumn > 0 && allPieces[currentRow - 2][currentColumn - 1] != null && allPieces[currentRow - 2][currentColumn - 1].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
            --column;
            row -= 2;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            isKingChecked(allPieces, row, column);
            column = currentColumn;
            row = currentRow;
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, 2 * horseDiagonal, Color.RED);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(155);
        }
        else if(currentRow > 1 && currentColumn > 0 && allPieces[currentRow - 2][currentColumn - 1] == null) {
            --column;
            row -= 2;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            column = currentColumn;
            row = currentRow;
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, 2 * horseDiagonal, Color.GREEN);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(155);
        }

        // UP RIGHT
        if(currentRow > 1 && currentColumn < 7 && allPieces[currentRow - 2][currentColumn + 1] != null && allPieces[currentRow - 2][currentColumn + 1].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
            ++column;
            row -= 2;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            isKingChecked(allPieces, row, column);
            column = currentColumn;
            row = currentRow;
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, 2 * horseDiagonal, Color.RED);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(205);
        }
        else if(currentRow > 1 && currentColumn < 7 && allPieces[currentRow - 2][currentColumn + 1] == null) {
            ++column;
            row -= 2;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            column = currentColumn;
            row = currentRow;
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, 2 * horseDiagonal, Color.GREEN);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(205);
        }

        // RIGHT UP
        if(currentColumn < 6 && currentRow > 0 && allPieces[currentRow - 1][ currentColumn + 2] != null && allPieces[currentRow - 1][currentColumn + 2].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
            column += 2;
            --row;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            isKingChecked(allPieces, row, column);
            column = currentColumn;
            row = currentRow;
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, 2 * horseDiagonal, Color.RED);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(245);
        }
        else if(currentColumn < 6 && currentRow > 0 && allPieces[currentRow - 1][ currentColumn + 2] == null) {
            column += 2;
            --row;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            column = currentColumn;
            row = currentRow;
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, 2 * horseDiagonal, Color.GREEN);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(245);
        }

        // RIGHT DOWN
        if (currentColumn < 6 && currentRow < 7 && allPieces[currentRow + 1][currentColumn + 2] != null && allPieces[currentRow + 1][currentColumn + 2].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
            column += 2;
            ++row;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            isKingChecked(allPieces, row, column);
            column = currentColumn;
            row = currentRow;
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, 2 * horseDiagonal, Color.RED);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(295);
        } else if (currentColumn < 6 && currentRow < 7 && allPieces[currentRow + 1][currentColumn + 2] == null) {
            column += 2;
            ++row;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            column = currentColumn;
            row = currentRow;
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, 2 * horseDiagonal, Color.GREEN);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(295);
        }

        // DOWN RIGHT
        if (currentRow < 6 && currentColumn < 7 && allPieces[currentRow + 2][currentColumn + 1] != null && allPieces[currentRow + 2][currentColumn + 1].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
            ++column;
            row += 2;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            isKingChecked(allPieces, row, column);
            column = currentColumn;
            row = currentRow;
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, 2 * horseDiagonal, Color.RED);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(335);
        } else if (currentRow < 6 && currentColumn < 7 && allPieces[currentRow + 2][currentColumn + 1] == null) {
            ++column;
            row += 2;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            column = currentColumn;
            row = currentRow;
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, 2 * horseDiagonal, Color.GREEN);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(335);
        }

        // DOWN LEFT
        if(currentRow < 6 && currentColumn > 0 && allPieces[currentRow + 2][currentColumn - 1] != null && allPieces[currentRow + 2][currentColumn - 1].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
            --column;
            row += 2;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            isKingChecked(allPieces, row, column);
            column = currentColumn;
            row = currentRow;
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, 2 * horseDiagonal, Color.RED);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(25);
        }
        else if(currentRow < 6 && currentColumn > 0 && allPieces[currentRow + 2][currentColumn - 1] == null) {
            --column;
            row += 2;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            column = currentColumn;
            row = currentRow;
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, 2 * horseDiagonal, Color.GREEN);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(25);
        }

        // LEFT DOWN
        if(currentColumn > 1 && currentRow < 7 && allPieces[currentRow + 1][currentColumn - 2] != null && allPieces[currentRow + 1][currentColumn - 2].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
            column -= 2;
            ++row;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            isKingChecked(allPieces, row, column);
            column = currentColumn;
            row = currentRow;
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, 2 * horseDiagonal, Color.RED);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(65);
        }
        else if(currentColumn > 1 && currentRow < 7 && allPieces[currentRow + 1][currentColumn - 2] == null) {
            column -= 2;
            ++row;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            column = currentColumn;
            row = currentRow;
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, 2 * horseDiagonal, Color.GREEN);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(65);
        }

        // LEFT UP
        if(currentColumn > 1 && currentRow > 0 && allPieces[currentRow - 1][ currentColumn - 2] != null && allPieces[currentRow - 1][currentColumn - 2].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
            column -= 2;
            --row;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            isKingChecked(allPieces, row, column);
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, 2 * horseDiagonal, Color.RED);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(115);
        }
        else if(currentColumn > 1 && currentRow > 0 && allPieces[currentRow - 1][ currentColumn - 2] == null) {
            column -= 2;
            --row;
            setPairs(allPieces, tempPair, row, column, currentRow, currentColumn);
            allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, 2 * horseDiagonal, Color.GREEN);
            allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(115);
        }
    }

    public void pawnCheckMovement(Piece[][] allPieces, ArrayList<Pair<Integer>> pair, int currentRow, int currentColumn) {
        while (!allPieces[currentRow][currentColumn].getLines().isEmpty()) {
            allPieces[currentRow][currentColumn].clearLines();
        }
        
        ArrayList<Pair<Integer>> tempPair = pair;
        //System.out.println("Row: " + currentRow + "\r\nColumn: " + currentColumn + "*********************************************************");
        // White Pawn
        if (allPieces[currentRow][currentColumn].isWhite()) {
            // If White Pawn has moved we can only move one step
            if (allPieces[currentRow][currentColumn].getHasMoved()) {
                for (int row = currentRow - 1; row >= currentRow - 1; --row) {
                    if(currentRow == 0) {
                        break;
                    }
                    // Piece can't move right if it is furthest to the right on the board
                    whiteMoveRightLeft(allPieces, currentRow, currentColumn, tempPair, row);
                    
                    // If there is a piece in front of the Pawn it can't move so we break the for-loop
                    if (allPieces[row][currentColumn] != null) {
                        break;
                    } else {
                        setPairs(allPieces, tempPair, row, currentColumn, currentRow, currentColumn);
                        setLinesGreen(allPieces[currentRow][currentColumn], currentRow, currentColumn, squareSize, 180);
                    }
                }
            } else {
                for (int row = currentRow - 1; row >= currentRow - 2; --row) {
                    // Piece can't move right if it is furthest to the right on the board
                    if(row == currentRow - 1) {
                        whiteMoveRightLeft(allPieces, currentRow, currentColumn, tempPair, row);
                    }
                    // If there is a piece in front of the Pawn it can't move so we break the for-loop
                    if (allPieces[row][currentColumn] != null) {
                        break;
                    } else {
                        setPairs(allPieces, tempPair, row, currentColumn, currentRow, currentColumn);
                        setLinesGreen(allPieces[currentRow][currentColumn], currentRow, currentColumn, (currentRow - row) * squareSize, 180);
                    }
                }
            }
        }
        
        
        // Black Pawn
        else {
            // If Black Pawn has moved we can only move one step
            if (allPieces[currentRow][currentColumn].getHasMoved()) {
                for (int row = currentRow + 1; row <= currentRow + 1; ++row) {
                    if(currentRow == 7) {
                        break;
                    }
                    moveBlackRightLeft(allPieces, currentRow, currentColumn, tempPair, row, 315, 45);
                    // If there is a piece in front of the Pawn it can't move so we break the for-loop
                    if (allPieces[row][currentColumn] != null) {
                        break;
                    } else {
                        setPairs(allPieces, tempPair, row, currentColumn, currentRow, currentColumn);
                        allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, squareSize, Color.GREEN);
                    }
                }
            } else {
                for (int row = currentRow + 1; row <= currentRow + 2; ++row) {
                    if(row == currentRow + 1) {
                        moveBlackRightLeft(allPieces, currentRow, currentColumn, tempPair, row, 225, 135);
                    }
                    // If there is a piece in front of the Pawn it can't move so we break the for-loop
                    if (allPieces[row][currentColumn] != null) {
                        break;
                    } else {
                        setPairs(allPieces, tempPair, row, currentColumn, currentRow, currentColumn);
                        allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, (row - currentRow) * squareSize, Color.GREEN);
                    }
                }
            }
        }
    }
    
    private void whiteMoveRightLeft(Piece[][] allPieces, int currentRow, int currentColumn, ArrayList<Pair<Integer>> tempPair, int row) {
        if (currentColumn != 7) {
            if (allPieces[row][currentColumn + 1] != null && allPieces[row + 1][currentColumn].isWhite() != allPieces[row][currentColumn + 1].isWhite()) {
                setPairs(allPieces, tempPair, row, currentColumn + 1, currentRow, currentColumn);
                allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, squareHypotamus, Color.RED);
                isKingChecked(allPieces, row, currentColumn + 1);
                allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(225);
            }
        }
        
        // Piece can't move left if it is furthest to the left on the board
        if (currentColumn != 0) {
            // Check if there is a enemy piece UP LEFT
            if (allPieces[row][currentColumn - 1] != null && allPieces[row + 1][currentColumn].isWhite() != allPieces[row][currentColumn - 1].isWhite()) {
                setPairs(allPieces, tempPair, row, currentColumn - 1, currentRow, currentColumn);
                allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, squareHypotamus, Color.RED);
                isKingChecked(allPieces, row, currentColumn - 1);
                allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(135);
            }
        }
    }
    
    private void moveBlackRightLeft(Piece[][] allPieces, int currentRow, int currentColumn, ArrayList<Pair<Integer>> tempPair, int row, int degreesToRotate, int degreesToRotate2) {
        if (currentColumn <   7) {
            // Check if there is a enemy piece DOWN RIGHT
            if (allPieces[row][currentColumn + 1] != null && allPieces[row - 1][currentColumn].isWhite() != allPieces[row][currentColumn + 1].isWhite()) {
                setPairs(allPieces, tempPair, row, currentColumn + 1, currentRow, currentColumn);
                allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, squareHypotamus, Color.RED);
                isKingChecked(allPieces, row, currentColumn + 1);
                allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(degreesToRotate);
            }
        }
        // Piece can't move left if it is furthest to the left on the board
        if (currentColumn > 0) {
            // Check if there is a enemy piece DOWN LEFT
            if (allPieces[row][currentColumn - 1] != null && allPieces[row - 1][currentColumn].isWhite() != allPieces[row][currentColumn - 1].isWhite()) {
                setPairs(allPieces, tempPair, row, currentColumn - 1, currentRow, currentColumn);
                allPieces[currentRow][currentColumn].setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, squareHypotamus, Color.RED);
                isKingChecked(allPieces, row, currentColumn - 1);
                allPieces[currentRow][currentColumn].getLines().get(allPieces[currentRow][currentColumn].getLines().size() - 1).rotateLine(degreesToRotate2);
            }
        }
    }
    
    private void setPairs(Piece[][] allPieces,  ArrayList<Pair<Integer>> pair, int row, int column, int currentRow, int currentColumn) {
        if(allPieces[row][column] == null) {
            Pair whereToMove = new Pair(row, column);
            pair.add(whereToMove);
        }  else if(allPieces[row][column].isWhite() != allPieces[currentRow][currentColumn].isWhite()) {
            Pair whereToMove = new Pair(row, column);
            pair.add(whereToMove);
        }
    }
    
    private void setLinesGreen(Piece piece, int currentRow, Integer currentColumn, int endPosY, int degreesToRotate) {
        piece.setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, endPosY, Color.GREEN);
        piece.getLines().get(piece.getLines().size() - 1).rotateLine(degreesToRotate);
    }
    
    private void setLinesRed(Piece piece, int currentRow, Integer currentColumn, int endPosY, int degreesToRotate) {
        piece.setLine(5, currentColumn * squareSize + offset, currentRow * squareSize + offset, endPosY, Color.RED);
        piece.getLines().get(piece.getLines().size() - 1).rotateLine(degreesToRotate);
    }
    
    private void isKingChecked(Piece[][] allPieces, int row, int column) {
        if(allPieces[row][column].type() == PieceTypes.KING) {
            King king = (King) allPieces[row][column];
            king.setIsChecked(true);
            System.out.println("King is checked");
        }
    }
}
