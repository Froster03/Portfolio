/********************************************************************
*	Edge Class
*
*	Description: Class is used to keep track of edges with 2 vertexes
*
*
*	Author: Dave Frost
*	Date: Fall 2014
*
*	Functions: Constructor: sets initial amounts to the edges
*				Acessors: one to get source, another to get Dest
*						  and another to get Cost
*				Print: Returns a toString of the edge (a,b)
*
**********************************************************************/
public class Edge
	{
	private int source;
    private int dest;
    private float cost;
/********************************************************************
*	Constructor
*
*	Description: Set  intial source, destination and cost of the new
*				edge
*	
*	Parameters: int- Source of the edge
*				int- Destination of the Edge
*				Float- Cost of the edge
*
*	Return: none
*
**********************************************************************/
    public Edge(int inSource, int inDest, float inCost)
    {
     source=inSource;
     dest=inDest;
     cost=inCost;
    }
    
/********************************************************************
*	Acessor: getSource
*
*	Description: return the int of the source of the edge 
*	
*	Parameters: None
*
*	Return: int- the value that is current at source for the is edge
*
**********************************************************************/
    public int getSource()
    {
    	return source;
    }
    
/********************************************************************
*	Acessor: getDest
*
*	Description: return the int of the Destination of the edge 
*	
*	Parameters: None
*
*	Return: int- the value that is current at Destination for the is
*					edge
*
**********************************************************************/
    public int getDest()
    {
    	return dest;
    }
    
/********************************************************************
*	Acessor: getCost
*
*	Description: return the float of the cost of the edge 
*	
*	Parameters: None
*
*	Return: Float- the value that is current the Cost for the is edge
*
**********************************************************************/
    public float getCost()
    {
    	return cost;
    }
/********************************************************************
*	toString: Print
*
*	Description: Return a formated string of the edge 
*	
*	Parameters: None
*
*	Return: String- The formatted string of the (source, Destination)
*
**********************************************************************/
    public String Print()
    {
    	return ("("+source+","+dest+")");
    }
    
}
