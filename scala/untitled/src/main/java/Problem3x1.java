import dto.resultC;

/**
 * Created by han on 2016/8/9.
 */


public class Problem3x1 {

    public static void main(String[] args) {
        Hello h = new Hello();
        h.sayHello("scala");
        long l =1 ;
        for (;l<100;l++){
           resultC c= xgl.getxgl(l) ;
            System.out.println("Num="+l + " "+c.getValue()+" "+c.getList().toString());
        }

    }
}

