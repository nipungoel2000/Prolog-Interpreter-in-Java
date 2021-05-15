import java.util.*;

public class Parser 
{
    public Rule parse_rule(String input)
    {
        Expression head;
        Integer id1=input.indexOf(":-");
        Integer id2=input.indexOf(".");
        Integer type;
        if(id2 == -1)
        {
            // Raise Error
        }
        else
        {
            if(id1==-1)
            {
                // fact
                String input_term=input.substring(0,id2);  
                head=this.parse_term(input_term);
                type=0;
            }
            else
            {
                // rule
                String input_term=input.substring(0,id1);  
                head=this.parse_term(input_term);
                type=1;
            }
        }
        // fact
        if(type==0)
        {
            Constant term= new Constant("TRUE");
            List<Expression> tail=new ArrayList<Expression>();
            tail.add(term);
            List<String> ops=new ArrayList<String>();
            return new Rule(head,tail,ops);
        }
        else
        {
            List<Expression> tail=new ArrayList<Expression>();
            List<String> ops=new ArrayList<String>();
            String buffer="";
            int i=id1+2;
            while(i<input.length())
            {   
                if(input.charAt(i)=='.')
                {
                    for(int j=i+1;i<input.length();j++)
                    {
                        if((input.charAt(j))!=' ')
                        {
                            // Raise Error
                        }
                    }
                    break;
                }
                else if(input.charAt(i)==',' || input.charAt(i)==';')
                {
                    Expression tail_content=this.parse_term(buffer);
                    tail.add(tail_content);
                    String b;
                    b+=input.charAt(i);
                    ops.add(b);
                    buffer="";
                    i++;

                }
                else if(input.charAt(i)!=' ')
                {
                    buffer+=input.charAt(i);
                }
                
            }
            return new Rule(head,tail,ops);
        }
    }

    public Expression parse_rule_term(String input)
    {
        int n=input.length();
        int k=-1;
        for(int i=0;i<n;i++)
        {
            if(input.charAt(i)==',' && k==-1)
            {   
                for(int j = i-1;j>=0;j--)
                {
                    
                }
                if(input.charAt(i-1)==')') //complex Term
                {

                }
            }
            
        }

        
    }
}