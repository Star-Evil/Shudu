
//在代理模式（Proxy Pattern）中，一个类代表另一个类的功能

public class judging3 implements judging1{
    private static judging judging;
    public judging3(){
        super();
        judging = new judging();
    }

    public static  boolean judge9(int[] answer) {
        before();
        return judging1.judge9();
    }

    private static void before() {
        ;
    }
}
