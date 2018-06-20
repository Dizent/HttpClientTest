package Test;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

/**
 * Created by haitian on 2018/2/5.
 */
@SuppressWarnings("restriction")
public class encryptUtil {

    private static final String ALGORITHM = "DESede";
    private static final String CIPHER_TRANSFORMAT = "DESede/ECB/PKCS5Padding";
    private static final String ENCODING = "UTF-8";

    public static String aesEncrypt(String str, byte[] key) throws Exception {
        if (str == null || key == null) return null;
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));
        byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
        return new BASE64Encoder().encode(bytes);
    }

    public static String aesDecrypt(String str, String key) throws Exception {
        if (str == null || key == null) return null;
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
        byte[] bytes = new BASE64Decoder().decodeBuffer(str);
        bytes = cipher.doFinal(bytes);
        return new String(bytes, "utf-8");
    }

    public static String Encrypt(String plainText) throws IOException{
        StringBuffer buf = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            System.out.println("result: " + buf.toString());// 32位的加密
            System.out.println("result: " + buf.toString().substring(8, 24));// 16位的加密
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return buf.toString();
    }

    public static String getMD5(String str, String encoding) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(encoding));
        byte[] result = md.digest();
        StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val < 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toUpperCase();
    }

        public static String encryptToBase64(String plainText, byte[] key) throws Exception {
            SecretKey deskey = new SecretKeySpec(key, ALGORITHM);
            Cipher c1 = Cipher.getInstance(CIPHER_TRANSFORMAT);
            c1.init(Cipher.ENCRYPT_MODE, deskey);

            byte[] result = c1.doFinal(plainText.getBytes(ENCODING));

            return Base64.encodeBase64String(result);
        }

        public static String decryptFromBase64(String base64, String key)
                throws Exception {
            SecretKey deskey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher c1 = Cipher.getInstance(CIPHER_TRANSFORMAT);
            c1.init(Cipher.DECRYPT_MODE, deskey);

            byte[] result = c1.doFinal(Base64.decodeBase64(base64));

            return new String(result, ENCODING);
        }

    public static String encode(String data, String password) throws Exception {
        byte[] DESIV = {0x12, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF};// 设置向量，略去
        AlgorithmParameterSpec iv = null;
        Key key = null;
        DESKeySpec keySpec = new DESKeySpec(password.getBytes("utf-8"));// 设置密钥参数
        iv = new IvParameterSpec(DESIV);// 设置向量
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
        key = keyFactory.generateSecret(keySpec);// 得到密钥对象
        Cipher enCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");// 得到加密对象Cipher
        enCipher.init(Cipher.ENCRYPT_MODE, key);// 设置工作模式为加密模式，给出密钥和向量
        byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(pasByte);
    }

    public static String md5(byte[] bs) throws Exception{
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(bs);
        String hex = new BigInteger(1, digest.digest()).toString(16);
        // 补齐BigInteger省略的前置0
        return new String(new char[32 - hex.length()]).replace("\0", "0") + hex;
    }


    public static String AESEncode(String encodeRules,String content) throws Exception{
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
            //3.产生原始对称密钥
            SecretKey original_key=keygen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte [] raw=original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key=new SecretKeySpec(raw, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte [] byte_encode=content.getBytes("utf-8");
            //9.根据密码器的初始化方式--加密：将数据加密
            byte [] byte_AES=cipher.doFinal(byte_encode);
            //10.将加密后的数据转换为字符串
            //这里用Base64Encoder中会找不到包
            //解决办法：
            //在项目的Build path中先移除JRE System Library，再添加库JRE System Library，重新编译后就一切正常了。
            String AES_encode=new BASE64Encoder().encode(byte_AES);
            //11.将字符串返回
            return AES_encode;
    }


    public static byte[] build3DesKey(byte[] keyStr) throws Exception{
                 byte[] key = new byte[24];    //声明一个24位的字节数组，默认里面都是0
                 byte[] temp = keyStr;    //将字符串转成字节数组

                 /*
          * 执行数组拷贝
          * System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
          */
                 if(key.length > temp.length){
                         //如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
                         System.arraycopy(temp, 0, key, 0, temp.length);
                     }else{
                         //如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
                         System.arraycopy(temp, 0, key, 0, key.length);
                     }
                 return key;
             }


}
