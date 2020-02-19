/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver;

public class Main {
    public static void main(String[] args) {
        // TODO code application logic here
        SudokuSolver solver = new SudokuSolver();
        int[][] sudoku_board = new int[][] {
            {-1,-1,-1,-1,-1,-1,-1,1,-1}
                ,{5,3,-1,7,-1,8,-1,6,-1}
                ,{2,-1,-1,-1,4,-1,-1,9,-1}
                ,{-1,-1,9,-1,3,-1,-1,7,-1}
                ,{-1,-1,8,-1,1,-1,4,-1,5}
                ,{-1,-1,-1,-1,6,-1,-1,-1,-1}
                ,{4,-1,-1,9,-1,-1,-1,-1,1}
                ,{-1,7,-1,-1,8,-1,-1,2,-1}
                ,{-1,-1,6,5,-1,-1,-1,-1,7}
            
        };
        solver.solve(sudoku_board);
    }
}
