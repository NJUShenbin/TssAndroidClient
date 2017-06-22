package edu.nju.tss.tssandroidclient.Json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by admin on 2017/6/20.
 */
public class ClassJson {
    int id;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
