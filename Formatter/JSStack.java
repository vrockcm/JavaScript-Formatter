/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formatter;
import java.util.*;

/**
 *
 * @author vrock
 */

public class JSStack 
{
    Stack<BlockType> stak;
    public JSStack()
    {
       stak=new Stack<BlockType>();

    }
    public void push(BlockType b)
    {
        stak.push(b);
    }
    public BlockType pop() throws EmptyStackException 
    {
        if(stak.isEmpty())
        {
            throw new EmptyStackException("Stack is Empty");
        }
        BlockType temp=stak.lastElement();
        stak.pop();
        return temp;
    }
    public BlockType peek() throws EmptyStackException
    {
         if(stak.isEmpty())
        {
            throw new EmptyStackException("Stack is Empty");
        }
        
        return stak.peek();
        
    }
    public void removeEverything()
    {
        stak.clear();
    }
    public boolean isEmpty()
    {
        if(stak.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
