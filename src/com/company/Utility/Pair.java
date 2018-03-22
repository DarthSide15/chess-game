package com.company.Utility;

public class Pair<T>{
        private T row;
        private T column;
        
        public Pair(T row, T column) {
            this.row = row;
            this.column = column;
        }
        
        public T getRow() {
            return row;
        }
        
        public T getColumn() {
            return column;
        }
    
    @Override
    public String toString() {
        return "Row: " + row + "\r\nColumn: " + column + "\r\n";
    }
    
    //Pair<Integer> pair = new Pair<Integer>(1,2);
}
