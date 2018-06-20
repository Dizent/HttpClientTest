package String.test;
import String.StringUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Dizent<zhentao.d @ winbaoxian.com>
 * @date :2018/1/15-19:26
 * @description :
 * @Modify By :
 */
public class StringUtileTest {

    private StringUtil stringUtil;

    @Before
    public void before(){
        stringUtil = new StringUtil();
    }


    @Test
    public void testBirth(){
        String type = "01";
        String idNO = "362424199507242031";
        System.out.println(stringUtil.getBirthDayByidNo(type,idNO));
        System.out.println(stringUtil.getGenderDayByidNo(type,idNO));
    }

    @Test
    public void testRound(){
        int num = 10;
        System.out.println("结果是"+stringUtil.round(num));
        System.out.println("9结果是"+stringUtil.round(8));

    }

    @Test
    public void ecmTest(){
        String licenseNo = "京A66666";
        System.out.println(stringUtil.isEcm(licenseNo));
    }

    @Test
    public void indexTest(){
        String msg = "[从redis获取地区代码失败],[从redis获取车型信息失败]";
        String body = "";
        System.out.println(stringUtil.isIndex(msg,body));
    }

    @Test
    public void testChinese(){
        String str = "RuntimeException 平台未生成验证码！";
        System.out.println(stringUtil.isChinese(str));
    }

    @Test
    public void testContains(){
        String messageBody = "正在查询商业险报价信息，请输入验证码";
        String msg = "请输入验证码";
        System.out.println(messageBody.contains(msg));
    }

    @Test
    public void testListRemove(){
        List<String> temp = new ArrayList<>();
        List<String> temp2 = new ArrayList<>();
        for(int i = 0;i<10;i++){
            temp.add(String.valueOf(i));
        }
        for(int i = 0;i<10;i++){
            temp2.add(String.valueOf(2*i-1));
            temp2.add(String.valueOf(2*i+10));
        }
        System.out.println(temp+"-\t-"+temp2);
        int size = temp.size();
        for(int j = size-1;j>=0;j--){
            if(temp2.contains(temp.get(j))){
                temp.remove(temp.get(j));
//                size--;
            }
        }
        System.out.println(temp+"-\n-"+temp2);
    }

}
