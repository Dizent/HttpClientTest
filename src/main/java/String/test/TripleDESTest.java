package String.test;

import String.HQTripleDES;
import String.HQBase64;
import String.HQTripleDES.HQDESAlgorithm;
import String.HQTripleDES.HQPadding;
import org.junit.Test;

/**
 * @author: Dizent<zhentao.d @ winbaoxian.com>
 * @date :2018/3/7-11:33
 * @description :
 * @Modify By :
 */
public class TripleDESTest {
    HQTripleDES tripleDES = HQTripleDES.getInstance();
    HQBase64 base64 = HQBase64.getInstance();

    @Test
    public void encode() throws Exception
    {
        byte[] data = "12345678".getBytes();
        HQDESAlgorithm[] algorithms = HQDESAlgorithm.values();
        for (HQDESAlgorithm algorithm : algorithms)
        {
            System.err.println("==================================");
            System.err.println(algorithm);
            byte[] key = tripleDES.initKey(algorithm);
            System.err.println("密钥：" + base64.encodeToString(key));
            HQTripleDES.HQWorkingMode[] workingModes = HQTripleDES.HQWorkingMode.values();
            for (HQTripleDES.HQWorkingMode workingMode : workingModes)
            {
                HQPadding[] paddings = HQPadding.values();
                for (HQPadding padding : paddings)
                {
                    byte[] iv = tripleDES.initIv(algorithm, workingMode, padding);
                    System.err.println(algorithm + "/" + workingMode + "/" + padding);
                    byte[] result = tripleDES.encrypt(data, key, algorithm, workingMode, padding, iv);
                    System.err.println("加密：" + base64.encodeToString(result));
                    System.err
                            .println("解密：" + new String(tripleDES.decrypt(result, key, algorithm, workingMode, padding, iv)));
                }
            }
        }
    }

}
