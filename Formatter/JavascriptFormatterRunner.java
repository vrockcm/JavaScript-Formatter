
package Formatter;

import java.io.*;
import static java.lang.System.out;
import java.util.*;

public class JavascriptFormatterRunner 
{
    public static void main(String args[])
    {
        JavascriptFormatter ob1=new JavascriptFormatter();
        Scanner Filename=new Scanner(System.in);
        
        try{
        System.out.println("Enter the file name: ");
        String filename=Filename.nextLine();
        
        
        
        FileInputStream fstream = new FileInputStream(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;
             String f="";
             
        while ((strLine = br.readLine()) != null)   
        {

                f=f+strLine;
        }
         br.close();
         f =  f.replaceAll("\t", "");
         f= f.replaceAll("\n", "");
        
         System.out.print(ob1.format(f));
        
    }
     
        catch(IOException ex )
        {
                    System.out.println("FILE NOT FOUND");
         }
    
}
}
