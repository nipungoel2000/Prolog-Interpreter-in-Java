import java.util.*;

public class Rule 
{
    // Rules are used to define relationships between facts and other rules.They
    // allow us to make conditional statements about our world.
    Expression head;
    List<Expression> tail;
    List<String> ops;
    // also covers facts
    public Rule(Expression head,List<Expression> tail,List<String> ops)
    {
        this.head=head;
        this.tail=tail;
        this.ops=ops;
    }
    @Override
    public String toString()
    {
        
        String str="";
        str+=this.head.toString();
        str+=" :-";
        for(int i=0;i<this.tail.size()-1;i++)
        {
            str+=" ";
            str+=this.tail.get(i).toString();
            str+=" ";
            str+=this.ops.get(i);
        }
        str+=" ";
        str+=this.tail.get(tail.size()-1).toString();
        return str;
    }
    public Expression getHead()
    {
        return this.head;
    }
    public List<Expression> getTail()
    {
        return this.tail;
    }
    public List<String> getOps()
    {
        return this.ops;
    }
    public static void main(String[] args)
    {
        String test = "enemy(X,Y):-friend(X,Z),friend(Y,Z).";
        // String test = "friend(friend(X,Z),friend(Y,Z),X,Y,nipun,vinayak,friend(A,friend(B,friend(C,D)))).";
        Parser p=new Parser();
        Rule output = p.parse_rule(test);
        System.out.println(output.toString());

        
    }

    
}