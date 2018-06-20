import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.DateUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;



    /**
     * 在java中处理http请求.
     * @author nagsh
     *
     */
    public class HttpClient {

        /**
         * 处理post请求.
         * @param url  请求路径
         * @param params  参数
         * @return  json
         */
        private static String post(String url,String params){
            //实例化httpClient
            CloseableHttpClient httpclient = HttpClients.createDefault();
            //实例化post方法
            HttpPost httpPost = new HttpPost(url);
            //结果
            CloseableHttpResponse response = null;
            List<NameValuePair> npvs = new ArrayList<NameValuePair>();
            npvs.add(new BasicNameValuePair("",params));
            String content="";
            try {
                //提交的参数
                httpPost.setHeader("Content-type", "application/json;charset=UTF-8");
                //将参数给post方法
                httpPost.setEntity(new StringEntity(params,Charset.forName("UTF-8")));
                //执行post方法
                response = httpclient.execute(httpPost);

                if(response.getStatusLine().getStatusCode()==200){
                    content = EntityUtils.toString(response.getEntity(),"utf-8");
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }
        public static void main(String[] args) throws IOException {
            Map map = new HashMap();
            String url = "http://119.253.81.113/tk-link-carModel/rest";
//            String url = "http://119.253.81.113/tk-link-carModel/rest?head={\"version\": \"v1\",\"function\": \"getToken\",\"transTime\": \"2017-12-25 17:44:17\",\"channelId\": \"63469\",\"reqMsgId\": \"sdjfweotjgjiowjrtekljwre\"}&apply_content={}";
            ObjectMapper mapper = new ObjectMapper();
            String json = "";
            Map head = new HashMap();
            Map params = new HashMap();
            //版本号（匹配接口版本）
            head.put("version","v1");
            //接口代码（获取token）
            head.put("function","getToken");
            //请求时间
            head.put("transTime", DateUtils.formatDate(new Date(),"YYYY-MM-dd hh:mm:ss"));
            //渠道id
            head.put("channelId","63469");
            //请求报文id，自定义生成，服务器原样返回）
            head.put("reqMsgId","123456789");
            //将Map转换为Json字符串
            map.put("head",head);
            map.put("apply_content","{}");
            json = mapper.writeValueAsString(map);
            System.out.println(json);
            String entityStr = post(url,json);
            System.out.println(entityStr);
        }
}
