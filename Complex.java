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
        
        List<Expression> tail_copy=new ArrayList<Expression>();
        for(int i=0;i<this.arity;i++)
        {
            
           
            tail_copy.add(this.args.get(i));
            Expression term=tail_copy.get(i).get_substituted_binding(bindings,parents);
            if(term!=null)
            {
                tail_copy.set(i,term);
            }
           
        }
        return new Complex(this.functor,tail_copy);

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