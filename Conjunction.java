
import java.util.*;

public class Conjunction extends Term 
{

    public Conjunction(String functor, List<Expression> arguments)
    {
        super(functor,arguments);

    }

    @Override
    public Expression substitute_variable_bindings(Hashtable<Variable,Expression> bindings)
    {
        List<Expression> arguments = new ArrayList<Expression>();
        
        for(Expression argument : this.arguments)
        {
            arguments.add(argument.substitute_variable_bindings(bindings));

        }
        return new Conjunction(this.functor,arguments);


    }

    public String toString()
    {
        String conjunction="[";
        int n=this.arguments.size();

        for(int i=0;i<n;i++)
        {
            conjunction+=this.arguments.get(i).toString();
            if(i!=n-1)
            {
                conjunction+=",";
            }
            
        }
        conjunction+="]";

        return conjunction;
    }   

    
    
    // public query(Database database)
    // {






    // }






}