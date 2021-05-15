import java.util.*;

public class Rule
{
    // Rules are used to define relationships between facts and other rules.They
    // allow us to make conditional statements about our world.
    Expression head;
    List<Expression> tail;
    List<String> ops;
    // also covers facts
    public Rule(Expression head,List<Expression> tail,List<String> ops)
    {
        this.head=head;
        this.tail=tail;
        this.ops=ops;
    }
}