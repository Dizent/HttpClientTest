import JSON.TkicCheck;
import JSON.TkicCheckList;
import JSON.CheckTemp;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Dizent<zhentao.d @ winbaoxian.com>
 * @date :2018/1/2-12:09
 * @description :
 * @Modify By :
 */
public class TestJSON {

    public static void main(String[] args){
        TkicCheckList tkicCheckList = new TkicCheckList();
        List<CheckTemp> checkList = new ArrayList<CheckTemp>();
        TkicCheck check = new TkicCheck("1",null);
        CheckTemp temp = new CheckTemp();
        temp.setCheck(check);
        TkicCheck check2 = new TkicCheck("2","");
        CheckTemp temp2 = new CheckTemp();
        temp2.setCheck(check2);
        checkList.add(temp);
        tkicCheckList.setCheckList(checkList);
        System.out.println(JSON.toJSONString(tkicCheckList));
    }
}
