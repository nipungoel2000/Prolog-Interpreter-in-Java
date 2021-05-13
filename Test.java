import java.util.*;

class Test
{   
    String name;
    public Test(String name)
    {
        this.name = name;
    }
    public String f(Integer s)
    {
        return s.toString() + "-" + this.name;
    }
    public static void main(String[] args)
    {
        Test t = new Test("Kashif");
        System.out.println(t.f(2));
    }
}