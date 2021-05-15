import java.util.* ;

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
            int i=id1+2;
            while(i<input.length())
            {   
                if((input.charAt(i))=='.')
                {
                    for(int j=i+1;i<input.length();j++)
                    {
                        if((input.charAt(j))!=' ')
                        {
                            // Raise Error
                        }
                    }
                }
                int id3=input.indexOf(",");
                int id4=input.indexOf(";");
                int id5=input.indexOf('.');
                Expression current_arg;
                if(id3==-1)
                {
                    if(id4==-1)
                    {
                        // Last term
                        String current_inp=input.substring(i,id5-i);
                        current_arg=parse_term(current_inp);
                        i=id5;
                    }
                    else
                    {
                        // or operator after term
                        String current_inp=input.substring(i,id4-i);
                        current_arg=parse_term(current_inp);
                        ops.add(";");
                        i=id4+1;
                    }
                }
                else
                {
                    if(id4==-1)
                    {
                        // and operator after term
                        String current_inp=input.substring(i,id3-i);
                        current_arg=parse_term(current_inp);
                        ops.add(",");
                        i=id3+1;
                    }
                    else
                    {
                        if(id3<id4)
                        {
                            // and operator after term
                            String current_inp=input.substring(i,id3-i);
                            current_arg=parse_term(current_inp);
                            ops.add(",");
                            i=id3+1;
                        }
                        else
                        {
                            // or operator after term
                            String current_inp=input.substring(i,id4-i);
                            current_arg=parse_term(current_inp);
                            ops.add(";");
                            i=id4+1;
                        }
                    }
                }
                tail.add(current_arg);
            }
            return new Rule(head,tail,ops);
        }
    }
}