import com.fasterxml.jackson.databind.util.BeanUtil;
import model.Student;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by <zhentao.d@winbaoxian.com> on 2018/6/4.
 */
public class ObjectCloneTest {

    @Test
    public void cloneStudent(){
        Student s1 = new Student();
        s1.setName("liming");
        s1.setAge(23);
        Student s2 = s1;
        System.out.println(s1.toString()+"\n"+s2.toString());
        s2.setName("xiaowang");
        System.out.println(s1.toString()+"\n"+s2.toString());
        Student s3  = (Student) s1.clone();
        s3.setName("cloneLiming");
        System.out.println(s1.toString()+"\n"+s2.toString()+"\n"+s3.toString());
    }

    @Test
    public void TimeTest(){
        Date date = new Date();
        Date endDate = new Date(1514736000000l);
        System.out.println(date+"\n"+endDate);
        System.out.println(endDate.before(date));
    }

    @Test
    public void testList2String(){
        List<Integer> integerList = Arrays.asList(10,20,30,40,50,60);
        System.out.println(String.valueOf(integerList));
    }

    @Test
    public void testMap(){
        ConcurrentMap<String,String> map = new ConcurrentHashMap<>();//初始化为空
        map.put("A", "123");//put值
        String valuex = map.putIfAbsent("xx", "abc");
        System.out.println(valuex);
    }
}
