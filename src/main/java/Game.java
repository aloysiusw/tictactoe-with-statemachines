import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialLiteTheme;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Name:    Aloysius Arno Wiputra
 * E-mail:  aloysiuswiputra@gmail.com / awiputra@nyit.edu
 * NYIT ID: 1244139
 * Last modification (DD/MM/YY): 30/07/21
 * Changelog:
 * - Reimplemented the coin flip
 * Todo:
 *  - Add a mode selection (1p/2p)
 *  - Assign a "coin flip" on game ending, assign who moves first based on game winning state
 *  - Rewrite codebase with better practices
 *  - fix initial run bug
 */

public class Game extends JPanel
{
    String[] boardValues = new String[9];

    int currentPlayerWin = 0;
    int currentCPUWin = 0;
    int opponentMove = 0;

    int currentGameState = 0;

    public static void main(String[] args)
    {
        //new Game();
        JFrame TicTacToeV2 = new JFrame();
        try
        {
            UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialLiteTheme()));
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        TicTacToeV2.setSize(630, 500);
        TicTacToeV2.setContentPane(new Game().panelMain);
        TicTacToeV2.setVisible(true);
    }

    public Game()
    {

        initComponents();
        text_Tally.setText(currentPlayerWin + "-" + currentCPUWin);
        for (int i = 0; i < 9; i++)
        {
            boardValues[i] = "null";
        }
        btn_Reset.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                resetBoard();
            }
        });

        System.out.println("Determining coin flip.");
        System.out.println("Checking coin flip: ");
        boolean flipValue = coinFlip();
        if(!flipValue)
        {
            JOptionPane.showMessageDialog(null, "Opponent moves first.");
            setOpponentMovement();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Player moves first.");
        }


        /*
        Board layout:
        0 | 1 | 2
        ---------
        3 | 4 | 5
        ---------
        6 | 7 | 8

        Strategy:
        Check if opponent can move on next move (Strike)
        Check if player can win on next move (Block)
        Check if middle is free
        Check if corner is free, preferably take one adjacent to player
        Choose random side
        */

        btn_Corner_1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                btn_Corner_1.setEnabled(false); //Marks the button disabled as it has been used to avoid user from double input
                btn_Corner_1.setText("X"); //Sets the button graphic to the letter of the player movement, this time "X"
                boardValues[0] = "X"; //Sets the corresponding internal board value table (used for tracking location and state checking)
                checkWinnings(boardValues);
                if(!(currentGameState != 0))
                {
                    setOpponentMovement();
                }
            }
        });
        btn_Edge_1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                btn_Edge_1.setEnabled(false);
                btn_Edge_1.setText("X");
                boardValues[1] = "X";
                checkWinnings(boardValues);
                if(!(currentGameState != 0))
                {
                    setOpponentMovement();
                }
            }
        });
        btn_Corner_2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                btn_Corner_2.setEnabled(false);
                btn_Corner_2.setText("X");
                boardValues[2] = "X";
                checkWinnings(boardValues);
                if(!(currentGameState != 0))
                {
                    setOpponentMovement();
                }
            }
        });
        btn_Edge_4.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                btn_Edge_4.setEnabled(false);
                btn_Edge_4.setText("X");
                boardValues[3] = "X";
                checkWinnings(boardValues);
                if(!(currentGameState != 0))
                {
                    setOpponentMovement();
                }            }
        });
        btn_Center.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                btn_Center.setEnabled(false);
                btn_Center.setText("X");
                boardValues[4] = "X";
                checkWinnings(boardValues);
                if(!(currentGameState != 0))
                {
                    setOpponentMovement();
                }            }
        });
        btn_Edge_2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                btn_Edge_2.setEnabled(false);
                btn_Edge_2.setText("X");
                boardValues[5] = "X";
                checkWinnings(boardValues);
                if(!(currentGameState != 0))
                {
                    setOpponentMovement();
                }            }
        });
        btn_Corner_3.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                btn_Corner_3.setEnabled(false);
                btn_Corner_3.setText("X");
                boardValues[6] = "X";
                checkWinnings(boardValues);
                if(!(currentGameState != 0))
                {
                    setOpponentMovement();
                }            }
        });
        btn_Edge_3.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                btn_Edge_3.setEnabled(false);
                btn_Edge_3.setText("X");
                boardValues[7] = "X";
                checkWinnings(boardValues);
                if(!(currentGameState != 0))
                {
                    setOpponentMovement();
                }            }
        });
        btn_Corner_4.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                btn_Corner_4.setEnabled(false);
                btn_Corner_4.setText("X");
                boardValues[8] = "X";
                checkWinnings(boardValues);
                if(!(currentGameState != 0))
                {
                    setOpponentMovement();
                }            }
        });
    }

    private void setOpponentMovement()
    {
        boolean opponentTurn = true;
        opponentMovementLoop:
        while (opponentTurn)
        {
            String[] boardValuesDupe = new String[9];
            for (int i = 0; i < boardValues.length; i++)
            {
                boardValuesDupe[i] = boardValues[i];
            }

            //Step 1 - Check if comp can win next move, if so take
            String winningMove = checkWinningsDuped(boardValuesDupe, "O");
            if (!(winningMove.equals("null")))
            {
                opponentMove = Integer.parseInt(winningMove);
                boardValues[opponentMove] = "O";
                buttonStatusUpdate(opponentMove, "O");
                System.out.println("Found winning move (Stage 1) - ID: " + opponentMove);
                break opponentMovementLoop;
            }
            else
            {
            }

            //Step 2 - Check if player can win next move, if so block
            String winningMovePlayer = checkWinningsDuped(boardValuesDupe, "X");
            if (!(winningMovePlayer.equals("null")))
            {
                opponentMove = Integer.parseInt(winningMovePlayer);
                boardValues[opponentMove] = "O";
                System.out.println("Moving to block (Stage 2) - ID: " + opponentMove);
                buttonStatusUpdate(opponentMove, "O");
                break opponentMovementLoop;
            }
            else
            {
            }

            //Step 4 - Check if comp can take corner, if so take
            for (int i = 0; i < 9; i = i + 2)
            {
                if (boardValues[i].equalsIgnoreCase("null"))
                {
                    boardValues[i] = "O";
                    buttonStatusUpdate(i, "O");
                    System.out.println("Corner found, at ID: " + i);
                    break opponentMovementLoop;
                }
            }
            //Step 3 - Check if comp can take middle, if so take
            if (boardValues[4].equalsIgnoreCase("null"))
            {
                boardValues[4] = "O";
                buttonStatusUpdate(4, "O");
                System.out.println("Middle found");
                break opponentMovementLoop;
            }
            else
            {
            }

            //Step 5 - Check if comp can take edge, if so take
            for (int i = 1; i < 9; i = i + 2)
            {
                if (boardValues[i].equalsIgnoreCase("null"))
                {
                    boardValues[i] = "O";
                    buttonStatusUpdate(i, "O");
                    System.out.println("Edge found, ID: " + i);
                    break opponentMovementLoop;
                }
            }
        }
        checkWinnings(boardValues);

    }

    //Determine which side moves first
    private boolean coinFlip()
    {
        boolean playFirst = false;
        double spin;
        spin = Math.random(); //Decides who goes first by returning either 0.0 or 1.0, then weighing against 0.5
        if(spin <= .5)
        {
            playFirst = true;
        }
        else
        {
            playFirst = false;
        }
        return playFirst;
    }


    private String checkWinningsDuped(String[] boardValuesCopy, String whichPlayer)
    {
        CheckStat checkGameStatusDupe = new CheckStat();
        String winningMove = "null";
        for (int i = 0; i < 9; i++)
        {
            if (boardValuesCopy[i].equalsIgnoreCase("null"))
            {
                boardValuesCopy[i] = whichPlayer;

                int gameStateIfDuped = checkGameStatusDupe.whoWon(boardValuesCopy);
                if (whichPlayer.equals("O"))
                {
                    if (gameStateIfDuped == 2) //
                    {
                        winningMove = Integer.toString(i);
                        break;
                    }
                    else
                    {
                        winningMove = "null";
                    }
                }
                else
                {
                    if (gameStateIfDuped == 1) //
                    {
                        winningMove = Integer.toString(i);
                        break;
                    }
                    else
                    {
                        winningMove = "null";
                    }
                }
                boardValuesCopy[i] = "null";
            }
        }
        return winningMove;
    }

    private void checkWinnings(String[] boardValues)
    {
        CheckStat checkGameStatus = new CheckStat();
        int gameState = checkGameStatus.whoWon(boardValues);
        if (gameState == 1)
        {
            JOptionPane.showMessageDialog(null, "Player won!");
            currentPlayerWin++;
            text_Tally.setText(currentPlayerWin + "-" + currentCPUWin);
            currentGameState = gameState;
            disableButtons();

        }
        else if (gameState == 2)
        {
            JOptionPane.showMessageDialog(null, "CPU won.");
            currentCPUWin++;
            text_Tally.setText(currentPlayerWin + "-" + currentCPUWin);
            currentGameState = gameState;

            disableButtons();
        }
        else if (gameState == 3)
        {
            JOptionPane.showMessageDialog(null, "Game is a tie.");
            currentGameState = gameState;

            disableButtons();
        }
        else
        {
        }
    }

    private void disableButtons()
    {
        btn_Corner_1.setEnabled(false);
        btn_Edge_1.setEnabled(false);
        btn_Corner_2.setEnabled(false);
        btn_Center.setEnabled(false);
        btn_Edge_2.setEnabled(false);
        btn_Corner_3.setEnabled(false);
        btn_Edge_3.setEnabled(false);
        btn_Corner_4.setEnabled(false);
        btn_Edge_4.setEnabled(false);
    }

    private void buttonStatusUpdate(int location, String move)
    {
        switch(location)
        {
            case(0):
            {
                btn_Corner_1.setEnabled(false);
                btn_Corner_1.setText(move);
                break;
            }
            case(1):
            {
                btn_Edge_1.setEnabled(false);
                btn_Edge_1.setText(move);
                break;
            }
            case(2):
            {
                btn_Corner_2.setEnabled(false);
                btn_Corner_2.setText(move);
                break;
            }
            case(3):
            {
                btn_Edge_4.setEnabled(false);
                btn_Edge_4.setText(move);
                break;
            }
            case(4):
            {
                btn_Center.setEnabled(false);
                btn_Center.setText(move);
                break;
            }
            case(5):
            {
                btn_Edge_2.setEnabled(false);
                btn_Edge_2.setText(move);
                break;
            }
            case(6):
            {
                btn_Corner_3.setEnabled(false);
                btn_Corner_3.setText(move);
                break;
            }
            case(7):
            {
                btn_Edge_3.setEnabled(false);
                btn_Edge_3.setText(move);
                break;
            }
            case(8):
            {
                btn_Corner_4.setEnabled(false);
                btn_Corner_4.setText(move);
                break;
            }
        }
        /*
        if (location == 0)
        {

        }
        else if (location == 1)
        {
            btn_Edge_1.setEnabled(false);
            btn_Edge_1.setText(move);
        }
        else if (location == 2)
        {
            btn_Corner_2.setEnabled(false);
            btn_Corner_2.setText(move);
        }
        else if (location == 3)
        {
            btn_Edge_4.setEnabled(false);
            btn_Edge_4.setText(move);
        }
        else if (location == 4)
        {
            btn_Center.setEnabled(false);
            btn_Center.setText(move);
        }
        else if (location == 5)
        {
            btn_Edge_2.setEnabled(false);
            btn_Edge_2.setText(move);
        }
        else if (location == 6)
        {
            btn_Corner_3.setEnabled(false);
            btn_Corner_3.setText(move);
        }
        else if (location == 7)
        {
            btn_Edge_3.setEnabled(false);
            btn_Edge_3.setText(move);
        }
        else if (location == 8)
        {
            btn_Corner_4.setEnabled(false);
            btn_Corner_4.setText(move);
        }
        */
    }

    private void resetBoard()
    {
        CheckStat checkGameStatus = new CheckStat();
        int gameState = checkGameStatus.whoWon(boardValues);


        btn_Corner_1.setText("");
        btn_Edge_1.setText("");
        btn_Corner_2.setText("");
        btn_Center.setText("");
        btn_Edge_2.setText("");
        btn_Corner_3.setText("");
        btn_Edge_3.setText("");
        btn_Corner_4.setText("");
        btn_Edge_4.setText("");

        btn_Corner_1.setEnabled(true);
        btn_Edge_1.setEnabled(true);
        btn_Corner_2.setEnabled(true);
        btn_Center.setEnabled(true);
        btn_Edge_2.setEnabled(true);
        btn_Corner_3.setEnabled(true);
        btn_Edge_3.setEnabled(true);
        btn_Corner_4.setEnabled(true);
        btn_Edge_4.setEnabled(true);

        for (int i = 0; i < 9; i++)
        {
            boardValues[i] = "null";
        }
        currentGameState = 0;
        /*
        if (gameState == 1)
        if(!coinFlip())
        {
            JOptionPane.showMessageDialog(null, "Opponent moves first.");
            setOpponentMovement();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Player moves first.");
        }
        */

    }


    private void initComponents()
    {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Aloysius Arno Wiputra
        panelMain = new JPanel();
        panelGame = new JPanel();
        btn_Corner_1 = new JButton();
        btn_Edge_1 = new JButton();
        btn_Corner_2 = new JButton();
        btn_Edge_4 = new JButton();
        btn_Center = new JButton();
        btn_Edge_2 = new JButton();
        btn_Corner_3 = new JButton();
        btn_Edge_3 = new JButton();
        btn_Corner_4 = new JButton();
        panel1 = new JPanel();
        text_Tally = new JLabel();
        btn_Reset = new JButton();

        //======== panelMain ========
        {
            panelMain.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder ( 0
            , 0 ,0 , 0) ,  "" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM
            , new java. awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,
            panelMain. getBorder () ) ); panelMain. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
            ) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );

            //======== panelGame ========
            {
                panelGame.setMinimumSize(new Dimension(400, 400));
                panelGame.setLayout(new GridLayout(3, 3));

                //---- btn_Corner_1 ----
                btn_Corner_1.setFont(new Font("Segoe UI", Font.PLAIN, 100));
                panelGame.add(btn_Corner_1);

                //---- btn_Edge_1 ----
                btn_Edge_1.setFont(new Font("Segoe UI", Font.PLAIN, 100));
                panelGame.add(btn_Edge_1);

                //---- btn_Corner_2 ----
                btn_Corner_2.setFont(new Font("Segoe UI", Font.PLAIN, 100));
                panelGame.add(btn_Corner_2);

                //---- btn_Edge_4 ----
                btn_Edge_4.setFont(new Font("Segoe UI", Font.PLAIN, 100));
                panelGame.add(btn_Edge_4);

                //---- btn_Center ----
                btn_Center.setFont(new Font("Segoe UI", Font.PLAIN, 100));
                panelGame.add(btn_Center);

                //---- btn_Edge_2 ----
                btn_Edge_2.setFont(new Font("Segoe UI", Font.PLAIN, 100));
                panelGame.add(btn_Edge_2);

                //---- btn_Corner_3 ----
                btn_Corner_3.setFont(new Font("Segoe UI", Font.PLAIN, 100));
                panelGame.add(btn_Corner_3);

                //---- btn_Edge_3 ----
                btn_Edge_3.setFont(new Font("Segoe UI", Font.PLAIN, 100));
                panelGame.add(btn_Edge_3);

                //---- btn_Corner_4 ----
                btn_Corner_4.setFont(new Font("Segoe UI", Font.PLAIN, 100));
                panelGame.add(btn_Corner_4);
            }

            //======== panel1 ========
            {

                //---- text_Tally ----
                text_Tally.setText("[PH]-[PH]");
                text_Tally.setFont(new Font("Segoe UI", Font.PLAIN, 28));
                text_Tally.setHorizontalAlignment(SwingConstants.CENTER);

                //---- btn_Reset ----
                btn_Reset.setText("RESET");

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(btn_Reset, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
                        .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(text_Tally, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                );
                panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addGap(124, 124, 124)
                            .addComponent(text_Tally)
                            .addGap(115, 115, 115)
                            .addComponent(btn_Reset)
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
            }

            GroupLayout panelMainLayout = new GroupLayout(panelMain);
            panelMain.setLayout(panelMainLayout);
            panelMainLayout.setHorizontalGroup(
                panelMainLayout.createParallelGroup()
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelGame, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
            );
            panelMainLayout.setVerticalGroup(
                panelMainLayout.createParallelGroup()
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelMainLayout.createParallelGroup()
                            .addComponent(panelGame, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE)
                            .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Aloysius Arno Wiputra
    private JPanel panelMain;
    private JPanel panelGame;
    private JButton btn_Corner_1;
    private JButton btn_Edge_1;
    private JButton btn_Corner_2;
    private JButton btn_Edge_4;
    private JButton btn_Center;
    private JButton btn_Edge_2;
    private JButton btn_Corner_3;
    private JButton btn_Edge_3;
    private JButton btn_Corner_4;
    private JPanel panel1;
    private JLabel text_Tally;
    private JButton btn_Reset;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
