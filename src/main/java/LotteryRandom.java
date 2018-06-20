import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: Dizent<zhentao.d @ winbaoxian.com>
 * @date :2018/3/1-14:06
 * @description :
 * @Modify By :
 */
public class LotteryRandom {
    public static final int RED_BALL_MAX = 32;
    public static final int BLUE_BALL_MAX = 16;

    public static List<Integer> alerdyRedNum  = new ArrayList<>();

    static void getRandomRedNum(){
        Integer num = (int) (Math.random()*100%RED_BALL_MAX);
        if(alerdyRedNum.contains(num)){
            getRandomRedNum();
        }else {
            alerdyRedNum.add(num);
        }
    }

    static void getRandomBlueNum(){
        int num = (int) (Math.random()*100%BLUE_BALL_MAX);
        alerdyRedNum.add(num);
    }

    public static void main(String[] args) {
        while(alerdyRedNum.size()<6){
            getRandomRedNum();
        }
        getRandomBlueNum();
        System.out.println(alerdyRedNum);
    }
}
