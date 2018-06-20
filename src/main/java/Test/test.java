package Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * 在java中处理http请求.
 * @author nagsh
 *
 */
public class test {

    /**
     * 处理post请求.
     * @param url  请求路径
     * @param params  参数
     * @return  json
     */
    public String post(String url,Map<String, String> params){
        //实例化httpClient
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //实例化post方法
        HttpPost httpPost = new HttpPost(url);
        //处理参数
        List<NameValuePair> nvps = new ArrayList <NameValuePair>();
        Set<String> keySet = params.keySet();
        for(String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }
        //结果
        CloseableHttpResponse response = null;
        String content="";
        try {
            //提交的参数
            UrlEncodedFormEntity uefEntity  = new UrlEncodedFormEntity(nvps, "UTF-8");
            //将参数给post方法
            httpPost.setEntity(uefEntity);
            //执行post方法
            response = httpclient.execute(httpPost);

//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                //创建一个输入流对象
//                InputStream instream = entity.getContent();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
//                StringBuilder sb = new StringBuilder();
//                String line = null;
//                while ((line = reader.readLine()) != null) {
//                    sb.append(line + "\n");
//                }
//                instream.close();
//                System.out.println(sb.toString());
//            }
            if(response.getStatusLine().getStatusCode()==200){
                content = EntityUtils.toString(response.getEntity(),"UTF-8");
                System.out.println(content);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }


    public static void main(String[] args) throws Exception{

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        sun.misc.BASE64Encoder baseEncoder = new sun.misc.BASE64Encoder();
        byte[] key = md5.digest("Password".getBytes("utf-8"));
//        key = encryptUtil.build3DesKey(key);
        String t = encryptUtil.aesEncrypt("123456789", key);
        System.out.println("123");
    }




}