/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chelsea
 */
public class DaveFrostP4 {
    public static void main(String[] args)
    {
        CodeTree root= new CodeTree('*');
        fillTree(root,4);
        root.inOrder();
    }

static void fillTree(CodeTree root, int length)
{
    root.setLeft('B');
    root.setRight('W');
    fillTree(root.getLeft(),length--);
    fillTree(root.getRight(),length--);
}
}