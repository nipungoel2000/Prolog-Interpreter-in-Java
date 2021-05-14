
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

    public List<Expression> find_solutions(int argument_index,Hashtable<Variable,Expression> bindings,Database database)
    {
        List<Expression> solutions = new ArrayList<Expression>();
        
        if(argument_index>=this.arguments.size())
        {
            solutions.add(this.substitute_variable_bindings(bindings));
            return solutions;
        }
        Expression current_term=this.arguments.get(argument_index);
        
        for(Expression e : database.query(current_term.substitute_variable_bindings(bindings)))
        {
            Hashtable<Variable,Expression> combined_bindings = Database.merge_bindings(current_term.match_variable_bindings(e),bindings);

            if(combined_bindings!=null)
            {
                  for(Expression p:  find_solutions(argument_index+1,combined_bindings,database))
                  {
                      solutions.add(p);
                  }
                  return solutions;

            }

        }

    }
    public List<Expression> query(Database database)
    {
        
        Hashtable<Variable,Expression> bindings=new Hashtable<Variable,Expression>();
        return find_solutions(0,bindings,database);

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

    
    
   





    // }






}