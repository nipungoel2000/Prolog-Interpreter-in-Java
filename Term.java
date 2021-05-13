import java.util.*;
//Import other classes - Variable


// Term can be atom or compund terms.
public class Term{

    String functor;
    List<Term> arguments;
    public Term(String functor, List<Term> arguments)
    {
        this.functor = functor;
        this.arguments = arguments;
    }

    public match_variable_bindings(Term new_term)
    {

    }
}