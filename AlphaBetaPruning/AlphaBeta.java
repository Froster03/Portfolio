/********************************************************************
*	Main Function: AlphaBeta
*	
*	Author: Dave Frost
*	Date: Fall 2014
*
*	Description:  This Program takes in a file board state called
*					"boardState.txt", and then finds
*					optimal move for Max using Alpha-Beta Pruning
*		 
*	Functions: Main- Gets and fills the intial board state and
*						then run alpha-beta pruning on it with
*						give search depth. Then prints out the
*						board state with the optimal move.
*
*
*				fillboard- Takes in the file and then reads it to fill
*							in the intial board state. It returns the 
*							search depth.
*
*				AlphaBetaAlg- a recursive function that searches
*								all the protential board states and
*								returns the optimal board state with
*								alpha and beta values to determine if
*								other moves can be pruned
*					
*
**********************************************************************/
import java.io.*;
import java.util.Scanner;

public class AlphaBeta
{
/********************************************************************
*	Main
*
*	Description: See Program Header
*	
*	Parameters: String Args- not used
*
*	Return: none
*
**********************************************************************/
	public static void main( String args [])
	{
		TicTacToe myGame= new TicTacToe();//game board
		int searchDepth=fillBoard(myGame);//fillBoard with return the search depth
		
		if (searchDepth>0)//if filled board was a sucess
		{
		Object board[]= new Object[3];//object array to be filled by alpha beta
		board[0]=myGame;//first slot of object array is a tictactoe board
		board[1]=Integer.MIN_VALUE;//second it the alpha
		board[2]=Integer.MAX_VALUE;//third is beta value
		myGame.printBoard();//print out starting board
		myGame.printTurn();//print out starting player
		System.out.println("Search Depth: "+searchDepth);//print out requested search depth
		board=AlphaBetaAlg(board,searchDepth,"Max");//run alpha-beta pruning
		System.out.println("\n\nFinal game State:");//title of optimal move
		myGame.copy((TicTacToe)board[0]);//extract the game board from the object array
		myGame.printBoard();//print optimal game board move
		System.out.println("Alpha: "+(int)board[1]+" Beta: "+(int)board[2]);//print ending Alpha-beta Values beta wont change due to the way alpha-beta workds
		}
		else//fillboard failed program will kick out
		{
			System.out.println("Error: boardState.txt not found!");//error message
		}
	}//end of main
/********************************************************************
*	fillBoard
*
*	Description: Reads in formated file named "boardState.txt"
*	
*	Parameters: TicTacToe- Modified to get the intial game board
*
*	Return: int- the search Depth that the file request
*
**********************************************************************/	
public static int fillBoard(TicTacToe board)
{
	Scanner sc;
	int turnPlayed;
	int searchDepth;
	int row,col;
	
	try
	{
	sc= new Scanner(new File("boardState.txt"));
	String[] currentLine;
	turnPlayed= sc.nextInt();
	searchDepth= sc.nextInt();//the Search depth that the file request
	
	currentLine= sc.nextLine().split(" ");//get new line
      do//cycles trough text file and reads in moves
      	{
      		currentLine= sc.nextLine().split(" ");
      		row= Integer.parseInt(currentLine[0]);
      		col= Integer.parseInt(currentLine[1]);
      		
      		board.setSpace(row,col);//makes the requested move
      		
      	}while(sc.hasNextLine());
	}
	catch (FileNotFoundException e)//if no file
      {
      	searchDepth=-1;//make sure that we return a negative number for the function that called this function to handle
      }//end of catch for file reading
	return searchDepth;
}
/********************************************************************
*	AlphaBetaAlg
*
*	Description: Run the recursive alpha-beta pruning algorithm and
*				informs the user with it reaches a terminal state,
*				when the alpha or beta is updated, and/or if any 
*				branches of the tree gets pruned
*	
*	Parameters: Objects[]- length 3
*					  [0]- The Given Tic Tac Toe Board
*					  [1]- Int: Alpha value
*					  [2]- int: Beta Value
*				int- The Search depth
*				String- the name of the player whose turn it is	
*
*	Return: Objects[]- length 3
*				   [0]- The modified Tic Tac Toe Board
*				   [1]- int: updated Alpha value
*				   [2]- int: updated Beta Value
*
**********************************************************************/
public static Object[] AlphaBetaAlg(Object[] inBoard, int depth, String currentTurn)
	{
		
		TicTacToe board= (TicTacToe)inBoard[0];//extracted game Board
		int alpha= (int) inBoard[1];//extracted alpha value
		int	beta=  (int) inBoard[2];//extracted beta value
		int value;//utility value
		Object returningObjects[]=new Object[3];//returning object array
		
		System.out.println();
		
		/*Terminal state is if the alg reachs the search depth or
		either side is in a winning state*/
		if (depth==0 || board.isMatchOver()>0)//if terminal state
		{
			//System.out.println("");
			
			returningObjects[0]=new TicTacToe((TicTacToe)inBoard[0]);
			//^Updateing the returning object[]
			value= board.utilityValue();//getting the boardstate utility value
			returningObjects[1]=value;//updating returning object[]-alpha
			returningObjects[2]=value;//updating returning object[]-alpha
			//^ Assign to both alpha and beta for the level above to handle
			
			
		/*Print Statement to infrom user of board state********/
			System.out.print("Terminal State: ");
			if(depth==0)
			{
				System.out.println("Reached Search Depth!");
			}
			else
			{
				if(currentTurn.equals("Min"))
				{
				System.out.println("Max Won!");
				}
				else
				{
				System.out.println("Min Won!");
				}
			}
			board.printBoard();
			System.out.println("Utitity Value:"+value);
		/*****************************************************/
			
			return returningObjects;
		}//minCase
		
	/****Spawning children*************************************/
		int numOfChildren=9-board.getSize();//the number of possible moves
		int childrenLeft= 0;//sentinal variable for while loop
		int childProsistion=0;//sentinal variable for array of children
		
		TicTacToe currentBoard= new TicTacToe(board);//getting copy of given board
		TicTacToe bestBoard= new TicTacToe(board);//getting copy of given board to me changed later
		
		//System.out.println("\nNumber of children: "+numOfChildren);
		
		
		TicTacToe child[]= new TicTacToe[numOfChildren];//array of children
		
		for (int row=0;row<3;row++)//makingChildren
				{
					for(int col=0; col<3;col++)
					{
						if(currentBoard.isEmpty(row,col))//is we can play here
						{
							
							child[childProsistion]= new TicTacToe(currentBoard, row, col);//make a child with that move
							childProsistion++;//increase the prosistion in the child array
						}//end of if
					}
				
				}
	/*************************************************************/	
	
	
	/*****Max's Turn**********************************************/
	
		if (currentTurn.equals("Max"))
		{
			
			/*Continue while we have children left to check and while we havent found the best child*/	
			while((childrenLeft<child.length)&& (alpha<=beta))
			{
				/*Updating Sending object []*/
				Object childBoard[]= new Object[3];//intinaling sending object[]
				childBoard[0]= child[childrenLeft++];//setting sending board state
				childBoard[1]= alpha;//setting sending alpha value
				childBoard[2]= beta;//setting sending beta value
				/***************************/
				
				/*catcher for the object[] that is returned*/
				Object returnedBoard[]= new Object[3];
				
				TicTacToe currentChild= new TicTacToe((TicTacToe)childBoard[0]);//copy of current child
				
				/*recursive call sending object [current child board, 
				current alpha, current beta], 1 less depth level, 
				and other person turn*/
				returnedBoard=AlphaBetaAlg(childBoard,depth-1,"Min");
				
				System.out.println();
				value= (int)returnedBoard[2];//return beta will be checked against current alpha
				
				if (value > alpha)//if value is better than current alpha
				{
					
					bestBoard.copy((TicTacToe)returnedBoard[0]);//update bestBoard
					alpha=value;//update alpha
					/*inform user that alpha was updated*/
					System.out.println(currentTurn+"'s on level: "+ depth+" Can update Alpha!");
					System.out.println("Updated Alpha to:" +alpha);
					if (alpha<=beta)
					{
					/*inform user that there is no need to check rest
					 of children*/
					System.out.println("Pruning rest of children!");
					}
				}
			}
			/* After finding best child from all children update the returning object [] */
			returningObjects[0]=bestBoard;
			returningObjects[1]=alpha;
			returningObjects[2]=beta;
			System.out.println(currentTurn+"'s on level: "+ depth+"\nAlpha: "+alpha+ " Beta: "+beta);//printing current alpha/beta values
			return returningObjects;
		}//end of Max
/*******************************************************************/



/********Min's Turn************************************************/
		else
		{
			
			/*Continue while we have children left to check and while we havent found the best child*/
			while( (childrenLeft<child.length) && (alpha<=beta))
			{
				
				Object childBoard[]= new Object[3];//sending object[]
				childBoard[0]= child[childrenLeft++];//setting next child board to be sent
				childBoard[1]= alpha;//setting current alpha value to be sent
				childBoard[2]= beta;//setting current beta value to be sent
				Object returnedBoard[]= new Object[3];//object[] to catch the returning object []

				/*recursive call sending object [current child board, 
				current alpha, current beta], 1 less depth level, 
				and other person turn*/				
				returnedBoard=AlphaBetaAlg(childBoard,depth-1,"Max");
				
				
				value= (int)returnedBoard[1];//Alpha from child
				
				/*if new value is better then current beta*/
				if (value< beta)
				{
					/*update the best board to be returned*/
					bestBoard.copy((TicTacToe)returnedBoard[0]);
					beta=value;//update current beta
					
					/*Inform user of change*/
					System.out.println(currentTurn+"'s on level: "+ depth+" Can update Beta!");
					System.out.println("Updated Beta to:" +beta);
				}
				if (alpha<=beta)
					{
					/*inform user that we dont need to look at rest of children */
					System.out.println("Pruning rest of children");
					}
			}
			/*Updating the object[] to[a copy of the best child found, current alpha, current beta]*/
			returningObjects[0]=bestBoard;
			returningObjects[1]=alpha;
			returningObjects[2]=beta;
			/* Informing the user of the current alpha/beta */
			System.out.println(currentTurn+"'s on level: "+ depth+"\nAlpha: "+alpha+ " Beta: "+beta);
			return returningObjects;
		}
	
	}


	
}//end of Program	
