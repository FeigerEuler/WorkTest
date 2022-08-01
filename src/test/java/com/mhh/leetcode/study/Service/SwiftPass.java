package com.mhh.leetcode.study.Service;

import com.mhh.leetcode.utils.SignUtil;
import com.mhh.leetcode.utils.SignUtils;
import okhttp3.*;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class SwiftPass {
    public static final MediaType FORM_CONTENT_TYPE
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

    private  String priKey = "MIIEowIBAAKCAQEAn1PL+AVK/NUipvx9GoW3UGYxKTvJzMpPsbmEmrR8J1OP/5uO0fuBtmAeGejx7CYuPknHyaULrRYSqxcE12ekw6i72qmQw5CauRtu9YPj5q8xae3+NPxPZtKo3i+uESa2tK2apdt3bz1uL1aJjEYEXKDzp9GFlafjOk+T2hYBODPHYeJTGc+e4FcDnXdPBffWfdn90IGbRIyPvxF7zHp18W/2kaHzZLflnZNhCyejCD9HfVFOb91oC7rjb+UJ0GM4/0TK/Bg/sKw2IjUCcbGk5hPzhCYILLpoQ+HmGOWJUcthCDedOHLo2FSlNcDkmYKvaCQspxkdGj7kFsefCB8RLQIDAQABAoIBAB3xBXfKptj0xNpUwL3nOxfk3IA/OCFAM9HWZ1EbcemCDqGefix/81Ruo7mCyVPzqOUTwHPojFUuvLtiTAqe85vBbewdyZK27hyzRxrkekOz0LlzfD5A3Dsag5mdpDyc5eD2bwWURNGm3boPrTGrRs6RgLic61nAfzdKFSL616fT5zqn6P2OZ8ZqThQiJiaaSEtwC9aX7TbUGaPRteQjxw2LaVG1z7Q9jI41uDi8Srj0aB0sFIKOi1vV0sDV33LivpW8yy9AYZVg/SFf/sghFBG5TfO8scX4aUBWgIV4+Vvgd2LDDmbivd/lzDqWJsPBKVAzDmzp78G9DUh1K1dkVAECgYEA06I5ZzUtXu/rYz8xbToRj49bggTwBCHhwHJz4HfNGxIro8TCwpxmgL5p06zLuF6NHuXwD+Be0elA/iFLH69wgALuwmnUOPU1vkqLwL3IsvQGbklVSMMp7ukHWIBcjyntS2WABKuZPpgdVYeatEaMTDQAOaRmCoPPuDJprCYu1+0CgYEAwLpwjqiIh9dvG7P9VUSIQ6c7kVuUK/0kWztrGlYOUUh5zv5xk9jrsUk05HPMq8e6nfUpgMV5eZEdt/A4TDkDp0QaJpXZlbQCbiYK3UMrdMF33kFUKrIaglGhP8YRG6UgnWz0mWymWXaZVdMujub1yIccPtagL1y3McOOrN0fdkECgYEApUPno8sJFTwyCxzMEknocL04bn5AmFmFG23FQ6MKTnuQDKGYnbTY7uzDY4Nqyx07Asf5Aczq/u0xzcJeEe5E4hJoNPXDvn7uax13qRoLYoPhibpGmT2ev2zGSbxji4DoR5qJsBv43cKYFbSBgQoegVuSWXEiJk/spCDqGH7w0bkCgYAoMDh8ZwRhXzkaC8RN3YaUpPC09OXG7niAVbmVulAt/vD3ivNgIyGyzFMtOEB7qaBug8rtwT/9a5dI3dy/eDwcywSw+xpuLyU6ltA5J2KObDF0tSa56H3Cowb+52x12U8dxLSUsaVWjnaXAPx6bArgj9wB9ntcpPbGd8LupQWEgQKBgAmDOtI9QlUTUmcVDUYIsH1s0uvaiNXnsz3O8UBIGp6VQSPUmXEzadZKQypkIpFyeGG+V1p9dhwmP7BXgVukrt67jasb4NV4lD2x+gOp8XeQd0F9JkvfsA5ujjcrDy0XSm3xTtJnT+Ansrhj3bwyDFz+HSXS4TFC+KXCSyNAe4AR";
    @Test
 public  void mhfTest() {
     // 1、构建OkHttpClient
     OkHttpClient client = new OkHttpClient();


// 4、 Call发起同步或异步请求
//实例化RequestBody对象
     Map<String,String> map = new HashMap<String,String>();
     map.put("service","pay.unionpay.wappay");
     map.put("charset","UTF-8");
     map.put("version","2.0");
     map.put("sign_type","RSA_1_256");
     map.put("mch_id","403560187763");
     map.put("out_trade_no","1001123");
     map.put("body","test");
     map.put("total_fee","1");
     map.put("mch_create_ip","111.0.234.249");
     map.put("notify_url","http://mhf418.work:8088/api/order");
     map.put("nonce_str","iojoijoifj");
     map.put("callback_url","http://baidu.com");
        Map<String,String> params = SignUtils.paraFilter(map);
        StringBuilder buf = new StringBuilder((params.size() +1) * 10);
        SignUtils.buildPayParams(buf,params,false);
        String preStr = buf.toString();

        System.out.println("preSre="+preStr);
        map.put("sign", SignUtil.getSign("RSA_1_256", preStr));
     String xmlStr = toXml(map);
     System.out.println(xmlStr);
     System.out.println("\n");


     RequestBody body=RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),xmlStr);

     Request request = new Request.Builder()
             .url("https://pay.swiftpass.cn/pay/gateway")
             .post(body)
             .build();

     String data = null;
     try {
         // Response对象存储的就是获取的数据
         Response response = client.newCall(request).execute();
         data = response.body().string();
     } catch (IOException e) {
         e.printStackTrace();
     }


     System.out.println(data);
 }
    public String toXml(Map<String, String> params){
        StringBuilder buf = new StringBuilder();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        buf.append("<xml>");
        for(String key : keys){
            buf.append("<").append(key).append(">");
            buf.append(params.get(key));
            buf.append("</").append(key).append(">\n");
        }
        buf.append("</xml>");
        return buf.toString();
    }
}
