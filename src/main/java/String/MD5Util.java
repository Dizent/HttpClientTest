package String;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;

public class MD5Util {

    private static final String EMPTY_STRING = "";

    public static byte[] getMD5Mac(byte[] bySourceByte) {
        byte[] byDisByte;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(bySourceByte);
            byDisByte = md.digest();
        } catch (NoSuchAlgorithmException n) {
            return (null);
        }
        return (byDisByte);
    }

    public static String bintoascii(byte[] bySourceByte) {
        int len, i;
        byte tb;
        char high, tmp, low;
        String result = EMPTY_STRING;
        len = bySourceByte.length;

        for (i = 0; i < len; i++) {
            tb = bySourceByte[i];
            tmp = (char) ((tb >>> 4) & 0x000f);
            if (tmp >= 10)
                high = (char) ('a' + tmp - 10);
            else
                high = (char) ('0' + tmp);
            result += high;
            tmp = (char) (tb & 0x000f);
            if (tmp >= 10)
                low = (char) ('a' + tmp - 10);
            else
                low = (char) ('0' + tmp);
            result += low;
        }
        return result;
    }

    public static String getMD5ofStr(String inbuf, String encoding) {
//        if (StringUtils.isEmpty(inbuf)) {
//            return EMPTY_STRING;
//        }
        try {
            return bintoascii(getMD5Mac(inbuf.getBytes(encoding)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return EMPTY_STRING;
    }

    public static String createSignUsingMD5(String inbuf, String encoding) {
        return getMD5ofStr(inbuf, encoding).toLowerCase();
    }

    /**
     * MD5加密后转大写
     * @param inbuf
     * @param encoding
     * @return
     */
    public static String createSignUsingMD5WithUpper(String inbuf,String encoding) {
        return getMD5ofStr(inbuf, encoding).toUpperCase();
    }


    public static String encrypt(String text,String key) throws InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, NoSuchProviderException, ShortBufferException, UnsupportedEncodingException {
//        String text = "12345678";   //要加密的字符串
//        String key = "Password"; //私钥   AES固定格式为128/192/256 bits.即：16/24/32bytes。DES固定格式为128bits，即8bytes。
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] sourceBytes = key.getBytes();
        byte[] keyBytes = md5.digest(sourceBytes);
        //两个参数，第一个为私钥字节数组， 第二个为加密方式 AES或者DES
        Key keySpec = new SecretKeySpec(keyBytes, "DESede");
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        //实例化加密类，参数为加密方式，要写全
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS7Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        // /初始化，此方法可以采用三种方式，按服务器要求来添加。（1）无第三个参数（2）第三个参数为SecureRandom random = new SecureRandom();中random对象，随机数。(AES不可采用这种方法)（3）采用此代码中的IVParameterSpec
        byte[] b = cipher.doFinal(text.getBytes());
        String ret = Base64.encodeBase64String(b);//Base64、HEX等编解码
        return ret;
    }

    public static String Md5(String plainText) {
        StringBuffer buf = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes("utf-8"));
            byte b[] = md.digest();
            int i;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return buf.toString();
    }
}
