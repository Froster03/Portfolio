/********************************************************************
*	Class: PriorityQueue
*
*	Author: Dave Frost
*	Date: Fall 2014
*
*	Description: This is a min Heap that self sorts based on the cost
*					a vertex
*	Functions: Constructor- intialize the length of the Heap and 
*							current size of the Heap
*				Print- Self prints out the entire Heap
*				Insert- makes a new vertex and resorts the Heap based
*						on cost
*				Delete- Removes the top of the Heap and then resorts
*						itself then return the value removed
*				UpdateCost- a Mutator: reassigns the current cost of
*							a vertex and then resorts the Heap
*				Heapify- Resorts the entire heap based on cost
*				isEmpty- returns a boolean to tell if the Heap 
*							is empty or not			
*
**********************************************************************/

import java.util.Random;

public class PriorityQueue
  {
  private int size;//current size
  private int maxSize;//max number of elements
  private float ThisHeap[][];//heap vertex, cost
  private boolean sorted;//sort check
/********************************************************************
*	Constructor
*
*	Description: Set the max size of the Heap and intialize all the 
*				nodes in the Heap to have -1 as cost and name of vertex
*				Also Initalizes size, max size and sorted.
*	
*	Parameters: int- The Max number of elements the Heap may have
*
*	Return: none
*
**********************************************************************/
  public PriorityQueue(int inSize)//constructor
  {
  	 ThisHeap=new float [inSize+1][2];
  	 for (int i=1; i<inSize; i++)
  	 	{
  	 		ThisHeap[i][0]=-1;
  	 		ThisHeap[i][1]=-1;
  	 	}
 	 size=0;
 	 maxSize=inSize;
 	 sorted=false;
  }
  
/********************************************************************
*	Print
*
*	Description: Cycle through the Heap and printing out the order
*				of the proirity Heap
*	
*	Parameters: None
*
*	Return: none
*
**********************************************************************/
  public void Print()//Print
  {
   if (size>=1)//if not empty
   {
  	for(int i=1; i<=size;i++)//cycle trough the current elements
  	{
  		System.out.print(((int)ThisHeap[i][0])+"  ");
  			if (i%10==0)
  				System.out.println("");
  	}
   }
   else// if empty
   {
   System.out.print("Empty");
   }
  	System.out.println("");//new Line
  	
  }
 
  
/********************************************************************
*	Insert
*
*	Description: Adds and new element to the heap and then resorts
*				the heap
*	
*	Parameters: int- Vertex Number
*				Float- Cost to visit Vertex
*
*	Return: none
*
**********************************************************************/

public void Insert(int vertex, float cost)
  {
    size++;//increase size
    ThisHeap[size][0]=vertex;// insert New vertex
    ThisHeap[size][1]=cost;// insert new cost

	int k=size;//set to last element prosistion
	float vCost=ThisHeap[size][1];
	int vVertex= (int)ThisHeap[size][0];//set to last element Value
	
	boolean heap=false;//heap check
	int j=k;//Tracking variable
	
	while(!(heap) && j>=1)//continues till element is in the correct prosistion in the heap
		{
  			j=k/2;// look at parent
  				
  			if (vCost>ThisHeap[j][1])//Check to see if it is a heap already
  				{
  				heap=true;
				}
  			else// if not place parent in child prosistion
  				{
  				ThisHeap[k][0]=ThisHeap[j][0];
  				ThisHeap[k][1]=ThisHeap[j][1];
  				k=j;//look at the next parent
  				}
  			
  		}
  		if(k<1)//if at root
  			k=1;
  		
  		ThisHeap[k][0]=vVertex;
  		ThisHeap[k][1]=vCost;//Once you found the prosistion set values there
  }
  
/********************************************************************
*	Delete
*
*	Description: Removes and returns the top of the heap 
*					and then resorts
*	
*	Parameters: None
*
*	Return: Int- the "number" of the Vertex that was removed
*
**********************************************************************/
public int Delete()//return top data
 {
    int root= (int)ThisHeap[1][0];//set return value

    /*Swaps first element with last*/
    ThisHeap[1][1]=ThisHeap[size][1];
    ThisHeap[1][0]=ThisHeap[size][0];
    ThisHeap[size][0]=-1;
    ThisHeap[size][1]=-1;
    
    
    
    size--;//"removes" the largest element from the heap by decrement size
		
	/*Top down heapifying of the root*/
		float valueCost=ThisHeap[1][1];
		float valueVertex=ThisHeap[1][0];
		int k=1;
		int j=k;
  		boolean heap=false;
  		
  		while(!(heap) && 2*k<=size)//continues till it finds the proper prosistion
  		{
  			j=2*k;//looks at next child
  			
  			if(j<size)// if it has a second child
  			{
  				if(ThisHeap[j][1]>ThisHeap[j+1][1])//find largest of children
  					{
  					j++;
  					}
  			}
  			
  			if (valueCost<=ThisHeap[j][1])//check to see if it already a heap
  				{
  				heap=true;
  				}
  			else//if not set largest child value at the parent
  				{
  				ThisHeap[k][0]=ThisHeap[j][0];
  				ThisHeap[k][1]=ThisHeap[j][1];
  				k=j;//set the next prosistion to look at as the child
  				}
  				
  			
  		}
  	
  	
  	ThisHeap[k][1]=valueCost;//set values in the proper prosistion
    ThisHeap[k][0]=valueVertex;
  	return root;// return the value of the "removed" element
  }
  
/********************************************************************
*	Mutator: UpdateCost
*
*	Description: Searches for given vertex and updates it cost,
*					then resorts
*	
*	Parameters: int- Name of the vertex to update
*				Float- New cost of the given vertex
*
*	Return: none
*
**********************************************************************/

public void UpdateCost(float vertex, float newCost)//returns the size of the current heap
{
	int i=1;
	boolean foundIt=false;
	do//Cycle through current Vertes
	{
  		if(ThisHeap[i][0]==vertex)
  		{
  			foundIt=true;
  		}
  		i++;
  	}while((i<size)&&!(foundIt));
  	
  	ThisHeap[i-1][1]=newCost;// update cost
  	Heapify();//resort
}
/********************************************************************
*	Heapify
*
*	Description: Resort the entire min heap based on cost, to make it
*				a min Heap
*	
*	Parameters: None
*
*	Return: none
*
**********************************************************************/
private void Heapify()
  {
  	int k,j;//tracking Variables
  	float vVertex, vCost;
  	boolean heap;//Heap check
  	
  	for(int i=size/2; i>0; i--)//Iterates down through all of the parents
  	{
  		k=i;
  		vVertex=ThisHeap[i][0];
  		vCost=ThisHeap[i][1];//Current element's value
  		heap=false;
  		
  		while(!(heap) && 2*k>=size)//Puts value in proper prosistion
  		{
  			j=2*k;//finds first child
  			if(j<size)//If has second child
  			{
  				if(ThisHeap[j][1]>ThisHeap[j+1][1])//finds biggest child
  					j++;
  			}
  			if (vCost<=ThisHeap[j][1])//compares to see if value is in proper prosistion
  				heap=true;
  			else//if not put bigger child in parent node
  				ThisHeap[k][0]=ThisHeap[j][0];
  				ThisHeap[k][1]=ThisHeap[j][1];
  			k=j;//look at next child
  		}
  		ThisHeap[k][0]=vVertex;
  		ThisHeap[k][1]=vCost;//After finding proper prosistion, put Values in proper spot
  	}
  
  }
/********************************************************************
*	isEmpty
*
*	Description: return true if heap is empty, false if the heap still
*					has elements
*	
*	Parameters: None
*
*	Return: Boolean- weather or not the heap has elements left
*
**********************************************************************/
public boolean isEmpty()
{
	return (size<1);
}

  
  }
