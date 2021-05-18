import java.util.*;
import java.io.*;
public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Welcome to KNV - Prolog ( version 1.1.0 )\nThis is free software developed by Kashif, Nipun and Vinayak.\nFor source code, visit https://github.com/vinayakagarwal12/Prolog-Interpreter-in-Java\n");
        List<Rule> rules=new ArrayList<Rule>();
        Parser p=new Parser();
        System.out.print("?- "); 
        String input = myObj.nextLine();
        while(!input.equals("Exit"))
        {
            // solving query
            int n=input.length();
            // Knowledge base update query
            if(n>=10 && input.substring(0,8).equals("consult(") && input.substring(n-2,n).equals(")."))
            {
                String filename=input.substring(8,n-2);
                filename=filename+".pl";
                try
                {
                    File file=new File(filename);
                    Scanner sc=new Scanner(file);   
                    rules.clear();
                    while (sc.hasNextLine())
                    {
                        Rule current_rule = p.parse_rule(sc.nextLine());
                        rules.add(current_rule);
                    }
                    // for(Rule rule:rules)  
                    // {
                    //     System.out.println(rule.toString()); 
                    // }
                    System.out.println("true.");
                }
                catch (FileNotFoundException ex)  
                {
                    throw new java.lang.Error("File not found");
                }
            }
            // general query
            else
            {
                Query q = new Query(input,rules);
                q.solve();
            }
            System.out.println("");
            System.out.print("?- "); 
            input = myObj.nextLine();
        }
    }
}
