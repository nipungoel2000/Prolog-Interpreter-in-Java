//Import other classes - Variable
import java.util.*;
public class Constant implements Expression
{
    String value;
    public Constant(String value)
    {
        this.value=value;
    }

    @Override
    public String toString()
    {
        return this.value;
    }

    @Override
    public Expression get_substituted_binding(HashMap<String,Pair> bindings,HashSet<String> parents)
    {   
        return new Constant(this.value);
    }
}