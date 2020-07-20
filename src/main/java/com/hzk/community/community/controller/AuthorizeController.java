package com.hzk.community.community.controller;

import com.hzk.community.community.dto.AccessTokenDTO;
import com.hzk.community.community.dto.GithubUser;
import com.hzk.community.community.mapper.UserMapper;
import com.hzk.community.community.model.User;
import com.hzk.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 *
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;
    //Authorization callback URL  (设置在GitHub的callbackurl)
    //http://localhost:8080/callback?code=ff3b215f7448749d81c3&state=1
    //github access tokens: 78bb543204e760479fe0999723d602bded8be837

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;



    //获取得到User信息，存放到cookie和session中，使用HttpServletRequest得到cookie
    @RequestMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_url(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser != null){
            //System.out.println("count="+userMapper.selectUser());
            //System.out.println(githubUser.toString());
            //将用户信息添加到数据库
            User user = new User();
            //此处插入自定义token作为标识，而不使用GitHub传输的token
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insertUser(user);
            //登录成功，写cookie和session
            request.getSession().setAttribute("user",githubUser);
            //重定向到根目录首页面
            return "redirect:/";
        }else {
            //重定向到根目录首页面
            return "redirect:/";
        }

    }
}
