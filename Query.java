import java.util.*;

public class Query{

    String query;
    HashMap<String,Pair> map;
    List<Rule> kb;
    // HashSet<HashMap<String,Pair>> allbindings;
    public Query(String query,List<Rule> kb)
    {
        this.query = query;
        this.map = new HashMap<String,Pair>();
        this.kb = kb;
        // this.allbindings = new HashSet<HashMap<String,Pair>>();
    }
    public void solve()
    {
        Integer id = this.query.indexOf("=");
        int n = this.query.length();
        Parser p = new Parser();
        if(id!=-1) //for unification
        {   
            Expression e1 = p.parse_term(this.query.substring(0,id));
            Expression e2 = p.parse_query(this.query.substring(id+1));
            // System.out.println(e1.toString());
            // System.out.println(e2.toString());
            if(unify(e1,e2)==true)
            {   
                // System.out.println("true");
                if(validateMap())
                {
                    System.out.println("true");
                    printMap();
                }
                else
                {
                    System.out.println("false");
                }
            }   
            else
            {   
                System.out.println("false");
            }
        }
        else //for proof search
        {   
            Expression e1 = p.parse_query(query);
            Expression e2 = p.parse_query(query);

            if(proofSearch(e1))
            {
                System.out.println("true.");
                HashSet<String> parents=new HashSet<String>();
                print_corr_binding(e1.get_substituted_binding(map,parents),e2);
                // System.out.println(e.get_substituted_binding(map,parents));
            }
            else
            {
                System.out.println("false");
            }
        }
    }
    public void print_corr_binding(Expression binded, Expression original)
    {
        Complex c1=(Complex)binded;
        Complex c2=(Complex)original;
        // System.out.println(c1.toString());
        // System.out.println(c2.toString());


        int n=c1.arity;
        for(int i=0;i<n;i++)
        {
            // System.out.println(c2.args.get(i).toString());
            
            if(c2.args.get(i).getClass()==Variable.class)
            {
                System.out.println(c2.args.get(i).toString()+" = " + c1.args.get(i).toString());
            }
            else if(c2.args.get(i).getClass()==Complex.class)
            {
                print_corr_binding(c1.args.get(i),c2.args.get(i));
            }
        }

    }
    
    public boolean unify(Expression e1, Expression e2)
    {   
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
                    Pair pr = map.get(e2.toString());
                    List<Expression> le = pr.getFirst();
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
    public boolean validateMap()
    {
        Set<String> visited = new HashSet<String>();
        for(Map.Entry elem : map.entrySet())
        {   
            String var = (String)elem.getKey();
            if(visited.contains(var)==false)
            {   
                Set<Expression> myset = new HashSet<Expression>(); 
                Queue<String> q = new LinkedList <>();
                q.add(var);
                while(q.peek()!=null)
                {    
                    String v = q.poll();
                    visited.add(v);
                    if(map.containsKey(v)==true)
                    {
                        Pair pr = map.get(v);
                        List<Expression> le = pr.getFirst();
                        List<Variable> lv = pr.getSecond();
                        int n = lv.size();
                        if(le.size()>0)
                        {
                            myset.add(le.get(0));
                        }
                        for(int i = 0;i<n;i++)
                        {   
                            if(visited.contains(lv.get(i).toString())==false)
                                q.add(lv.get(i).toString());
                        }
                    }
                }
                boolean flag = true;
                for(Expression e1: myset)
                {
                    for(Expression e2:myset)
                    {   
                        if(e1.toString().equals(e2.toString())==false)
                        {
                            flag = unify(e1,e2);
                            if(flag == false)
                                return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    public void printMap()
    {
        for(Map.Entry elem : map.entrySet())
        {
            String var = (String)elem.getKey();
            // System.out.println("var="+var);

            Variable p=new Variable(var);
            HashSet<String> parents=new HashSet<String>();
            // parents.add(var);
            Expression term= p.get_substituted_binding(map,parents);
            if(term==null)
                System.out.println(var + " = " +var);
            else
                System.out.println(var + " = " +term.toString());
        }
           
    }

    public boolean proofSearch(Expression e)
    {   
        int n = kb.size();
        for(int i = 0;i<n;i++)
        {   
            // System.out.println("i = "+i);
            HashMap<String,Pair> temp = new HashMap<String,Pair>();
            temp.putAll(map);
            if(unify(kb.get(i).getHead(),e)==true && validateMap())
            {   
                HashSet<String> parents = new HashSet<String>();
                // Expression expr = e.get_substituted_binding(map,parents);
                List<Expression> tail = kb.get(i).getTail();
                List<String> ops = kb.get(i).getOps();
                int sz = tail.size();
                boolean flag = true;
                for(int j = 0;j<sz;j++)
                {   
                    // System.out.println("j = " + j);
                    if(tail.get(j).getClass()==Constant.class) //fact
                    {   
                        //Add True to List<Map> or add empty map to list
                        return true;
                        // allbindings.add(map);
                    }
                    else
                    {   
                        Expression myexpr = tail.get(j).get_substituted_binding(map,parents);
                        if(proofSearch(myexpr)==false)
                        {     
                            if(j==0) flag = false;
                            else if((j>0) && ops.get(j-1).equals(","))
                            {
                                flag = false;
                            }
                        }
                        else //true
                        {   
                            if(j==0) flag = true;
                            else if((j>0) && ops.get(j-1).equals(";"))
                            {
                                flag = true;
                            }
                        }
                        if((j>0) && ops.get(j-1).equals(";") && flag == true) //query already true
                        {   
                            // allbindings.add(map);
                            return true;
                            // map = temp1;
                        }
                    }
                }
                // System.out.println("flag = " +flag);
                if(flag==true)
                {   
                    // allbindings.add(map);
                    // map = temp;
                    return true;
                }
                else if(flag==false)
                {
                    map = temp;
                }
            }
            else //restore global map
            {
                map = temp;
            }
        }
        return false;
    }

    // public static void main(String[] args)
    // {   
    //     // String test = "g(X,Y,Z,h(a,a))=g(A,B,C,h(a,a))";
    //     // String test = "h(X,Y,g(X,Y)) = h(a,b,g(Y,X))";
    //     // String test = "f(f(X),Y)=f(Z,A)";
    //     // String test = "f(f(f(X)),Y)=f(Z,A)";
    //     // String test = "f(X,Y,Z) = f(X,h(c,Z),d)";
    //     // String test = "f(a,X)=g(Y,q(a,b))";
    //     String test =  "g(a,b,c,d,a) = g(X,Y,Z,W,X).";
    //     // String test = "f(X,Y,a)= f(X,b,Z)";
    //     // String test = "friend(friend(X,Z),friend(Y,Z),X,Y,nipun,vinayak,friend(A,friend(B,friend(C,D)))).";
    //     Query q = new Query(test);
    //     q.solve(test);   
    // }

}