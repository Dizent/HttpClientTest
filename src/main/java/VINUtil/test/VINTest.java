package VINUtil.test;

import VINUtil.VINIsuredUtil;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author: Dizent<zhentao.d @ winbaoxian.com>
 * @date :2018/1/23-15:57
 * @description :
 * @Modify By :
 */
public class VINTest {
    @Test
    public void testVin(){
        VINIsuredUtil vinIsuredUtil = new VINIsuredUtil();
        int getnum = 5;
        List<String> vinList = vinIsuredUtil.getVinList(getnum);
        System.out.println(vinList);
    }

    @Test
    public void testDate(){
        Date beginDate = new Date(118,4,14,13,00);
        Date dateNow = new Date();
        System.out.println(beginDate);
        System.out.println(dateNow);
        System.out.println(beginDate.after(dateNow));
    }
}
