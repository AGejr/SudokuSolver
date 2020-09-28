package base;

public class Sudoku
{
    // MEMBERS

    public static int[][] mGrid;
    private static final int mGridWidth = 9;

    // CONSTRUCTORS

    Sudoku(int[][] grid)
    {
        mGrid = grid;
    }

    // MEMBER METHODS

    public static void solve()
    {
        long startTime = System.nanoTime();

        if (!BTAlgorithm())
        {
            System.out.print("ERROR: no solution found\n");
            return;
        }

        long stopTime = System.nanoTime();
        System.out.print("\nExecution time: " + ((stopTime - startTime) / Math.pow(10,9)) + " seconds\n");
    }

    private static Boolean BTAlgorithm()
    {
        /*

        BTAlgorithm (BackTracking Algorithm)
        is a backtracking algorithm that works
        by brute force searching for correct values

        https://en.wikipedia.org/wiki/Sudoku_solving_algorithms

         */

        // Get next empty cell
        Vec2i nextCellPos = returnEmptyCellPos();

        // Success if there is no empty cells
        if (nextCellPos == null)
            return true;


        for(int i = 1; i <= 9; i++)
        {
            if (valueIsValid(nextCellPos, i))
            {
                // Assume the value is valid for now
                mGrid[nextCellPos.mX][nextCellPos.mY] = i;

                // Recurse
                // If recursive brach achieves success, return true
                if (BTAlgorithm())
                    return true;

                /*
                If the recursive branch should fail,
                the chosen value was wrong. Upon failing,
                reset the value which was assumed to be correct
                and increment the guess (integer i)
                */
                mGrid[nextCellPos.mX][nextCellPos.mY] = 0;
            }
        }
        /*
        If no guess from 1 to 9 is correct,
        then return (one step back) on the recursive branch
         */
        return false;
    }

    private static Boolean valueIsValid(Vec2i pos, int value)
    {
        int row = pos.mX;
        int column = pos.mY;

        // Test row
        for (int i = 0; i < (mGrid[row].length); i++)
        {
            // If the current column is the same as the column of the value being tested, continue
            if (i == column)
                continue;

            // If there is a value on the row that is equal to the value being tested, return false
            if (mGrid[row][i] == value)
                return false;
        }

        // Test column
        for (int i = 0; i < (mGrid.length); i++)
        {
            // If the current row is the same as the row of the value being tested, continue
            if (i == row)
                continue;

            // If there is a value on the column that is equal to the value being tested, return false
            if (mGrid[i][column] == value)
                return false;
        }

        // Test 3x3 square
        int offsetX = row / 3;
        int offsetY = column / 3;
        /*
        Note: values offsetX and offsetY are truncated on purpose,
        the sudoku is seperated into 9 3x3 squares. The same number
        cannot occur within the same square.
        |_|_|_|
        |_|_|_|
        |_|_|_|
         */
        for (int y = 0; y < 3; y++)
        {
            for (int x = 0; x < 3; x++)
            {
                // If the current row and column is the same as the
                // row and column of the value being tested, continue
                if ((offsetX + x) == row && (offsetY + y) == column)
                    continue;

                // If there is a value in the 3x3 grid that is equal
                // to the value being tested, return false
                if (mGrid[offsetX * 3 + x][offsetY * 3 + y] == value)
                    return false;
            }
        }

        // If no error is detected, return true
        return true;
    }

    public static Vec2i returnEmptyCellPos()
    {
        // Return the (x,y) position of the next
        // element in the grit that has a value of 0

        for (int i = 0; i < (mGrid.length); i++)
        {
            for (int j = 0; j < (mGrid[i].length); j++)
            {
                if (mGrid[i][j] != 0)
                    continue;

                return new Vec2i(i,j);
            }
        }

        return null;
    }

    public static void print()
    {
        System.out.print("\n");

        for(int[] row: mGrid)
        {
            System.out.print("[ ");

            for (int element: row)
            {
                System.out.print(element + " ");
            }

            System.out.print("]\n");
        }
    }
}
