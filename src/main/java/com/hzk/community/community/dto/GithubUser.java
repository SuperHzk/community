package com.hzk.community.community.dto;

/**
 *https://api.github.com/user?access_token=78bb543204e760479fe0999723d602bded8be837
 * 请求中得到的属性
 * （选择需要的的属性）进行属性封装
 */
public class GithubUser {
    private String name;
    //除了GitHub还要支持其他多平台，所以这里设置为Long
    private  Long id;
    private String bio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}
