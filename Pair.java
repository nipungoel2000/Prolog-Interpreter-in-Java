import java.util.*;

public class Pair 
{   
    List<Expression> le;
    List<Variable> lv;
    public Pair(List<Expression> le,List<Variable> lv)
    {
        this.le = le;
        this.lv = lv;
    }

    public List<Expression> getFirst()
    {
        return le;
    }

    public List<Variable> getSecond()
    {
        return lv;
    }

    public void addFirst(Expression e)
    {
        this.le.add(e);
    }

    public void addSecond(Variable v)
    {
        this.lv.add(v);
    }
}