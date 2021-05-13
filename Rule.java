import java.util.*;

public class Rule{
    
    """Rules are used to define relationships between facts and other rules.They
    allow us to make conditional statements about our world."""
    Term head;
    Term tail;
    public Rule(Term head, Term tail)
    {
        this.head = head;
        this.tail = tail;
    }
    public toString()
    {
        return this.head.toString() + " :- " + this.tail.toString();
    }   
    
}