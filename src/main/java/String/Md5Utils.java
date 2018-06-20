package String;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Arrays;

/**
 * @author: Dizent<zhentao.d       @       winbaoxian.com>
 * @date :2018/3/7-10:53
 * @description :
 * @Modify By :
 */
public class Md5Utils {
    private static final String ALGORITHM = "DESede";

    //默认为 DESede/ECB/PKCS5Padding
    private static final String CIPHER_TRANSFORMAT = "DESede/ECB/PKCS5Padding";

    private static final String ENCODING = "UTF-8";

    public static String encryptToBase64(String plainText, String key) throws Exception {
        SecretKey deskey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher c1 = Cipher.getInstance(CIPHER_TRANSFORMAT);
        c1.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] result = c1.doFinal(plainText.getBytes(ENCODING));
        return Base64.encodeBase64String(result);
    }


    public static byte[] hex(String key) {
        String f = DigestUtils.md5Hex(key);
        byte[] bkeys = new String(f).getBytes();
        byte[] enk = new byte[24];
        for (int i = 0; i < 24; i++) {
            enk[i] = bkeys[i];
        }
        return enk;
    }

    /**
     * 3DES加密
     *
     * @param key    密钥，24位
     * @param srcStr 将加密的字符串
     * @return lee on 2017-08-09 10:51:44
     */
    public static String encode3Des(String key, String srcStr) {
        byte[] keybyte = hex(key);
        byte[] src = srcStr.getBytes();
        try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, "DESecb");
            //加密
            Cipher c1 = Cipher.getInstance("DES/ECB/");
            c1.init(Cipher.ENCRYPT_MODE, deskey);

            String pwd = Base64.encodeBase64String(c1.doFinal(src));
