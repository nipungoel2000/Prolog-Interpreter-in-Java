import java.util.*;

public class Parser 
{
    public Rule parse_rule(String input)
    {
        String p="";  
        int n=input.length();
        int k=n;
        for(int i=0;i<n;i++)
        {
            if(input.charAt(i)!=' ')
                p+=input.charAt(i);
            else
            {
                k--;
            }

        }
        input=p; 
        n=k;     
        Expression head=null;
        Integer id1=input.indexOf(":-");
        Integer id2=input.indexOf(".");
        Integer type=0;
        if(id2 == -1)
        {
            throw new java.lang.Error("Syntax error");
        }
        else
        {
            if(id1==-1)
            {
                // fact
                String input_term=input.substring(0,id2);  //ALso check if id1 is the last non-empty character or not else raise an error.
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
            int op=0;
            while(i<n)
            {   
                if(input.charAt(i)=='.')
                {
                    Expression tail_content=this.parse_term(buffer);
                    tail.add(tail_content);
                    buffer="";
                    for(int j=i+1;j<n;j++)
                    {
                        if((input.charAt(j))!=' ' || input.charAt(j)=='.')
                        {
                            throw new java.lang.Error("Syntax error");
                        }
                    }
                    break;
                }
                if(input.charAt(i)=='(')
                    op++;
                if(input.charAt(i)==')')
                    op--;
                // System.out.println("buffer1"+buffer);
                if((input.charAt(i)==',' || input.charAt(i)==';') && op==0)
                {   
                    // System.out.println("buf2"+buffer);
                    Expression tail_content=this.parse_term(buffer);
                    tail.add(tail_content);
                    String b="";
                    b+=input.charAt(i);
                    ops.add(b);
                    buffer="";
                }
                else
                {
                    buffer += input.charAt(i);
                }
                i++;
            }
            return new Rule(head,tail,ops);
        }
    }
    public Expression parse_query(String input)
    {
        int n=input.length();
        if(input.charAt(n-1)!='.')
        {
            throw new java.lang.Error("Syntax error");
        }
        
        input=input.substring(0,n-1);
        return parse_term(input);
    }
    public Expression parse_term(String input)
    {
        // System.out.println("parse " +input);
        String p="";  
        int n=input.length();
        int k=n;
        for(int i=0;i<n;i++)
        {
            if(input.charAt(i)!=' ')
                p+=input.charAt(i);
            else
            {
                k--;
            }
        }
        input=p; 
        n=k;     
        int g =input.indexOf('(');

        if(g==-1)
        {
            // System.out.println(input);
            if(Character.isUpperCase(input.charAt(0)))
            {
                Variable x = new Variable(input);
                return x;
            }
            else
            {
                Constant c= new Constant(input);
                return c;
            }
        }
        else
        {
            if(input.charAt(n-1)!=')')
            {
                throw new java.lang.Error("Syntax error");
            }
                
            String functor="";
            
            for(int i=0;i<g;i++)
            {
                functor+=input.charAt(i);
                
            }
            List<Expression> arg=new ArrayList<Expression>();
            String buffer="";
            int op=0;
            for(int i=g+1;i<n-1;i++)
            {
                if(input.charAt(i)=='(')
                    op++;
                if(input.charAt(i)==')')
                    op--;
                if(input.charAt(i)==',' && op==0)
                {
                    if(buffer.equals("")==false)
                        arg.add(parse_term(buffer));
                    buffer="";
                }
                else{
                    buffer+=input.charAt(i);
                }
                                
            }
            if(buffer.equals("")==false)
            {
                arg.add(parse_term(buffer));
            }
            return new Complex(functor,arg);
        }       
    }
}


