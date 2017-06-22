package edu.nju.tss.tssandroidclient.service;


import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import edu.nju.tss.tssandroidclient.Json.LoginJson;
import edu.nju.tss.tssandroidclient.Json.UserJson;

/**
 * Created by admin on 2017/6/20.
 */
@Rest(rootUrl = "http://115.29.184.56:8090/api",converters = {MappingJackson2HttpMessageConverter.class})
public interface LoginRestService {

    @Post("/user/auth")
    public UserJson login(@Body LoginJson userJson);

}
