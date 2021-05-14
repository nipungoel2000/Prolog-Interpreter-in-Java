
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
        
        return 
    }   

    
    
    // public query(Database database)
    // {






    // }






}