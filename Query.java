import java.util.*;

public class Query{

    String query;
    HashMap<String,Pair> map;

    public Query(String query)
    {
        this.query = query;
        this.map = new HashMap<String,Pair>();
    }
    public void solve(String query)
    {
        Integer id = query.indexOf("=");
        int n = query.length();
        if(id!=-1) //for unification
        {   
            Parser p = new Parser();
            Expression e1 = p.parse_term(query.substring(0,id));
            Expression e2 = p.parse_term(query.substring(id+1));
            // System.out.println(e1.toString());
            // System.out.println(e2.toString());
            if(unify(e1,e2)==true)
            {   
                System.out.println("true");
                // if(validateMap())
                // {
                //     System.out.println("true");
                //     printMap();
                // }
                // else
                // {
                //     System.out.println("false");
                // }
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
            if(e1.toString().equals(e2.toString()))
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
            if(e1.getClass() == Variable.class)
            {
                if(map.containsKey(e1.toString())) 
                {   
                    // System.out.println("here");
                    Pair pr = map.get(e1.toString());
                    List<Expression> le = pr.getFirst();
                    List<Variable> lv = pr.getSecond();
                    if(e2.getClass() == Variable.class)
                    {
                        pr.addSecond((Variable)e2);
                        return true;
                    }
                    else
                    {
                        int len = le.size();
                        if(len>0) //len can be atmost 1
                        {
                            return unify(le.get(0),e2);     
                        }
                        else
                        {   
                            pr.addFirst(e2);
                            return true;
                        }
                    }
                }
                else
                {      
                    List<Expression> le = new ArrayList<Expression>();
                    List<Variable> lv = new ArrayList<Variable>();
                    if(e2.getClass() == Variable.class)
                    {
                        lv.add((Variable)e2);
                    }
                    else
                    {
                        le.add(e2);
                    }
                    Pair pr= new Pair(le,lv);
                    map.put(e1.toString(),pr);
                    return true;
                }
            }
            else //e2 is variable
            {
                if(map.containsKey(e2.toString())) 
                {   
                    // System.out.println("here");
                    Pair pr = map.get(e2.toString());
                    List<Expression> le = pr.getFirst();
                    List<Variable> lv = pr.getSecond();
                    if(e1.getClass() == Variable.class)
                    {
                        pr.addSecond((Variable)e1);
                        return true;
                    }
                    else
                    {
                        int len = le.size();
                        if(len>0) //len can be atmost 1
                        {
                            return unify(le.get(0),e1);     
                        }
                        else
                        {   
                            pr.addFirst(e1);
                            return true;
                        }
                    }
                }
                else
                {      
                    List<Expression> le = new ArrayList<Expression>();
                    List<Variable> lv = new ArrayList<Variable>();
                    if(e1.getClass() == Variable.class)
                    {
                        lv.add((Variable)e1);
                    }
                    else
                    {
                        le.add(e1);
                    }
                    Pair pr= new Pair(le,lv);
                    map.put(e2.toString(),pr);
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
        {
            return false;
        }
    }
    // public boolean validateMap()
    // {

    // }
    // public void printMap()
    // {
    //     for(Map.Entry<String,Expression> e: map.entrySet())
    //     {
    //         System.out.println(e.getKey()+" : "+e.getValue());
    //     }
    // }
    public static void main(String[] args)
    {   
        String test = "g(X,Y,Z,h(a,b))=g(A,B,C,h(a,a))";
        // String test = "h(X,Y,g(X,Y)) = h(a,a,g(Y,X))";
        // String test = "friend(friend(X,Z),friend(Y,Z),X,Y,nipun,vinayak,friend(A,friend(B,friend(C,D)))).";
        Query q = new Query(test);
        q.solve(test);   
    }

}