import java.util.*;

public class Query{

    String query;
    HashMap<String,Expression> map;

    public Query(String query)
    {
        this.query = query;
        this.map = new HashMap<String,Expression>();
    }
    public void solve(String query)
    {
        Integer id = query.indexOf("=");
        int n = query.length();
        if(id!=-1) //for unification
        {   
            Parser p = new Parser();
            Expression e1 = p.parse_term(query.substring(0,id-1));
            Expression e2 = p.parse_term(query.substring(id+1));
            // System.out.println(e1.toString());
            // System.out.println(e2.toString());
            if(unify(e1,e2)==true)
            {
                System.out.println("true");
                printMap();
            }
            else
            {
                System.out.println("false");
            }
        }
        else //for proof search
        {
            System.out.println("Implement Proof Search");
        }
    }

    public boolean unify(Expression e1, Expression e2)
    {   
        // System.out.println(e1.toString()+e1.getClass());
        // System.out.println(e2.toString()+e2.getClass());
        if(e1.getClass() == Constant.class && e2.getClass() == Constant.class) //both terms are same atom or num
        {
            if(e1.toString()==e2.toString())
            {   
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(e1.getClass() == Variable.class || e2.getClass() == Variable.class)
        {   
            // if(e1.getClass() == Variable.class && e2.getClass() == Variable.class)
            // {
            //     if(map.containsKey(e1.toString()))
            //     {
            //         Expression e = map.get(e1.toString());
            //         if(e.getClass() == Variable.class)
            //         {
            //             if(e.toString().equals(e2.toString()))
            //         }
            //     }
            // }
            if(e1.getClass() == Variable.class)
            {
                if(map.containsKey(e1.toString())) 
                {   
                    // System.out.println("here");
                    Expression e = map.get(e1.toString());
                    return unify(e,e2);
                }
                else
                {   
                    // System.out.println("here");
                    map.put(e1.toString(),e2);
                    return true;
                }
            }
            else //e2 is variable
            {
                if(map.containsKey(e2.toString())) 
                {
                    Expression e = map.get(e2.toString());
                    return unify(e,e1);
                }
                else
                {
                    map.put(e2.toString(),e1);
                    return true;
                }
            }
        }
        else if(e1.getClass() == Complex.class && e2.getClass() == Complex.class)
        {   
            Complex t1 = (Complex)e1;
            Complex t2 = (Complex)e2;
            if(t1.getFunctor().equals(t2.getFunctor())==false) //Different Functor Name
            {   
                return false;
            }
            else if(t1.getArity()!=t2.getArity()) //Different arity
            {
                return false;
            }
            else
            {
                List<Expression> l1 = t1.getArglist();
                List<Expression> l2 = t2.getArglist();
                int n = l1.size();
                for(int i=0;i<n;i++)
                {
                    boolean flag = unify(l1.get(i),l2.get(i));
                    // System.out.println("flag = "+flag);
                    if(flag==false)
                        return false;
                }
                return true;
            }
        }
        else
            return false;
    }

    public void printMap()
    {
        for(Map.Entry<String,Expression> e: map.entrySet())
        {
            System.out.println(e.getKey()+" : "+e.getValue());
        }
    }
    public static void main(String[] args)
    {
        String test = "h(X,Y,g(X,Y)) = h(a,b,g(Y,X))";
        // String test = "friend(friend(X,Z),friend(Y,Z),X,Y,nipun,vinayak,friend(A,friend(B,friend(C,D)))).";
        Query q = new Query(test);
        q.solve(test);   
    }

}