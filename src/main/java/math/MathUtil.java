package math;

/**
 * @author: Dizent<zhentao.d @ winbaoxian.com>
 * @date :2018/1/15-13:14
 * @description :
 * @Modify By :
 */
public class MathUtil {

    /**
     * 阶乘
     * @param i
     * @return
     */
    public long factorial(int i){
        if(i==1){
            System.out.print(i+"=");
            return Long.valueOf(i);
        }
        else {
            System.out.print(i+"*");
            return i * factorial(i - 1);
        }
    }

}
