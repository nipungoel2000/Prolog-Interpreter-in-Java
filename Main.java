import java.util.*;
import java.io.*;
public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Welcome to Kashif, Nipun and Vinayak - Prolog\nThis is free software.\nFor source code, visit https://github.com/vinayakagarwal12/Prolog-Interpreter-in-Java\n");
        System.out.print("?- ");
        String input = myObj.nextLine();
        int n=input.length();
        // if(n<12)
        // {
        //     throw new java.lang.Error("Syntax error");
        // }
        // else
        // {
        //     String pre=input.substring(0,9);
        //     String end=input.substring(n-6,n-1);
        //     System.out.println(pre);
        //     System.out.println(end);
        //     for(int i=10;i<n-3;i++)
        //     {
        //         if(s[i]==)
        //     }
        // }
        String filename=input.substring(9,n-3);
        List<Rule> rules=new ArrayList<Rule>();
        Parser p=new Parser();
        try
        {
            File file=new File(filename);
            Scanner sc=new Scanner(file);     
            while (sc.hasNextLine())
            {
                Rule current_rule = p.parse_rule(sc.nextLine());
                rules.add(current_rule);
            }
            System.out.println("true.");
            System.out.println("");
        }
        catch (FileNotFoundException ex)  
        {
            throw new java.lang.Error("File not found");
        }
        // for(Rule rule:rules)  
        // {
        //     System.out.println(rule.toString()); 
        // }
        System.out.print("?- "); 
        input = myObj.nextLine();
        while(!input.equals("Exit"))
        {
            // do something
            Query q = new Query(input,rules);
            q.solve();
            System.out.println("");
            System.out.print("?- "); 
            input = myObj.nextLine();
        }
    }
}