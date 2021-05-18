//Import other classes - Variable
import java.util.*;
public class Variable implements Expression
{
    String var;
    // Bindings list
    public Variable(String var)
    {
        this.var=var;
    }
    @Override
    public String toString()
    {
        return this.var; 
        
    }
    @Override
    public Expression get_substituted_binding(HashMap<String,Pair> bindings,HashSet<String> parents)
    {
        
        if(parents.contains(this.toString())==true)
            return null;
         
        if(bindings.containsKey(this.toString())==false)
            return new Variable(this.var);
        if(bindings.get(this.toString()).getFirst().size()==0)
        {
            if(bindings.get(this.toString()).getSecond().size()==0)
                return new Variable(this.var);
            else
                return bindings.get(this.toString()).getSecond().get(0);
        }

        Expression binded=bindings.get(this.toString()).getFirst().get(0);
       
        // System.out.println(binded.toString());
        if(binded.getClass()==Constant.class)
            return binded;
        
        else if(binded.getClass()==Variable.class)
        {
                  
            parents.add(this.toString());
            
            Expression indirect_binding=binded.get_substituted_binding(bindings,parents);
            if(indirect_binding==null)
                return new Variable(this.var);
            else
                return indirect_binding;
            
            
        }
        else
        {
            // System.out.println("Complex");
            
            parents.add(this.toString());
            Expression indirect_binding=binded.get_substituted_binding(bindings,parents);
            if(indirect_binding==null)
                return new Variable(this.var);
            else
                return indirect_binding;

        }
       
        
    }
    
}