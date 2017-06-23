package edu.nju.tss.tssandroidclient.util;

/**
 * Created by admin on 2017/6/20.
 */

public enum UserType {

    student,teacher,admin;

    public String getText() {
        switch (this){
            case student:
                return "学生";
            case teacher:
                return "教师";
            case admin:
                return "管理员";
        }
        return "";
    }
}
