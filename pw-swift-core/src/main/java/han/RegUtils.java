package han;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by han on 2017/12/22.
 */
public class RegUtils {
    public static final String STR = "";
    private static RegUtils ourInstance = new RegUtils();

    public static RegUtils getInstance() {
        return ourInstance;
    }
    private final String Reg20="查询账号\\[ Inquirer account number \\]\\|(\\d*)\\|";
    private final Pattern  P20= Pattern.compile(Reg20);
    private final String Reg25="";
    private final Pattern  P25= Pattern.compile(Reg25);
    private final String Reg28C="";
    private final Pattern  P28C= Pattern.compile(Reg28C);
    private final String Reg60F="";
    private final Pattern  P60F= Pattern.compile(Reg60F);
    private final String Reg61="";
    private final Pattern  P61= Pattern.compile(Reg61);
    private final String Reg86="";
    private final Pattern  P86= Pattern.compile(Reg86);
    private final String Reg62F="";
    private final Pattern  P62F= Pattern.compile(Reg62F);

    private RegUtils() {

    }
    public Matcher match20(String str){
        return   P20.matcher(str);
    }
    public Matcher match25(String str){
        return   P25.matcher(str);
    }
    public Matcher match28C(String str){
        return   P28C.matcher(str);
    }
    public Matcher match60F(String str){
        return   P60F.matcher(str);
    }
    public Matcher match61(String str){
        return   P61.matcher(str);
    }
    public Matcher match86(String str){
        return   P86.matcher(str);
    }
    public Matcher match62F(String str){
        return   P62F.matcher(str);
    }
    public static void  main(String[] args){
        RegUtils reg= RegUtils.getInstance();
        Matcher m=reg.match20(STR);
        System.out.print(m.group());
    }
}
