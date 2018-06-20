package VINUtil;

import com.alibaba.fastjson.JSONArray;

/**
 * @author: Dizent<zhentao.d @ winbaoxian.com>
 * @date :2018/1/23-15:03
 * @description :
 * @Modify By :
 */
public class VINCaleUtil {


    private  static final int GD_OK  = 1;
    private  static final int GD_ERR =  -1;

    public static int VinCalc(char Vin[])
    {
        int reslt;
         int check = 0;
         char temp;
         int tempValue = 0;
         int j = 0;
        Object[][] jianquan = new Object[][]{
                {'A', 1}, {'B', 2}, {'C', 3}, {'D', 4}, {'E', 5}, {'F', 6},
                {'G', 7}, {'H', 8}, {'I', 0}, {'J', 1}, {'K', 2}, {'L', 3},
                {'M', 4}, {'N', 5}, {'O', 0}, {'P', 7}, {'Q', 8}, {'R', 9},
                {'S', 2}, {'T', 3}, {'U', 4}, {'V', 5}, {'W', 6}, {'X', 7},
                {'Y', 8}, {'Z', 9}};
        int[]  pos = new int[]{8,7,6,5,4,3,2,10,0,9,8,7,6,5,4,3,2};
        for(int i = 0;i<17;i++)
        {

            if(Vin[i]>='a'&&Vin[i]<='z')
            {
                temp = (char) (Vin[i]-32);
            }
            else if((Vin[i]>='A')&&(Vin[i]<='Z'))
            {
                temp = Vin[i];
            }
            else if((Vin[i]>='0')&&(Vin[i]<='9')){
                tempValue = Integer.parseInt(String.valueOf(Vin[i]));
                temp = Vin[i];
            }
            else{
                return GD_ERR;
            }
            if((temp>='A')&&(temp<='Z')){
                for(j=0;j<26;j++)
                {
                    if(temp == jianquan[j][0]){
                        tempValue = (int) jianquan[j][1];
                    }
                }
            }
            check += tempValue*pos[i];
            System.out.println(check+"+"+tempValue+"*"+pos[i]);
        }
        reslt = check%11;
        return GD_OK;
    }
    public static void main(String args[])
    {
        String Vin="JUYMH11929730MEXX";
        VinCalc(Vin.toCharArray());
    }

}
