package edu.nju.tss.tssandroidclient.Json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import edu.nju.tss.tssandroidclient.util.Gender;
import edu.nju.tss.tssandroidclient.util.UserType;

/**
 * Created by admin on 2017/6/20.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserJson implements Serializable{

    String username;
    String name;
    UserType type;
    String avatar;
    Gender gender;
    String email;

    int gitId;
    String number;

    boolean authority;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGitId() {
        return gitId;
    }

    public void setGitId(int gitId) {
        this.gitId = gitId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isAuthority() {
        return authority;
    }

    public void setAuthority(boolean authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "UserJson{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", avatar='" + avatar + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", gitId=" + gitId +
                ", number='" + number + '\'' +
                ", authority=" + authority +
                '}';
    }
}
