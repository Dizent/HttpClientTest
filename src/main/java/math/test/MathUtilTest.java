package math.test;

import math.MathUtil;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author: Dizent<zhentao.d @ winbaoxian.com>
 * @date :2018/1/15-13:14
 * @description :
 * @Modify By :
 */
public class MathUtilTest {

    @Test
    public void testfactorial(){
        MathUtil mathUtil = new MathUtil();
        System.out.println(mathUtil.factorial(25));
    }

    @Test
    public void testDouble2String(){
        Double a = 2d;
        System.out.println(String.valueOf(a.intValue()));
        String s = "2";
        System.out.println(((int) Double.parseDouble(s)));
    }

    @Test
    public void testLong(){
        String num = null;
        BigDecimal bigDecimal = null ;
        if(num!=null) {
            bigDecimal = new BigDecimal(num);
        }
        System.out.println(bigDecimal);
    }
}
