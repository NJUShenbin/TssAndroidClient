package edu.nju.tss.tssandroidclient.service;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

import edu.nju.tss.tssandroidclient.Json.AssignmentJson;
import edu.nju.tss.tssandroidclient.Json.CourseJson;
import edu.nju.tss.tssandroidclient.Json.UserJson;

/**
 * Created by admin on 2017/6/20.
 */
@Rest(rootUrl = "http://115.29.184.56:8090/api",
        converters = {MappingJackson2HttpMessageConverter.class},interceptors = {BasicAuthInterceptor.class})
public interface TssRestService {

    @Get("/group")
    List<CourseJson> getGroup();

    @Get("/group/{groupId}/students")
    List<UserJson> getGroupStudent(@Path int groupId);

    @Get("/course/{courseId}/exam")
    List<AssignmentJson> getExam(@Path int courseId);

    @Get("/course/{courseId}/homework")
    List<AssignmentJson> getHomework(@Path int courseId);

    @Get("/course/{courseId}/exercise")
    List<AssignmentJson> getExercise(@Path int courseId);

}