//           return c1.doFinal(src);//在单一方面的加密或解密
            System.out.println(String.valueOf(c1.doFinal(src)));
            return pwd;
        } catch (java.security.NoSuchAlgorithmException e1) {
            // TODO: handle exception
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String key = "Password";
        String data = "12345678";
        String o = encrypt();
        String ii = "e/ufJkaLNE2VKJg0MwoxGRJZoUX8JgwRveL6e8Rr0U18OMlmBFBHEY/SNViw616jIP1GT2a0DeL4DpS08RqjD5OZYKk0xEJACKT/7MIpWELBysA5yWbcnVJaZvutF787LHv++5E+0ph07qS8dti8ExALCnGUdkQocoyzuVBJUs4kYsHUaFZgF/tVsw7su/Ac6gNgIL76dj2I3vTl60FfsPcqYfJ9215qvsYPhyYyOlQNM14TzDHqiAzOBg9m9FW7lZHy5NpC0N0nnV3i1Iy3xCtwczox/0lsyZ/ce9zvKDfB2QaXTKz/y312JgbSsRO67IYQ+1VVrMTqMQb6HeTy8xcMvlQ7WASgze3uWD1A0pAVmk9aGBWOrqEYQgJaM6vAGwzpdaDcjUBIzIObz7b6cg/CbTrug6grW7HYYahp6E6fME5eZQmySLubCLhSVcxh+Wn3Uabhw0aTFx3OBqKMGetkJtoaOrgHUcc2Bee5DJYl7aclk7ScTpm+tSoZyMwIE5SNR+hivSBcnavb2XBYpFI1pWXhQjI7sMNlZl45WrDLyuoEZ4G7EiWyAOB58LFRUjWlZeFCMjvo7dSym0xwX+LAj+F4GSkwqTy2/FGzqlATlI1H6GK9IEy+ZjhftUEV1wHiHw6b3umkn2Xc0ze6XuP16/q20UyU1aSqetnsOsQa97wQlHbulCmGsjYSD/JU8n4l2zkKGwsQ2naYfENp3XoleaxMuykuzSMHlndPuGGaN2WIry/7IqWWYM6zRkNRyZLl7+iXLts4r0SnoZWRbQsFXUmZN5N+xotqIC29KGMs+2kjDrZyVr7j/rvPDKots8sNIJfKV5qxid3zhxVoY2//YtfcDnJCjyOTqCmoWCn6fK0rh3d6XH94n+9JYVbaEWIrqxpprYPFAzEFYfgMMEeamaJ+FFLly3q4Xo+Cbyj+YKlu/pifPzNdIY39UWLcjg6+s5cM/+JLwdIYikZxYqjcIkKfEReyt9nJ51a4lhEb1nYapX2p4WUnQldGVAKLZfWhZ94QA5Xi5P7u3EeD7D09+XuJbTeIVvUASXQaFLXof0ZFDQafwi3k6zQP/sVdx0n8kGf6QefQjAC/VEKtMd1IfkWtDnXT0EQyglPrvSd5WO5PulVUA4qtCqkHPOknW0LrcToZ3FkG1d0KQhqiSnDYpRIST3S3xPts5rV78x08D7Dhy4B3942U1zZiZvc3QAZ3qh+TOQ8ih7ho4H2Zro6qNRFsZKBj6R8zIqFlo3al+e3lr3gLSMrtp7isK8kiAgUi9yhqvlGE7Fg8czwhlugjle3IGW390C8ys+nlCzmmodmcNaqBH6aDU4jX9YjBFj+PePYKbEFnzTOa6llJQgRElVFua039dZMpghWakaMO8Myy0aIEEHmIKiicHkV9DTNeE8wx6ogMzgYPZvRVu7oZ5ccycK+eTTmnVGvOgVW0FgmlP20kNKuKKlz6y/RjPcvetZMNVOVOoerkIznUMYGF5sUHjY+/o83JNiMnKhhqaR0Ig7ScZZhPhefORJTBY0t39WyfwDWm7yKa5nYM9k9ZgJp0y9fNea07gpde3iMiHIh/vJbA1bEc4wwJ2doulXDWligGNerSUlDyrkNbTU90xDp8Q7+Ar6jMXjZaKg8oXf2D3jNYNk7031S4g+HdjJxbUg1le95TukApaNtZNWJ31LvxoxsQYjmjaWfp3S9yBdga6PJAhiQmQRB32TMFrr1FTBABcMLdSH5FrQ510/cQ/4Cf+IHN37P3AkHCPJMxhL785EI8X0upZC4B4SMyoqu5iRh/4Mx/rCO7gDnp3XRPXGMkVVM53XwA1JYD7oeDeo8qEHiUB9DFTQ6MtBgSCs0wNxamkAFWJoBYM1MhhuMv5bNwTh7P5r35qXEmbfIUCOe+cfXJpTm7KGWNzVD69bYRK+FtLFEMjPWW3AwPDlZtLY4hDs+H";
        String checksumString = MD5Util.getMD5ofStr(ii,"UTF-8");
        System.out.println(checksumString);
        byte[] md5Byte = md5.digest(ii.getBytes());
        String checksum = Base64.encodeBase64String(md5Byte).toUpperCase().trim();
        System.out.println(checksum);

        String md5String = MD5Util.Md5(ii).toUpperCase();
        System.out.println(md5String);


    }

    public static String encrypt() throws  InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, NoSuchProviderException, ShortBufferException, UnsupportedEncodingException {
        String text = "123456789";   //要加密的字符串
        String key = "Password"; //私钥   AES固定格式为128/192/256 bits.即：16/24/32bytes。DES固定格式为128bits，即8bytes。
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] sourceBytes = key.getBytes();
        byte[] keyBytes = md5.digest(sourceBytes);
//        byte[] keyBytes = MD5Util.getMD5ofStr(text,"UTF-8").getBytes("UTF-8");
//        String keyString = MD5Util.getMD5ofStr(text,"UTF-8");
        //两个参数，第一个为私钥字节数组， 第二个为加密方式 AES或者DES
        Key keySpec = new SecretKeySpec(keyBytes, "DESede");
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        //实例化加密类，参数为加密方式，要写全
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS7Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        // /初始化，此方法可以采用三种方式，按服务器要求来添加。（1）无第三个参数（2）第三个参数为SecureRandom random = new SecureRandom();中random对象，随机数。(AES不可采用这种方法)（3）采用此代码中的IVParameterSpec
        byte[] b =cipher.doFinal(text.getBytes());
        String ret = Base64.encodeBase64String(b);//Base64、HEX等编解码
        return ret;
    }
}
