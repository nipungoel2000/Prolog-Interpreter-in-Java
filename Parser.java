import java.util.*;

public class Parser 
{
    public Rule parse_rule(String input)
    {
        Expression head=null;
        Integer id1=input.indexOf(":-");
        Integer id2=input.indexOf(".");
        Integer type=0;
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
                    String b="";
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

    public Expression parse_term(String input)
    {
        
        String p=" ";  
        int n=input.length();
        for(int i=0;i<n;i++)
        {
            if(input.charAt(i)!=' ')
                p+=input.charAt(i);

        }
        input=p;      
        int g =input.indexOf('(');

        if(g==-1)
        {
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
                //Error.
            }
                
            String functor="";
            
            for(int i=0;i<g;i++)
            {
                functor+=input.charAt(i);
                
            }
            List<Expression> arg=new ArrayList<Expression>();
            String buffer="";
            for(int i=g+1;i<n;i++)
            {
                if(input.charAt(i)==',')
                {
                    arg.add(parse_term(buffer));
                    buffer="";
                }
                else{
                    buffer+=input.charAt(i);
                }
                                
            }
            arg.add(parse_term(buffer));
            return new Complex(functor,arg);
        }


            
            
    }

        
}


