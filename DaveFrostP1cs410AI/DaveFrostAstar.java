/********************************************************************
*	Main Function: DaveFrostAstar
*	
*	Author: Dave Frost
*	Date: Fall 2014
*
*	Description:  This Program takes in formatted Map text file,
*					than runs the A* algorithm on it find the
*					optimal path from given start to given goal
*		 
*	Functions: Main- Loads "graph.txt" and then run the A* algorithm
*					on it and keeps track of the fronteir, SPT, and
*					cost of paths. After each step it will print
*					current static of  the tracking arrays
*					Once The algorithm determines path or not main
*					will print optimal path or inform that a path
*					wasnt found
*
*
*				Print- Print out current status of the Fronteir,
*						SPT, and Cost Arrays
*					
*
**********************************************************************/
import java.io.*;

public class DaveFrostAstar
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
		public static void main (String args [])
		{
		Graph myGraph= new Graph();//Map
		int roomCount= myGraph.getNumRooms();//Max num of Rooms
		PriorityQueue pq= new PriorityQueue(roomCount);//PriorityQueue
		int goalRoom= myGraph.getGoal();//Goal room
		Edge frontier []= new Edge[roomCount];//frontier
		Edge spt[]= new Edge[roomCount];//Shortest Path Tree
		Edge connections[];//Connected Rooms
		float costToHerelist[]= new float [roomCount];//Cost of Path
		float costToHere;//current Path Cost
		int nextClosest;//Current Room we are looking at
		Edge dest;//Current edge we are looking at
		boolean foundGoal=false;//Flag if we found goal room or not
		float newCost=0;//Determined new cost
		
		
		
		for (int i=0; i<roomCount;i++)//Initializing Arrays
		{
			frontier[i]=null;
			spt[i]=null;
			costToHerelist[i]=0;
		}
		
		System.out.println(myGraph.toString());//Print Map
		
		pq.Insert(myGraph.getStart(),0);//Enqueue start
		frontier[myGraph.getStart()]= new Edge (myGraph.getStart(),myGraph.getStart(),0);//Updat Fronteir
		costToHerelist[myGraph.getStart()]=0;//set cost
		
		while(!(pq.isEmpty())&&!(foundGoal))
		//^While we have edges to look at and we havent found goal yet
		{
			
			nextClosest= pq.Delete();//Dequeue Lowest Cost
			spt[nextClosest]= frontier[nextClosest];//update SPT
			System.out.print("\nDequeued: ");//Inform user
			System.out.println(nextClosest);
			System.out.print("Proirity Heap: ");
			pq.Print();//Print current Status of Proirity Heap
			
			if (nextClosest==goalRoom)//If we found goal
				foundGoal=true;
			else//Not found goal
			{
				connections= myGraph.getConnectedEdges(nextClosest);
				//^ get array of connected rooms
				
				for(int i=0;i<connections.length;i++)//for each room
				{
					dest= connections[i];
					
					//Have we found the shortest path
					if (spt[dest.getDest()]==null)//Havent found Shortest Path
					{

					costToHere=costToHerelist[dest.getSource()];
					newCost= costToHere+connections[i].getCost()+myGraph.getManhattanDistance(connections[i].getDest(),goalRoom);
					//^ Calculate new cost
					
					
					//Have we Looked at this room?
					if (frontier[dest.getDest()]==null)//haven't seen room
					{
						costToHerelist[dest.getDest()]=newCost;
						pq.Insert(dest.getDest(),newCost);
						frontier[dest.getDest()]=dest;
						//^ update Arrays and add edge
					}
					else//Seen this room
					{
						//does the new cost is better then current cost
						if (newCost<costToHerelist[dest.getDest()] && spt[dest.getDest()]==null)
						{
							costToHerelist[dest.getDest()]=newCost;
							pq.UpdateCost(dest.getDest(),newCost);
							frontier[dest.getDest()]=dest;
							//^ update proirity Heap and other arrays
						}
					}
					}
				}//end of for
				Print(frontier,spt,costToHerelist, roomCount);
				//^ Print Arrays
			} //end of if
		}//end of while/Algorithm
		
		/*Print Results*/
		System.out.println("\nMap:\n"+myGraph.toString());
		System.out.println("\nStart: "+ myGraph.getStart()+" Goal: " +myGraph.getGoal());	
		System.out.println("");
		
		//Did we find the goal room
		if(!(foundGoal))//Didn't find goal room
		{
			System.out.println("Goal is Blocked! Could Not Find Path!");
		}
		
		else//Found Goal room
		{
			System.out.print("Path:\t");
			String path=""+myGraph.getGoal();
			int atHere=myGraph.getGoal();
			while( atHere!= myGraph.getStart())//Form Path
			{	
				atHere=spt[atHere].getSource();
				path= atHere+", "+ path;
	
			}
		
			System.out.println(path+"\n");
		}// end of else
		/*Print Results of the Arrays*/
			Print(frontier,spt,costToHerelist, roomCount);
		
			System.out.println("\nThanks For Playing");
		
		}//exit
		
		
/********************************************************************
*	Print
*
*	Description: Cycle through arrays and Print out results
*	
*	Parameters: Edge []- The current State of the Fontir
*				Edge []- Current State of the Shortest Path Tree
*				Float []- Current State of the Cost to get to Vertex
*				int- Length of the Arrays
*
*	Return: none
*
**********************************************************************/	
		
public static void Print(Edge [] frontier, Edge [] spt, float [] costToHerelist, int roomCount)
	{
	
	/*Print Frontier*/
	System.out.println("\nFrontier:");
			for (int i=0; i<roomCount;i++)//Cycle through array
			{
				if (frontier[i]==null)//if no element exist
				{
					System.out.print((i)+": (Null)");
				}
				else//if element exist: print it
				{
				System.out.print((i)+": "+frontier[i].Print());
				}
				System.out.print("\t");//Formatting
				if((i+1)%5==0)//Rows of 5
				{
					System.out.println();//Formatting
				}
			}
			/*Print Shortest Path Tree*/
			System.out.println("\n\nSpt:");
			for (int i=0; i<roomCount;i++)//Cycle through array
			{
				if (spt[i]==null)//if no element exist
				{
					System.out.print((i)+": (null)");
				}
				else//if element exist: print it
				{
				System.out.print((i)+": "+spt[i].Print());
				}
				System.out.print("\t");
				if((i+1)%5==0)//Rows of 5
				{
					System.out.println();//Formatting
				}
				
			}
			/*Print Cost to Vertex List*/
			System.out.println("\n\nCost List:");//Cycle through array
			for (int i=0; i<roomCount;i++)
			{
				if (costToHerelist[i]<0)//if no element exist
				{
					System.out.print((i)+": (null)");
				}
				else//if element exist: print it
				{
				System.out.print((i)+": "+costToHerelist[i]);
				}
				System.out.print("\t");//Formatting
				if((i+1)%5==0)//Rows of 5
				{
					System.out.println();//Formatting
				}
			}
		System.out.println();	//Formatting	
	
	
	
	}	
	
	
	}
