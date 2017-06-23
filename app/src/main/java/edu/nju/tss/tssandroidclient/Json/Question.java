package edu.nju.tss.tssandroidclient.Json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by admin on 2017/6/20.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Question implements Serializable {

    int id;
    String title;
    String description;
    String difficulty;
    String gitUrl;
    UserJson creator;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public UserJson getCreator() {
        return creator;
    }

    public void setCreator(UserJson creator) {
        this.creator = creator;
    }
}
