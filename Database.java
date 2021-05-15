import java.util.*;

// The database object is an object which contains a list of our declared rules.It's used to query our data for items matching a goal. It also contains the helper function used to merge variable bindings.
public class Database
{
    List<Expression> rules=new ArrayList<Expression>();
    public Term(List<Expression> rules)
    {
        this.rules=rules;
    }
    public List<Expression> query(Expression goal)
    {
        // We obtain the map containing our shared rule head and goal variable
        // bindings, and process the matching results if there are any to process.
        int n=this.rules.size();
        for(int i=0;i<n;i++)
        {
            
        }
    }
}