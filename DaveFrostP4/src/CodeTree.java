import javax.swing.*;

public class CodeTree {
	private Node root;			//The value inside this node of the tree

	public CodeTree(char value)
        {
            root= new Node(value);
        }
        
	public void setLeft(char value)
		{
		root.left= new CodeTree(value);
		}
	
	
	public void setRight(char value)
		{
		root.right= new CodeTree(value);
		}
	
	public CodeTree getLeft()
		{
		return root.left;
		}
	
	public CodeTree getRight()
		{
		return root.right;
		}
	public char getData()
		{
		return root.data;
		}
	
	public void preOrder()
		{
		System.out.print(root.data + " ");
		
		if(root.left != null)
			root.left.preOrder();
		
		if(root.right != null)
			root.right.preOrder();
		}
        
	public void inOrder()
		{
		if(root.left != null)
			root.left.inOrder();
		
		System.out.print(root.data + " ");
		
		if(root.right != null)
			root.right.inOrder();
		}//end of inOrder
	

	public void postOrder()
		{
		if(root.left != null)
			root.left.postOrder();
		
		if(root.right != null)
			root.right.postOrder();
		
		System.out.print(root.data + " ");
		}//end of postOrder
        private class Node{
            private char data;
            private CodeTree left;	
            private CodeTree right;
            
            private Node(char Value)
            {
                data= Value;
                left=null;
                right= null;
            }
            /*
            private class Node(char Value, CodeTree leftNode, CodeTree rightNode)
            {
                data= value;
                left= leftNode;
                right= RightNode;
            }
            */
            
        }
 	
	
	}//end of class

