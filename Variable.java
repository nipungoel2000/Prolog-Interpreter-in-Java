import java.util.*;
// import Term.Expression;

// A variable is a type of term. Variables start with an uppercase letter and
// represent placeholders for actual terms. 
public class Variable implements Expression
{   
    String name;
    public Variable(String name){
        this.name = name;
    }

    public Hashtable<Variable,Expression> match_variable_bindings(Expression newterm)
    {
        Hashtable<Variable,Expression> bindings  = new Hashtable<Variable,Expression>();
        
        if (this.equals(newterm)==false)
        {
            bindings.put(this,newterm);
        }
        return bindings;
    }

}