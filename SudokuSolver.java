/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver;

import java.util.HashMap;

public class SudokuSolver {
    static int DIMENSION = 9;
    static int BLOCK_DIMENSION = 3;
    
    void solve(int[][] sudoku_board) {
        //First Check if it is a 9x9 matrix
        
        //start solving using recursive-solve
        boolean is_solved = recursive_solve(sudoku_board, 0, 0);
        if(!is_solved) {
            System.out.println("Not Solvable");
        } else {
            print_board(sudoku_board);
        }
    }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    private boolean recursive_solve(int[][] sudoku_board, int curr_row, int curr_col) {
        
        if(curr_row == SudokuSolver.DIMENSION)
            return true;
        if(curr_col == SudokuSolver.DIMENSION)
            throw new UnsupportedOperationException("Should never reach here.");
        
        boolean is_solved = false;
        int next_row = curr_col == SudokuSolver.DIMENSION - 1 ? curr_row + 1 : curr_row;
        int next_col = curr_col == SudokuSolver.DIMENSION - 1 ? 0 : curr_col + 1;
        
        if(sudoku_board[curr_row][curr_col] == -1) {
            for(int i = 0; i < SudokuSolver.DIMENSION ; i++) {
                //Try every i for this row
                sudoku_board[curr_row][curr_col] = i+1;               
                if(is_assignment_consistent(sudoku_board, curr_row, curr_col)) {
                    //System.out.println("Setting row:" + curr_row + " col:" + curr_col + " " + sudoku_board[curr_row][curr_col]);
                    is_solved = recursive_solve(sudoku_board, next_row, next_col);
                    if(is_solved) {
                        return true;
                    }
                }
            }
        } else {
            return recursive_solve(sudoku_board, next_row, next_col);
        }
        
        sudoku_board[curr_row][curr_col] = -1;
        //System.out.println("Setting row:" + curr_row + " col:" + curr_col + " -1");
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private boolean is_block_consistent(int[][] sudoku_board, int curr_row, int curr_col, boolean debug) {
    /**
     * @param args the command line arguments
     */
    //Just need to check it for the current block, as inferred by the current row and column0
        int block_first_row = SudokuSolver.BLOCK_DIMENSION * (curr_row/SudokuSolver.BLOCK_DIMENSION);
        int block_first_col = SudokuSolver.BLOCK_DIMENSION * (curr_col/SudokuSolver.BLOCK_DIMENSION);
        HashMap hm = new HashMap();
        
        if(debug) {
            System.out.println("Checking Block Consistency");
        }
        for(int i = block_first_row, count_r = 0; count_r < SudokuSolver.BLOCK_DIMENSION; i++,count_r++) {
            for(int j = block_first_col, count_c = 0; count_c < SudokuSolver.BLOCK_DIMENSION; count_c++, j++) {
                if(sudoku_board[i][j] != -1) {
                    if(hm.containsKey(sudoku_board[i][j])) {
                        return false;
                    }
                    hm.put(sudoku_board[i][j], 1);
                    if(debug) {
                        System.out.println("ROW: " + i + " COLUMN: " + j + " " + sudoku_board[i][j]);
                    }
                }
            }
        }
                
        
        return true;
        
    }
    
    private boolean is_line_consistent(int[][] sudoku_board, int curr_row, int curr_col, boolean debug) {
        //Check for consistencies, both horizontally and vertically --- a check that the current assignment does not break a sudoku rule
        HashMap hm = new HashMap();
        if(debug) {
            System.out.println("Checking Vertical Consistency");
        }
        for (int i = 0; i < SudokuSolver.DIMENSION; i++) {
            if (sudoku_board[i][curr_col] != -1) {
                if (hm.containsKey(sudoku_board[i][curr_col])) {
                    return false;
                }
                hm.put(sudoku_board[i][curr_col], 1);
                if(debug) {
                    System.out.println("ROW: " + i + " " + sudoku_board[i][curr_col]);
                }
            }
        }
        
        hm.clear();
        
        if(debug) {
            System.out.println("Checking Horizontal Consistency");
        }
        
        for (int i = 0; i < SudokuSolver.DIMENSION; i++) {
            if (sudoku_board[curr_row][i] != -1) {
                if (hm.containsKey(sudoku_board[curr_row][i])) {
                    return false;
                }
                hm.put(sudoku_board[curr_row][i], 1);
                if(debug) {
                    System.out.println("Column: " + i + " " + sudoku_board[curr_row][i]);
                }
            }
        }
                
        return true;
    }

    private boolean is_assignment_consistent(int[][] sudoku_board, int curr_row, int curr_col) {
        boolean debug = false;
        //if(curr_row == 0 && curr_col == 3 && sudoku_board[curr_row][curr_col] == 3) debug = true;
        return (is_block_consistent(sudoku_board, curr_row, curr_col, debug) && is_line_consistent(sudoku_board, curr_row, curr_col, debug));
    }

    private void print_board(int[][] sudoku_board) {
        for(int i = 0; i < SudokuSolver.DIMENSION; i++ ) {
            for(int j = 0; j < SudokuSolver.DIMENSION; j++) {
                System.out.print("\t" + sudoku_board[i][j]);
            }
            System.out.println();
        }
    }

    
}
