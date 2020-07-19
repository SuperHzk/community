package com.hzk.community.community.dto;

/**
 *https://api.github.com/user?access_token=78bb543204e760479fe0999723d602bded8be837
 * 请求中得到的属性
 * （选择需要的的属性）进行属性封装
 */
public class GithubUser {
    private String name;
    private  String id;
    private String bio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
