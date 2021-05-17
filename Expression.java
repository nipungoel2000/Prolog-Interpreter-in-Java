//Import other classes - Variable

import java.util.*;
interface Expression
{
    public Expression get_substituted_binding(HashMap<String,Pair> bindings,HashSet<String> parents);
}