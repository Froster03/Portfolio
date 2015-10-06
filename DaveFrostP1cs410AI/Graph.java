/*******************************************************************************
 * 
 * Graph class - Starting code for A* Assignment
 * CS 410 Fall 2014
 * Author: Aaron Boudreaux
 * Last edit: 10/26/2014
 * Description: This class represents the graph being searched by the A* algorithm
 * 
 ******************************************************************************/
//package a_star;

import java.io.*;
import java.util.Scanner;

public class Graph
	{
	private float graph[][];
  private int numRows, numCols, start, goal;

	/****************************************************************************/
	
	/* The constructor loads in a set of connections from a file and stores the 
	 * information in a matrix called graph. Please refer to the assignment 
	 * description for more info on the text file.
	 * 
	 * Parameters: none
	 * Returns: a Graph object
	 */
	public Graph()
		{
		Scanner sc;

    try
      {
      sc = new Scanner(new File("graph.txt"));
      numRows = sc.nextInt();
      numCols = sc.nextInt();
      start = sc.nextInt();
      goal = sc.nextInt();

      int numCells = numRows * numCols;			
      graph = new float[numCells][numCells];
			
			//initialize all connections to -1 (no connection)
			for(int i=0; i<numCells; i++)
				for(int j=0; j<numCells; j++)
					graph[i][j] = -1;

			int room1, room2;
			float cost;
			while(sc.hasNextInt())
				{
				room1 = sc.nextInt();
				room2 = sc.nextInt();
				cost = sc.nextFloat();
				/* The order of room1 and room2 does not matter because set the 
				 * connection were room1 is first and where room2 is first here.
				 */
				graph[room1][room2] = cost;
				graph[room2][room1] = cost;
				}//end of while
      }//end of try
    catch (FileNotFoundException e)
      {
      System.out.println("Error: graph.txt not found!");
      }//end of catch
		}//end of constructor
	
	/****************************************************************************/
	
	/* This function returns true if there is a connection between the two rooms
	 * and false if there is not. 
	 *
	 * room1: the source room
	 * room2: the destination room
	 * Returns: A boolean indicating if the rooms are connected
	 */
	public boolean isConnected(int room1, int room2)
		{
		return (graph[room1][room2] > 0);
		}//end of isConnected
	
	/****************************************************************************/
	
	/* This function returns the number of the room that is the start of the graph.
	 * 
	 * Parameters: none
	 * Returns: the index of the start square
	 */
	public int getStart()
		{
		return start;
		}//end of getStart
	
	/****************************************************************************/
	
	/* This function calculates the total number of rooms in the graph
	 * 
	 * Parameters: none
	 * Returns: the number of rooms
 	 */
	public int getNumRooms()
		{
		return numRows * numCols;
		}//end of getNumRooms
	
	/****************************************************************************/
	
	/* This function returns the number of the room that is the goal
	 * 
	 * Parameters: none
	 * Returns: the index of the goal square
	 */
	public int getGoal()
		{
		return goal;
		}//end of getGoal
	
	/****************************************************************************/
	
	/* This function takes in the index of a vertex and figures out what other
	 * vertices are connected to it (cost > 0).
	 *
	 * vertex: the room we want the connections of
	 * Returns: An Edge array containing all edges connected to this vertex
	 */
	public Edge[] getConnectedEdges(int vertex)
		{
		int numConnections = 0;
		
		//count the number of connections by looping through the vertex's row
		for(int i=0; i<getNumRooms(); i++)
			{
			if(graph[vertex][i] > 0)
				numConnections++;
			}//end of for
		
		//create Edge array of the proper length
		Edge[] connections = new Edge[numConnections];
		
		int j=0;
		//loop through the row again and create Edges if there's a connection
		for(int i=0; i<getNumRooms(); i++)
			{
			if(graph[vertex][i] > 0) //a connection exists
				{
				//System.out.println("Connection between " + vertex + " and " + i);
				//create an Edge and add it to the array
				connections[j] = new Edge(vertex, i, graph[vertex][i]);
				j++;
				}
			}//end of for
		
		return connections;
		}//end of getConnectedEdges
	
	/****************************************************************************/
	
	/* This function produces a string that is an ASCII version of the graph. If
	 * two adjacent spaces are connected, that is shown on the graph. However,
	 * diagonal connections are not marked.
	 * For example:
	 * 
	 *		|---|---|---|---|
	 *		| 0   1 | 2   3 |
	 *		|- -|- -|- -|---|
	 *		| 4   5   6   7 |
	 *		|---|- -|---|---|
	 *		| 8   9  10  11 |
	 *		|- -|- -|- -|---|
	 *		|12 |13  14  15 |
	 *		|---|---|---|---|
	 *
	 * Parameters: none
	 * Returns: A string representation of the graph
	 */
  public String toString()
    {
    int index = 0;
		String s = "";
		
    for (int i = 0; i < numCols; i++)
      {
      s += "|---"; //print top border of graph
      }
    s += "|\n";
    
    for (int i = 0; i < numRows; i++)
      {
      for (int j = 0; j < numCols; j++)
        {
        index = i * numCols + j;

        //check West
        if (j != 0) //make sure we're not on the far west
          {
          if (graph[index][index - 1] > 0)  //if there's a connection to the west room
            {
            if(index < 10)
              s += "  " + index + " ";
            else
              s += " " + index + " ";
            }
          else
            {
            if(index < 10)
              s += "| " + index + " ";
            else
              s += "|" + index + " ";
            }
          }
        else
          {
          if(index < 10)
            s += "| " + index + " ";
          else
            s += "|" + index + " ";
          }
        }//end of for j
      s += "|\n";

      for (int k = 0; k < numCols; k++)
        {
        index = i * numCols +k;
        s += "|";
        //check South
        if (i != numRows - 1) //make sure we're not on the south-most row
          {
          if (graph[index][index+numCols] > 0) //if there's a connection to the south room
            {
            s += "- -";
            }
          else
            {
            s += "---";
            }
          }//end of if
        else
          {
          s += "---";
          }
        }//end of for k
      s += "|\n";
      }//end of for i
		
		return s;
    }//end of toString
	
	/****************************************************************************/

	/* This function calculates the Manhattan Distance between two vertices.
	 *
	 * v1: The first vertex
	 * v2: The second vertex
	 * Returns: The Manhattan distance between v1 and v2
	 */
	float getManhattanDistance(int v1, int v2)
		{
		int x1 = v1 / numCols;
		int x2 = v2 / numCols;
		int y1 = v1 % numCols;
		int y2 = v2 % numCols;
		
		float result = 0;
		
		//System.out.print(v1 + " = (" + x1 + ", " + y1 + ")\t");
		//System.out.print(v2 + " = (" + x2 + ", " + y2 + ")\n");
		
		result = Math.abs(x1-x2) + Math.abs(y1-y2);
		//System.out.println("distance = " + result);
		
		return result;
		}//end of getManhattanDistance
	
	}//end of class
