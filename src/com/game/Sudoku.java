package com.game;

import java.util.Arrays;

public class Sudoku {
    private final int DEFAULT = 0;

    int[][] matrix = new int[9][9];     // Matrix of numbers
    int missing;                        // Number of missing digits

    public Sudoku(int missing) throws Exception {
        if (missing < 1 || missing > 81)
            throw new Exception("Number of missing digits must be 1 to 81.");

        this.missing = missing;

        this.initialValues();
        this.fillDiagonal();
        this.fillRest(0, 3);
        this.clearRandomCells();
    }

    private void initialValues(){
        for (int i = 0; i < 9; i++)
            Arrays.fill(matrix[i], this.DEFAULT);
    }

    private void fillDiagonal(){
        for(int i = 0; i < 9; i = i+3)
            fillDiagonalBlock(i, i);
    }

    boolean fillRest(int row, int col){
        if(col >= 9) {
            if (row >= 9)
                return true;

            // Continues with next row
            row = row + 1;
            col = 0;
        }

        // Row less than 3, will skip first diagonal block
        if (row < 3)
        {
            if (col < 3)
                col = 3;
        }
        // Row 3 to 6, will skip center (second diagonal) block
        else if (row < 6)
        {
            if (col == (row / 3) * 3)
                col =  col + 3;
        }
        // Row grater than 6, will skip last (third diagonal) block
        else
        {
            if (col == 6)
            {
                row = row + 1;
                col = 0;

                if (row >= 9)
                    return true;
            }
        }

        // From 1-9
        for (int number = 1; number <= 9; number++)
        {
            // Checks if the number can be filled in the current cell
            if (canFill(row, col, number))
            {
                this.matrix[row][col] = number;

                // Continues with next cell of the column
                if (fillRest(row, col + 1))
                    return true;

                this.matrix[row][col] = 0;
            }
        }

        return false;
    }
    
    private void fillDiagonalBlock(int row, int col){
        int number;
        for (int i = 0; i < 3; i++)
            for(int y = 0; y < 3; y++){
                do {
                  number = this.randomNumber();
                } while(isInBlock(row, col, number));

                this.matrix[row + i][col + y] = number;
            }
    }

    private boolean isInBlock(int row, int col, int number){
        for (int i = 0; i < 3; i++)
            for(int y = 0; y < 3; y++)
                if(this.matrix[row + i][col + y] == number)
                    return true;

        return false;
    }

    private boolean isInRow(int row, int number){
        for (int i = 0; i < 9; i++)
            if (this.matrix[row][i] == number)
                return true;

        return false;
    }

    private boolean isInCol(int col, int number){
        for (int i = 0; i < 9; i++)
            if (this.matrix[i][col] == number)
                return true;

        return false;
    }

    // Check if a number can be filled in given cell (col/row)
    private boolean canFill(int row, int col, int number){
        return !(this.isInRow(row, number) ||
                this.isInCol(col, number) ||
                this.isInBlock(
                        row - row % 3,
                        col - col % 3,
                        number));
    }

    // Generates a random number between 1-9
    private int randomNumber(){
        return (int)Math.floor(Math.random() * (9 - 1 + 1) + 1);
    }

    private void clearRandomCells(){
        int count = this.missing;

        while(count != 0){
            int row = randomNumber() - 1;
            int col = randomNumber() - 1;

            if(this.matrix[row][col] == this.DEFAULT)
                continue;

            this.matrix[row][col] = this.DEFAULT;
            count--;
        }
    }

    public void print(){
        for (int i = 0; i < 9; i++){
            for (int y = 0; y < 9; y++)
                System.out.print(this.matrix[i][y] + "  ");

            System.out.println();
        }
    }
}
