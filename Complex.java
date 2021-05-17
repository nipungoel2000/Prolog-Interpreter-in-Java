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

    @Override
    public String toString()
    {
        String s = functor + "(";
        int n = this.args.size();
        for(int i = 0;i<n;i++)
        {
            s += (this.args.get(i)).toString();
            if(i<n-1)
                s += ",";
        }
        s += ")";
        return s;
    }

    @Override
    public Expression get_substituted_binding(HashMap<String,Pair> bindings,HashSet<String> parents)
    {
        for(int i=0;i<this.arity;i++)
        {
            // System.out.println(this.args.get(i).toString());
           
            Expression term=this.args.get(i).get_substituted_binding(bindings,parents);
            if(term!=null)
            {
                this.args.set(i,term);
            }
           
        }
        return this;

    }

    public String getFunctor()
    {
        return this.functor;
    }

    public Integer getArity()
    {
        return this.arity;
    }

    public List<Expression> getArglist()
    {
        return this.args;
    }
}