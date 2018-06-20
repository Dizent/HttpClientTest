package Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Dizent<zhentao.d @ winbaoxian.com>
 * @date :2018/2/9-16:47
 * @description :
 * @Modify By :
 */
public class TestParams {

    public static void testParams(String... params){
        System.out.println(params.length);
        for(String a:params){
            System.out.println(a);
        }
    }

    public static void main(String[] args) {
        //List参数准备
        List<String> params = new ArrayList<>();
        params.add("123");
        params.add("223");
        params.add("323");
        params.add("423");
        //List转数组
        String[] paramsStr = new String[params.size()];
        for(int i=0;i<params.size();i++){
            paramsStr[i] = params.get(i);
        }
        //数组做为可变参数传入
        testParams(paramsStr);
    }
}
