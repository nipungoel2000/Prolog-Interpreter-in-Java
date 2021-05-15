import java.util.*;

// TRUE is a predefined term used to represent facts as rules. i.e. functor(argument1,argument2) for example gets translated to functor(argument1, argument2) :- TRUE
public class True extends Term 
{
    public True(String functor, List<Expression> arguments)
    {
        // pass functor as True 
        super(functor,arguments);
    }
    @Override
    public Expression substitute_variable_bindings(Hashtable<Variable,Expression> bindings)
    {
        return this;
    }
    public List<Expression> query(Database database)
    {
        List<Expression> list=new ArrayList<Expression>(); 
        list.add(this);
        return list;
    }
}