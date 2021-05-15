//Import other classes - Variable

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
}