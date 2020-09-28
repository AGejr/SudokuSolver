package base;

public class Sudoku
{
    // MEMBERS

    public static int[][] m_grid;

    // CONSTRUCTORS

    Sudoku(int[][] grid)
    {
        m_grid = grid;
    }

    // MEMBER METHODS

    public static void solve()
    {

    }

    public static void print()
    {
        for(int[] row: m_grid)
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
