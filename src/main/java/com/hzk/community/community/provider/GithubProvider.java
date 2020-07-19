package com.hzk.community.community.provider;


import com.alibaba.fastjson.JSON;
import com.hzk.community.community.dto.AccessTokenDTO;
import com.hzk.community.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 提供第三方服务
 * OKHttp-服务
 * @Component  将此类初始化到SpringIOC上下文容器
 */

@Component
public class GithubProvider {

    //获取access token ，这个是post请求
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
         MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String str=response.body().string();
            String token = str.split("&")[0].split("=")[1];
            return token;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public  GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String s = response.body().string();

            //将String的JSON对象解析转化为Java的GithubUser对象，
            GithubUser githubUser = JSON.parseObject(s, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //异常或者其他情况直接抛出返回null
        return null;
    }
}
