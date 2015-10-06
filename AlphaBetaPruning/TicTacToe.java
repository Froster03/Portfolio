/********************************************************************
*	TicTacToe Class: 
*	
*	Author: Dave Frost
*	Date: Fall 2014
*
*	Description:  A Class to keep track of tic tac toe board and
*				modifications of the board
*
*	Class Variables:
*					final char X_TURN='X'- the character to be set 
*											when X plays
*					final char O_TURN='O'- the character to be set 
*											when O plays
*					char curTurn- keep track of whose turn it is
*
*					char[][] board- the actual board
*
*					int size- current number of moves played
*
*					final int MAX_SIZE- the max number of move that
*									can be play before game is over
*		 
*	Functions: Constructor:
*				TicTacToe()-Empty board- X Goes First
*				TicTacToe(TicTacToe)- a copy of the given board
*				TicTacToe(TicTacToe,int,int)-copy of the given board
*										with a single move made
*			   Acessors:
*				getSpace(int, int)-return the character of the given
*								coordinate
*				getSize()- return the current size
*
*				getCurTurn()- returns the character of the current
*						turn's player
*
*			   Mutators:
*				setSpace(int int)-Set the curTurn in coordinate given
*							then flips turn(basically make play)
*
*			   Printers:
*				printBoard()-print the current board in a formated 
*							style
*				printTurn()-prints who turn it is
*				
*			  Misc:
*				resetBoard()-rests all spaces to ' ', size to 0,
*					and curTurn to X_Turn.
*
*				isMatchOver()- return 0 if false,1 if someone won,
*								and 2 tied
*
*				checkWinner()- Checks each win condition, and returns
*							true if someone won, false if noone has
*
*				copy(TicTacToe)- copies given board, and variables over
*							from the sent instance of TicTacToe
*
*				isEmpty(int,int)- true if given coordinate is blank
*								  False- is coordinate is filled
*
*				flipTurn()-Set curTurn to the opposing player
*
*				UtilityValue()- returns the Utility value of the 
*					current state of the board[][] by talling how
*					many ways X can win minus the number of ways
*					O can win. If one is in a winning state will
*					return +10 for X win, -10 for O win, and 
*					0 for tie.	
*					
*
**********************************************************************/


import java.io.*;

