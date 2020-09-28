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
        // Test row

        // Test column

        // Test 3x3 square

        return true;
    }

    public static Vec2i returnEmptyCellPos()
    {
        for (int i = 0; i < (mGrid.length - 1); i++)
        {
            for (int j = 0; j < (mGrid[i].length - 1); j++)
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
