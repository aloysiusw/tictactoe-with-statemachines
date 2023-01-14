# TicTacToe with State Machines

![image](https://user-images.githubusercontent.com/48269287/211882978-097386ee-0dde-47be-8878-0743887ed9ba.png)

This project was developed for CSCI 312 - Theory of Computation as an option in place of a literary report. The main idea of the project is to apply the concept of state machines to a game or program.

# The Approach

![image](https://user-images.githubusercontent.com/48269287/211877958-51ea4b06-d9ce-4305-8f5d-878ee9390c62.png)

The following diagram serves as the reference state machine. It is essentially a simple logic flow that can be simplified and outlined into 5 stages:

1) The computer checks the board if it can win by iterating through every
empty value on the board and repeatedly checking if the win state is met.
If it finds one where it does, then it moves to occupy the position. If not,
then it moves to stage 2.
 
2) If the computer cannot find a win state for itself, then it will try to repeat
the same operation but looking if the player can win instead. If it finds
one, then it will move to block it. If not, then it moves to stage 3.

3) The computer checks on any value of the corners, if it finds an empty one
then it occupies it.

4) If it cannot find an empty corner value, then it moves to occupy the center
value.

5) If it cannot find a middle spot and a corner spot, then it goes to check the
edges and moves to that. This should be the final resort of the computer
but if for some reason it reaches it and cannot find anything either, then
some error has occurred in the runtime.


# Analysis

![image](https://user-images.githubusercontent.com/48269287/211878554-cbfaeaad-e2c7-448a-be84-7d98d4721ed3.png)

According to a calculation done by Henry Bottomley, the total number of possible
games while ignoring the symmetry of the board equals to 255168, which is acquired by
adding the total ways a side can win in multiple iteration, having to start with a minimum
of 5 moves (as a game can only be won by having three consecutive entries, and the
opponent has to move twice between each intersection) to 9 moves due to the total spaces
of the board as well as the draw conditions, where neither side achieve the win state.
The game also has what is called a symmetry.

Symmetry can best be understood by realizing that the
board only has three distinct placements, and it stays
constant regardless of the orientation of the board.
These placements are: the corner pieces, the edges, and
the middle piece, as outlined on the figure on the side.
These symmetries reduce redundancies in the total
number of wins.
