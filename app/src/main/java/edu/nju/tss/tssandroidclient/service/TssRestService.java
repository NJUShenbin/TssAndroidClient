package edu.nju.tss.tssandroidclient.service;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

import edu.nju.tss.tssandroidclient.Json.ClassJson;
import edu.nju.tss.tssandroidclient.Json.UserJson;

/**
 * Created by admin on 2017/6/20.
 */
@Rest(rootUrl = "http://115.29.184.56:8090/api",converters = {MappingJackson2HttpMessageConverter.class})
public interface TssRestService {

    @Get("/group")
    List<ClassJson> getGroup();

    @Get("/group/{groupId}/students")
    List<UserJson> getGroupStudent(@Path int groupId);

//    @Get("/course/{courseId}/exam")
//    List

}
