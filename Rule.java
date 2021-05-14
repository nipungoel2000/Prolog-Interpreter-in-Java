
public class Rule{
    
    // Rules are used to define relationships between facts and other rules.They
    // allow us to make conditional statements about our world.
    Expression head;
    Expression tail;
    public Rule(Expression head, Expression tail)
    {
        this.head = head;
        this.tail = tail;
    }
    @Override
    public String toString()
    {
        return this.head.toString() + " :- " + this.tail.toString();
    }   
    
}