import java.util.*;
//Import other classes - Variable

interface Expression
{

}
// Term can be atom or compund terms.
public class Term implements Expression{

    String functor;
    List<Expression> arguments;
    public Term(String functor, List<Expression> arguments)
    {
        this.functor = functor;
        this.arguments = arguments;
    }

    // public match_variable_bindings(Term new_term)
    // {

    // }
}