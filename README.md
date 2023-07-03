# SUDOKU

Sudoku game generator in Java

Sudoku rules:

- In all 9 sub matrices 3×3 the elements should be 1-9, without repetition.
- In all rows there should be elements between 1-9 , without repetition.
- In all columns there should be elements between 1-9 , without repetition.

Steps:
- Randomly take any number 1-9.
- Check if it is safe to put in the cell.(row , column and box)
- If safe, place it and increment to next location and go to step 1.
- If not safe, then without incrementing go to step 1.
- Once matrix is fully filled, remove k no. of elements randomly to complete game.

Improved Solution : We can improve the solution, if we understand a pattern in this game. We can observe that all 3 x 3 matrices, which are diagonally present are independent of other 3 x 3 adjacent matrices initially, as others are empty. 

Following is the improved logic for the problem.
1. Fill all the diagonal 3x3 matrices.
2. Fill recursively rest of the non-diagonal matrices.
   For every cell to be filled, we try all numbers until
   we find a safe number to be placed.  
3. Once matrix is fully filled, remove K elements
   randomly to complete game.
