package com.hzk.community.community.dto;

/**
 * 类与类之间的网络传输，命名为DTO，表示为网络传输的一个Object
 * 在数据当中的应该创建为一个model
 */

/**
 * http://localhost:8080/callback?code=ff3b215f7448749d81c3&state=1
 * 该实体对象封装了access token中的属性
 * 如果传输参数拥有两个以上的参数，那么就要把这些参数封装成一个对象
 */
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_url;
    private String state;

    public AccessTokenDTO() {

    }

    public AccessTokenDTO(String client_id, String client_secret, String code, String redirect_url, String state) {
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.code = code;
        this.redirect_url = redirect_url;
        this.state = state;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public String getCode() {
        return code;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "AccessTokenDTO{" +
                "client_id='" + client_id + '\'' +
                ", client_secret='" + client_secret + '\'' +
                ", code='" + code + '\'' +
                ", redirect_url='" + redirect_url + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
