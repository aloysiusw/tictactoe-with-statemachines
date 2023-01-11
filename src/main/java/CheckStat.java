import javax.swing.*;

public class CheckStat
{
    private int sameValues(String boardValues[])
    {
        int same = 0;
        for (int n = 0; n < 9; n++)
        {
            if ((boardValues[n].equals("X")) || (boardValues[n].equals("O")))
            {
                same++;
            }
            else
            {
            }
        }
        return same;
    }

    static boolean draw;

    public boolean gameDraw(String boardValues[])
    {
        this.sameValues(boardValues);
        if (this.sameValues(boardValues) == 9)
        {
            draw = true;
        }
        else
        {
            draw = false;
        }
        return draw;
    }
    static String[] bingo = new String[8];
    private void gameWon(String boardValues[])
    {
        bingo[0] = boardValues[0] + boardValues[1] + boardValues[2];
        bingo[1] = boardValues[3] + boardValues[4] + boardValues[5];
        bingo[2] = boardValues[6] + boardValues[7] + boardValues[8];
        bingo[3] = boardValues[0] + boardValues[3] + boardValues[6];
        bingo[4] = boardValues[1] + boardValues[4] + boardValues[7];
        bingo[5] = boardValues[2] + boardValues[5] + boardValues[8];
        bingo[6] = boardValues[0] + boardValues[4] + boardValues[8];
        bingo[7] = boardValues[6] + boardValues[4] + boardValues[2];
    }
    static int state;
    public int whoWon(String boardValues[])
    {
        this.gameWon(boardValues);
        for (int l = 0; l < 8; l++)
        {
            if (bingo[l].equals("XXX"))
            {
                state = 1;
                break;
            }
            else if (bingo[l].equals("OOO"))
            {
                state = 2;
                break;
            }
            else if(gameDraw(boardValues))
            {
                state = 3;
                break;
            }
            else
            {
                state = 0;
            }
        }
        return state;
    }
}

