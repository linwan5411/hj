package cn.jeefast.common.utils;

import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * okhttp封装工具类
 * @author lc
 */
public class HttpClientUtils {

    private static MediaType mediaType = MediaType.parse("application/json;charset=utf-8");

    protected static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    protected static final OkHttpClient okHttpClient = new OkHttpClient().newBuilder().connectTimeout(3, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .build();
    protected static final OkHttpClient okHttpClient2 = new OkHttpClient().newBuilder().connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build();

    public static String doPostJson(String url,String jsonStr){
        return doPostJsonWithToken(url,jsonStr,null);
    }

    public static String doPostJsonWithToken(String url,String jsonStr,String token){
        RequestBody requestBody = new FormBody.Builder().build();
        if (StringUtils.isNoneBlank(jsonStr)){
            requestBody = FormBody.create(mediaType,jsonStr);
            logger.debug("请求 jsonStr：{}" ,jsonStr);
        }
        return post(url,requestBody,token);
    }

    public static String doPost(String url, Map<String,Object> paramsMap){
        return doPostWithToken(url,paramsMap,null);
    }

    public static String doPostWithToken(String url, Map<String,Object> paramsMap,String token){
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String,Object> map : paramsMap.entrySet()) {
            formBody.addEncoded(map.getKey(),map.getValue() != null ? map.getValue().toString() : "");
        }
        return post(url,formBody.build(),token);
    }

    public static String post(String url, RequestBody requestBody,String token){
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            if (token!=null){
                request = request.newBuilder().addHeader("token",token).build();
            }
            Long start = System.currentTimeMillis();
            logger.debug("请求 request：{}" ,request);
            Response response = okHttpClient.newCall(request).execute();
            String responseStr = response.body().string();
            logger.debug("请求 request：{} 请求耗时{}ms 返回 Response：{}" ,request,System.currentTimeMillis()-start,responseStr);
            return responseStr;
        } catch (ConnectException ex) {
            logger.error("连接超时",ex);
            throw new RuntimeException("连接超时");
        } catch (Exception e) {
            logger.error("发起请求失败",e.getMessage());
            throw new RuntimeException("发起请求失败");
        }
    }

    public static String get(String url,String token){
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            if (token!=null){
                request = request.newBuilder().addHeader("token",token).build();
            }
            Response response = okHttpClient.newCall(request).execute();
            Long start = System.currentTimeMillis();
            logger.debug("请求 request：{}" ,request);
            String responseStr = response.body().string();
            logger.debug("请求 request：{} 请求耗时{}ms 返回 Response：{}" ,request,System.currentTimeMillis()-start,responseStr);
            return responseStr;
        } catch (ConnectException ex) {
            logger.error("连接超时",ex);
            throw new RuntimeException("连接超时");
        } catch (Exception e) {
            logger.error("发起请求失败",e.getMessage());
            throw new RuntimeException("发起请求失败");
        }
    }

    public static void doAsyncPost(String url, Map<String,Object> paramsMap){
        doAsyncPostWithToken(url,paramsMap,null);
    }

    public static void doAsyncPostWithToken(String url, Map<String,Object> paramsMap,final String token){
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String,Object> map : paramsMap.entrySet()) {
            formBody.addEncoded(map.getKey(),map.getValue().toString());
        }
        asyncPost(url,formBody.build(),token);
    }

    public static void asyncPost(String url, RequestBody requestBody, String token){
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .tag(3)  //重试次数
                    .build();
            if (token!=null){
                request = request.newBuilder().addHeader("token",token).build();
            }
            logger.debug("发起异步请求 request = " + request);
            Call call = okHttpClient2.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    int count = (int)call.request().tag();
                    logger.error("发起异步请求失败 count = "+count+" request = "+call.request() ,e);
                    if(count > 0){//如果超时并未超过指定次数，则重新连接
                        okHttpClient2.newCall(call.request().newBuilder().tag(--count).build()).enqueue(this);
                    }
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        logger.debug("发起异步请求成功",response.body().string());
                    }else {
                        logger.debug("发起异步请求失败");
                    }
                }
            });
        } catch (Exception e) {
            logger.error("发起请求失败",e.getMessage());
        }
    }

    public static Response doPost2(String url, Map<String,Object> paramsMap){
        try {
            FormBody.Builder formBody = new FormBody.Builder();
            for (Map.Entry<String,Object> map : paramsMap.entrySet()) {
                formBody.addEncoded(map.getKey(),map.getValue() != null ? map.getValue().toString() : "");
            }
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody.build())
                    .build();
            Long start = System.currentTimeMillis();
            logger.debug("请求 request：{}" ,request);
            Response response = okHttpClient2.newCall(request).execute();
            logger.debug("请求 request：{} 请求耗时{}ms 返回 Response：{}" ,request,System.currentTimeMillis()-start,response);
            return response;
        } catch (ConnectException ex) {
            logger.error("连接超时",ex);
            throw new RuntimeException("连接超时");
        } catch (Exception e) {
            logger.error("发起请求失败",e.getMessage());
            throw new RuntimeException("发起请求失败");
        }
    }
}
