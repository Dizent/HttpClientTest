package String;

import java.util.Date;

/**
 * @author: Dizent<zhentao.d @ winbaoxian.com>
 * @date :2018/1/15-19:25
 * @description :
 * @Modify By :
 */
public class StringUtil {

    public static int time = 0 ;
    public static final int maxtime = 6;

    public String getGenderDayByidNo(String identifyType, String identifyNumber) {
        String idGender;
        if(identifyType.equals("01")){
            if(identifyNumber.length()==18) {
                idGender = identifyNumber.substring(16,17);
            }else{
                idGender = identifyNumber.substring(14,15);
            }
            int g = Integer.parseInt(idGender);
            System.out.println(g);
            if(g%2==1){
                return "1";
            }else{
                return "2";
            }
        }else{
            return "1";
        }
    }

    public String getBirthDayByidNo(String identifyType, String identifyNumber) {
        String idBirth;
        if(identifyType.equals("01")){
            //18位身份证号
            if(identifyNumber.length()==18) {
                idBirth = identifyNumber.substring(6,14);
                return idBirth.substring(0,4)+"-"+idBirth.substring(4,6)+"-"+idBirth.substring(6,8);
            }else{
                //15位身份证号
                idBirth = identifyNumber.substring(6,12);
                String idYear = idBirth.substring(0,2);
                String birthYear;
                if(Integer.parseInt("20"+idYear)>new Date().getYear()){
                    birthYear = "19"+idYear;
                }else{
                    birthYear = "20"+idYear;
                }
                return birthYear+"-"+idBirth.substring(2,4)+"-"+idBirth.substring(4,6);
            }

        }else{
            return "1990-01-01";
        }
    }

    public int round(int num){
        try{
            System.out.println("num"+num);
            return num/time;
        }catch (Exception e){
            time++;
            System.out.println("time"+time);
            if(time<maxtime){
                return round(num);
            }else{
                return 999;
            }
        }finally{
            System.out.println("finallyTime"+time+"finallyNum"+num);
        }
    }

    public boolean isEcm(String licenseNo){
        if(licenseNo.substring(0,1).equals("京")){
            return true;
        }else{
            return false;
        }
    }

    public boolean isIndex(String longStr,String shortStr){
        System.out.println(longStr.indexOf(shortStr));
        if(longStr.indexOf(shortStr)!= -1){
            return true;
        }else{
            return false;
        }
    }

    public boolean isChinese(String msg){
        char msgArray[] = msg.toCharArray();
        for(char msgchar:msgArray){
            if(msgchar>= 0x4E00 &&  msgchar <= 0x9FA5){
                return true;
            }
        }
        return false;
    }

}
