package edu.nju.tss.tssandroidclient.sharedPref;

import org.androidannotations.annotations.sharedpreferences.SharedPref;

import edu.nju.tss.tssandroidclient.Json.UserJson;

/**
 * Created by admin on 2017/6/22.
 */
@SharedPref
public interface LoginPref {

    String loginJson();

    String userJson();

}
