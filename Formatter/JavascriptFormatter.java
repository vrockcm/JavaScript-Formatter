
package Formatter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavascriptFormatter 
{
    private JSStack stack;
    private int indentLevel=0;
  public JavascriptFormatter() 
  {
      stack=new JSStack();
  }
  public String indent(String str)
  {
      for(int k=0;k<indentLevel;k++)
      {
          str=str+"\t";
      }
      return str;
  }
  public String indentremover(String str)
  {
      for(int k=0;k<indentLevel;k++)
      {
          str = str.substring(0, str.length()-1);
      }
    return str;
  }
   public int newlinecounter(String str)
  {
      Scanner in=new Scanner(str);
       int count = 0;
        while (in.hasNextLine()) 
        {
        count++;
        in.nextLine();
      }
        return count;
         
       
  }
  public String format(String input)
  {
      
      String str="";
      int problem=0;
      int index=0;
       try{
      for(int i=0;i<input.length();i++)
      {
          char ch=input.charAt(i);
          if(ch=='{')
          {          
              indentLevel++;
              str=str+ch+"\n";
              str=indent(str);
              stack.push(BlockType.BRACE);
          }
          else if(ch=='}')
          {
                
              if( stack.isEmpty() || stack.peek()!=BlockType.BRACE )
              {       
                  problem=i;
                  str=str+ch+"\n";
                  throw new EmptyStackException("Extra Brace Found "+input.charAt(problem)+ " at line ");                             
              }
              else
              {
                   try{
                        stack.pop();                 
                      if(stack.isEmpty())
                        {         
                                str=indentremover(str);
                                indentLevel=0;
                                str=str+ch+"\n";
                        } 
                      else
                      {
                          str=indentremover(str);
                          indentLevel--;
                          str=indent(str); 
                          str=str+ch+"\n";
                          str=indent(str); 
                      }
                  }
                  catch(EmptyStackException ex)
                  {
                      System.out.println(ex.getMessage());
                      break;
                  }
              }     
          }
          else if(ch=='(')
          {
 
                 str=str+ch;
                 stack.push(BlockType.PAREN);   
                 
              if(str.indexOf("for(",index)<i && str.indexOf("for(",index)!=-1)
              {
                   index = input.indexOf("for(",index);
                   if(i-index==3)
                   {
                       stack.push(BlockType.FOR);
                       index = input.indexOf("for(",index)+1;
                   }
              }
              
          }
          else if(ch==')')
          {
              
              if(stack.peek()!=BlockType.PAREN && stack.peek()!=BlockType.FOR)
              {       
                  problem=i;
                  str=str+ch+"\n";
                  throw new EmptyStackException("EXTRA CLOSING PARENTHESIS FOUND"+input.charAt(problem)+ " at line ");
                              
              }
              else if(stack.peek()==BlockType.FOR)
              {
                  str=str+ch;
                  stack.pop();
                  stack.pop();
              }                                                                
              else
              {
                  if(!stack.isEmpty())
                  {
                      if(stack.peek()==BlockType.PAREN)
                      {
                          str=str+ch;
                          stack.pop();
                      }
                  }
                  else
                  {
                         str=str+ch+"\n";
                         stack.pop();
                  }
              }
                  
           }
            
          else if(ch==';')
          {         
              if(!stack.isEmpty())
              {
                if(stack.peek()==BlockType.PAREN)
                {
                    stack.removeEverything();
                    problem=i;
                    str=str+ch+"\n";
                   throw new EmptyStackException("Missing Parenthesis"+input.charAt(problem)+ " at line ");
                }      
                else if(stack.peek()==BlockType.FOR)
                {
                    str=str+ch;
                 }
                 else      
                 {        
                    str=str+ch+"\n";
                    str=indent(str); 
                   
                 }
               }
                else
               {
                   str=str+ch+"\n";
               }
          }
          else
            {
                str=str+ch;
            }
      }    
      if(!stack.isEmpty())
      {
          if(stack.peek()==BlockType.BRACE)
          {
            return str+"\n"+" Missing Closing Brace"+input.charAt(problem)+ " at the end of the program";
           }
          else if(stack.peek()==BlockType.PAREN)
          {
              return str+"\n"+"Missing Closing Parenthesis"+input.charAt(problem)+ " at the end of the program";
          }
      }
    }
  catch(EmptyStackException ex)
  {
        int line=newlinecounter(str);
        return str+"\n"+ex.getMessage()+line;
          
   }
       
  return str;
 

  }
}