public class TicTacToe
	{
			private final char X_TURN='X';
			private final char O_TURN='O';
			private char curTurn;
			private char board[][]=new char [3][3];;
			private int size=0;
			private final int MAX_SIZE=9;
/********************************************************************
*	TicTacToe()
*
*	Description: See Program Header
*	
*	Parameters: None
*
*	Return: none
*
**********************************************************************/
public TicTacToe()
{
	
	curTurn=X_TURN;
	resetBoard();
	
}

/********************************************************************
*	TicTacToe(TicTacToe)
*
*	Description: See Program Header
*	
*	Parameters: TicTacToe- board to be copied from
*
*	Return: none
*
**********************************************************************/
public TicTacToe(TicTacToe inBoard)
{
	this.copy(inBoard);
}


/********************************************************************
*	TicTacToe(TicTacToe,int,int)
*
*	Description: See Program Header
*	
*	Parameters: TicTacToe- board to be copied from
*				int- the row of what space a move should be played
*				int- the col of what space a move should be played
*
*	Return: none
*
**********************************************************************/
public TicTacToe(TicTacToe inBoard, int row, int col)
{
	this.copy(inBoard);
	this.setSpace(row,col);
}

/********************************************************************
*	reSetBoard()
*
*	Description: See Program Header
*	
*	Parameters: None
*
*	Return: none
*
**********************************************************************/
public void resetBoard()
		{
		/* An embeded for loop that cycle through the 
			board and sets each space to ' '*/
			for (int row=0; row<3; row++)
			{
				for ( int col=0; col<3; col++)
				{
				board[row][col]=' ';
				}
			}
				
			curTurn= X_TURN;// reset board so x goes first
			size=0;// set to 0 becuase the board is empty
		}
		
/********************************************************************
*	printBoard()
*
*	Description: See Program Header
*	
*	Parameters: None
*
*	Return: none
*
**********************************************************************/
public void printBoard()
		{
			char space;
			//A changing variable that is used in print statement
			
			
			//For loops to cycle trough board
			for (int row=0; row<3; row++)
			{
				for ( int col=0; col<3; col++)
				{
				space='_';//reset to '_'
				if (board[row][col]!=' ')//if board space isn't empty
					space= board[row][col];
					//set space to whatever occupy it
					
				System.out.print(space+"|");
				}
				System.out.println(row);// side key
			}
			System.out.print("0 1 2\n");// bottom key
		
		
		}

/********************************************************************
*	printTurn()
*
*	Description: See Program Header
*	
*	Parameters: None
*
*	Return: none
*
**********************************************************************/
public void printTurn() 
	{
		if (curTurn==X_TURN)
		{
		System.out.println("It is Max's Turn");// return the char of whatever cur_turn is set to
		}
		else
		{
		System.out.println("It is Min's Turn");
		}
	}

/********************************************************************
*	isMatchOver()
*
*	Description: See Program Header
*	
*	Parameters: None
*
*	Return: Int- 2 for tie, 1 for winner, 0 for still going
*
**********************************************************************/	
public int isMatchOver()
	{
	if (size==MAX_SIZE)// check for tie game
		{
		return 2;
		}
	if (checkWinner())// check board for valid winner
		{
		return 1;
		}
	return 0;// if neither tie or winner it isn't over
	}

/********************************************************************
*	checkWinner()
*
*	Description: See Program Header
*	
*	Parameters: None
*
*	Return: boolean- True if someone won, false if noone has
*
**********************************************************************/
public boolean checkWinner()
	{
	// Checks to see if the stating winning posistion is filled
	// If it is fill it will checks all the lines it could
	// win on to see if all the symbol are the same
	if (board[0][0]!=' ')//top left
	{
		if (board[0][0]==board[0][1]&& board[0][0]==board[0][2])//horizontal
			{
				return true;
			}
		if (board[0][0]==board[1][0] && board[0][0]==board[2][0])//vertical
			{
				return true;
			}
		if (board[0][0]==board[1][1] && board[0][0]==board[2][2])// diagonal
			{
				return true;
			}
	}
	
	if (board[0][1]!=' ')//top middle
	{
		if (board[0][1]==board[1][1]&& board[0][1]==board[2][1])//vertical
			{
			return true;
			}
	}
	
	if (board[0][2]!=' ')// top right
	{
		if (board[0][2]==board[1][1]&& board[0][2]==board[2][0])//diagonal
			{
			return true;
			}
		if (board[0][2]==board[1][2]&& board[0][2]==board[2][2])//vertical
			{
			return true;
			}

			
	}
	
	if (board[1][0]!=' ')// middle left
	{
		if (board[1][0]==board[1][1] && board[1][0]==board[1][2])//horizontal
			{
				return true;
			}
	
	}
	
	if (board[2][0]!=' ')//lower left
	{
		if (board[2][0]==board[2][1]&& board[2][0]==board[2][2])//horizontal
			{
			if (board[2][0]!=' ')
				return true;
			}
	
	}
			
		return false;
	}
	
/********************************************************************
*	copy()
*
*	Description: See Program Header
*	
*	Parameters: TicTacToe- the Instance in which will be copying into
*					this instance
*
*	Return: none
*
**********************************************************************/
public void copy(TicTacToe thatBoard)
{
	
	this.curTurn=thatBoard.getCurTurn();
	this.size=thatBoard.getSize();
	
	for(int row=0;row<3;row++)
	{
		for(int col=0; col<3; col++)
		{
		this.board[row][col]=thatBoard.getSpace(row,col);
		}
	
	}
}

/********************************************************************
*	getSpace(int, int )
*
*	Description: See Program Header
*	
*	Parameters: int- row coordinate
*				int- col corrdinate
*
*	Return: char- character that is the given coordinate 
*
**********************************************************************/
public char getSpace(int row, int col)
{
	return board[row][col];
}

/********************************************************************
*	getSize()
*
*	Description: See Program Header
*	
*	Parameters: None
*
*	Return: int- Current size
*
**********************************************************************/
public int getSize()
{
	return size;
}

/********************************************************************
*	getCurTurn()
*
*	Description: See Program Header
*	
*	Parameters: None
*
*	Return: Char- the character of whose turn to play.
*
**********************************************************************/
public char getCurTurn()
{
	return curTurn;
}

/********************************************************************
*	setSpace()
*
*	Description: See Program Header- 
*					Makes move, increase size and flips turn
*	
*	Parameters: int-row coordinate of where the player is going to play
*				int-col coordinate of where the player is going to play
*
*	Return: none
*
**********************************************************************/
public void setSpace(int row, int col)
	{
		
		board[row][col]=curTurn;// set space
		size++;// increment size
		flipTurn();
	}

/********************************************************************
*	isEmpty()
*
*	Description: See Program Header
*	
*	Parameters: int-row coordinate of where you want to check
*				int-col coordinate of where you want to check
*
*	Return: boolean- true if space is blank, false if space is filled.
*
**********************************************************************/
public boolean isEmpty(int row, int col)
{
	return (board[row][col]==' ');
}

/********************************************************************
*	flipTurn()
*
*	Description: See Program Header- 
*	
*	Parameters: None
*
*	Return: none
*
**********************************************************************/
public void flipTurn()
	{
		if (curTurn==X_TURN)// if it x's turn
			curTurn=O_TURN;// set turn to o
		else
			curTurn= X_TURN;// if not set it to x
	}

/********************************************************************
*	utilityValue()
*
*	Description: See Program Header
*	
*	Parameters: None
*
*	Return: int- the current utility value
*
**********************************************************************/
public int utilityValue()
{
	char OWinboard[][]= new char [3][3];//board for blank spaces to be filled with O's
	char XWinboard[][]= new char [3][3];//board for blank spaces to be filled with X's
	int currentUtility=0;//starting utility value
	//^ will incerment for X's win, Decrement for O's wins
	if (isMatchOver()==2)//if tie
	{
		currentUtility=0;
	}
	else
		{
	
		if (isMatchOver()==1)//if winner
			{
				if( curTurn=='X')//O wins
				{
					currentUtility=-10;
				}
				else//X wins
				{
					currentUtility=10;

				}
			}
		else
		{
	/*Filling in blank spots with X's or O's depending on which board*/
		for (int row=0; row<3;row++)
		{
			for(int col=0; col<3;col++)
			{
				XWinboard[row][col]=board[row][col];
					if(XWinboard[row][col]==' ')
					{
						XWinboard[row][col]=X_TURN;
					}
				OWinboard[row][col]=board[row][col];
					if(OWinboard[row][col]==' ')
					{
						OWinboard[row][col]=O_TURN;
					}
			}
		}//end of double for
	/*End of filling boards***************************************/


/*Check to see how many ways O can win**************************/
	if (OWinboard[0][0]=='O')//top left
	{
		if (OWinboard[0][1]=='O'&& OWinboard[0][2]=='O')//horizontal
			{
				currentUtility--;
			}
		if (OWinboard[1][0]=='O' && OWinboard[2][0]=='O')//vertical
			{
				currentUtility--;
			}
		if (OWinboard[1][1]=='O' && OWinboard[2][2]=='O')// diagonal
			{
				currentUtility--;
			}
	}
	
	if (OWinboard[0][1]=='O')//top middle
	{
		if (OWinboard[1][1]=='O'&& OWinboard[2][1]=='O')//vertical
			{
			currentUtility--;
			}
	}
	
	if (OWinboard[0][2]=='O')// top right
	{
		if (OWinboard[1][1]=='O'&& OWinboard[2][0]=='O')//diagonal
			{
			currentUtility--;
			}
		if (OWinboard[1][2]=='O'&& OWinboard[2][2]=='O')//vertical
			{
			currentUtility--;
			}

			
	}
	
	if (OWinboard[1][0]=='O')// middle left
	{
		if (OWinboard[1][1]=='O' && OWinboard[1][2]=='O')//horizontal
			{
				currentUtility--;
			}
	
	}
	
	if (OWinboard[2][0]=='O')//lower left
	{
		if (OWinboard[2][1]=='O'&& OWinboard[2][2]=='O')//horizontal
			{
			currentUtility--;
			}
	
	}
	
	
/*Check how many ways X can Win ************************************/
	
	if (XWinboard[0][0]=='X')//top left
	{
	
		if (XWinboard[0][1]=='X'&& XWinboard[0][2]=='X')//horizontal
			{
				currentUtility++;
				
				
			}
		if (XWinboard[1][0]=='X' && XWinboard[2][0]=='X')//vertical
			{
				currentUtility++;
				
			}
		if (XWinboard[1][1]=='X' && XWinboard[2][2]=='X')// diagonal
			{
				currentUtility++;
				
			}
	}
	
	if (XWinboard[0][1]=='X')//top middle
	{
	
		if (XWinboard[1][1]=='X'&& XWinboard[2][1]=='X')//vertical
			{
			currentUtility++;
			
			}
			
	}
	
	if (XWinboard[0][2]=='X')// top right
	{
		if (XWinboard[1][1]=='X'&& XWinboard[2][0]=='X')//diagonal
			{
			currentUtility++;
			
			}
		if (XWinboard[1][2]=='X'&& XWinboard[2][2]=='X')//vertical
			{
			currentUtility++;
			
			}

			
	}
	
	if (XWinboard[1][0]=='X')// middle left
	{
		if (XWinboard[1][1]=='X' && XWinboard[1][2]=='X')//horizontal
			{
				currentUtility++;
				
			}
	
	}
	
	if (XWinboard[2][0]=='X')//lower left
	{
		if (XWinboard[2][1]=='X'&& XWinboard[2][2]=='X')//horizontal
			{
			currentUtility++;
			
			}
	
	}	
/*end of checking x's wins*******************************************/
	
	}//end of else
	}//end of tie else
		return currentUtility;

	}

}	
