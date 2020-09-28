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
        if (!BTAlgorithm())
        {
            System.out.print("ERROR: no solution found\n");
        }
    }

    private static Boolean BTAlgorithm()
    {
        // Get next empty cell
        Vec2i nextCellPos = returnEmptyCellPos();

        // Success if there is no empty cells
        if (nextCellPos == null)
            return true;

        for(int i = 1; i <= 9; i++)
        {
            if (valueIsValid(nextCellPos, i))
            {
                mGrid[nextCellPos.mX][nextCellPos.mY] = i;

                // Recurse
                // If recursive brach achieves success, return true
                if (BTAlgorithm())
                    return true;

                // If the recursive branch should fail, delete value and backtrack
                mGrid[nextCellPos.mX][nextCellPos.mY] = 0;
            }
        }

        return false;
    }

    private static Boolean valueIsValid(Vec2i pos, int value)
    {
        int row = pos.mX;
        int column = pos.mY;

        // Test row
        for (int i = 0; i < (mGrid[row].length); i++)
        {
            if (i == column)
                continue;

            if (mGrid[row][i] == value)
                return false;
        }

        // Test column
        for (int i = 0; i < (mGrid.length); i++)
        {
            if (i == row)
                continue;

            if (mGrid[i][column] == value)
                return false;
        }

        // Test 3x3 square
        int offsetX = row / 3;
        int offsetY = column / 3;

        for (int y = 0; y < 3; y++)
        {
            for (int x = 0; x < 3; x++)
            {
                if ((offsetX + x) == row && (offsetY + y) == column)
                    continue;

                if (mGrid[offsetX * 3 + x][offsetY * 3 + y] == value)
                    return false;
            }
        }

        return true;
    }

    public static Vec2i returnEmptyCellPos()
    {
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
