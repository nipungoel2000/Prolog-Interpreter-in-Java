import java.util.*;
//Import other classes - Variable

interface Expression
{
    public Expression substitute_variable_bindings( Hashtable<Variable,Expression> bindings);

}
// Term can be atom or compund terms.
public class Term implements Expression{

    String functor;
    List<Expression> arguments = new ArrayList<Expression>();
    public Term(String functor, List<Expression> arguments)
    {
        this.functor = functor;
        this.arguments = arguments;
    }

    @Override
    public Expression substitute_variable_bindings( Hashtable<Variable,Expression> bindings)
    {



        
    }

    // public match_variable_bindings(Term new_term)
    // {

    // }
}