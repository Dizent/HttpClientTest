package String;

/**
 * @author: Dizent<zhentao.d @ winbaoxian.com>
 * @date :2018/3/7-11:32
 * @description :
 * @Modify By :
 */
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 3DES（或称为Triple DES）是三重数据加密算法（TDEA，Triple Data Encryption
 * Algorithm）块密码的通称。它相当于是对每个数据块应用三次DES加密算法
 *
 * @author jianggujin
 *
 */
public class HQTripleDES
{
    private static HQTripleDES tripleDES = new HQTripleDES();

    public static HQTripleDES getInstance()
    {
        return tripleDES;
    }

    private HQTripleDES()
    {
    }

    /**
     * DES算法
     *
     * @author jianggujin
     *
     */
    public static enum HQDESAlgorithm
    {

        DES("DES"), DESede("DESede");
        private String name;

        private HQDESAlgorithm(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return this.name;
        }
    }

    /**
     * 工作模式
     *
     * @author jianggujin
     *
     */
    public static enum HQWorkingMode
    {

        /**
         * 该模式不能使用向量
         */
        ECB("ECB"), CBC("CBC"), CFB("CFB"), OFB("OFB"), CTR("CTR");
        private String name;

        private HQWorkingMode(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return this.name;
        }
    }

    /**
     * 填充方式
     *
     * @author jianggujin
     *
     */
    public static enum HQPadding
    {

        NoPadding("NoPadding"), PKCS5Padding("PKCS5Padding");
        private String name;

        private HQPadding(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return this.name;
        }
    }

    public byte[] encrypt(byte[] data, byte[] key, HQDESAlgorithm algorithm, HQWorkingMode workingMode,
                          HQPadding padding, byte[] iv)
    {
        return encrypt(data, key, algorithm.getName(), workingMode.getName(), padding.getName(), iv);
    }

    public byte[] encrypt(byte[] data, byte[] key, String algorithm, String workingMode, String padding, byte[] iv)
    {
        return operate(Cipher.ENCRYPT_MODE, data, key, algorithm, workingMode, padding, iv);
    }

    public byte[] decrypt(byte[] data, byte[] key, HQDESAlgorithm algorithm, HQWorkingMode workingMode,
                          HQPadding padding, byte[] iv)
    {
        return decrypt(data, key, algorithm.getName(), workingMode.getName(), padding.getName(), iv);
    }

    public byte[] decrypt(byte[] data, byte[] key, String algorithm, String workingMode, String padding, byte[] iv)
    {
        return operate(Cipher.DECRYPT_MODE, data, key, algorithm, workingMode, padding, iv);
    }

    private byte[] operate(int mode, byte[] data, byte[] key, String algorithm, String workingMode, String padding,
                           byte[] iv)
    {
        try
        {
            String fullAlg = algorithm + "/" + workingMode + "/" + padding;
            Cipher cipher = Cipher.getInstance(fullAlg);
            SecretKey secretKey = new SecretKeySpec(key, algorithm);
            if (!HQWorkingMode.ECB.getName().equalsIgnoreCase(workingMode))
            {
                IvParameterSpec ivSpec = new IvParameterSpec(iv);
                cipher.init(mode, secretKey, ivSpec);
            }
            else
            {
                cipher.init(mode, secretKey);
            }
            return cipher.doFinal(data);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(e);
        }
    }

    public byte[] initKey(HQDESAlgorithm algorithm)
    {
        return initKey(algorithm.getName());
    }

    public byte[] initKey(String algorithm)
    {
        try
        {
            KeyGenerator kg = KeyGenerator.getInstance(algorithm);
            return kg.generateKey().getEncoded();
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(e);
        }
    }

    public byte[] initIv(HQDESAlgorithm algorithm, HQWorkingMode workingMode, HQPadding padding)
    {
        return initIv(algorithm.getName(), workingMode.getName(), padding.getName());
    }

    /**
     * 初始化向量
     *
     * @param algorithm
     * @param workingMode
     * @param padding
     * @return
     */
    public byte[] initIv(String algorithm, String workingMode, String padding)
    {
        try
        {
            String fullAlg = algorithm + "/" + workingMode + "/" + padding;
            Cipher cipher = Cipher.getInstance(fullAlg);
            int blockSize = cipher.getBlockSize();
            byte[] iv = new byte[blockSize];
            for (int i = 0; i < blockSize; i++)
            {
                iv[i] = 0;
            }
            return iv;
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(e);
        }
    }
}
