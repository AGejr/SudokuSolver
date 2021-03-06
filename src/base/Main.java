package base;

public class Main {

    public static void main(String[] args)
    {
        int grid[][] = {
                {1, 0, 0, 0, 6, 0, 0, 0, 0},
                {0, 6, 0, 3, 0, 0, 9, 1, 0},
                {0, 2, 4, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 9, 0, 0, 0, 7, 1},
                {0, 0, 0, 0, 5, 0, 0, 4, 9},
                {0, 0, 0, 0, 0, 3, 0, 5, 6},
                {0, 0, 0, 6, 0, 0, 1, 3, 0},
                {0, 1, 2, 7, 0, 0, 0, 0, 0},
                {0, 5, 8, 0, 0, 0, 7, 0, 0} };

        Sudoku sudoku = new Sudoku(grid);

        sudoku.print();

        sudoku.solve();

        sudoku.print();
    }
}
