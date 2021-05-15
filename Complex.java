import java.util.*;
//Import other classes - Variable

public class Complex implements Expression
{
    String functor;
    List<Expression> args;
    Integer arity;
    // Bindings list
    public Complex(String functor,List<Expression> args)
    {
        this.functor=functor;
        this.args=new ArrayList<Expression>(args);
        this.arity=this.args.size();
    }
    public String toString()
    {
        String s = functor + "(";
        int n = this.args.length();
        for(int i = 0;i<n;i++)
        {
            s += (this.args.get(i)).toString();
            if(i<n-1)
                s += ",";
        }
        s += ")";
        return s;
    }
}