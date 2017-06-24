package edu.nju.tss.tssandroidclient;

import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.util.StringUtils;

import java.io.IOException;

import edu.nju.tss.tssandroidclient.Json.LoginJson;
import edu.nju.tss.tssandroidclient.Json.UserJson;
import edu.nju.tss.tssandroidclient.service.BasicAuthInterceptor;
import edu.nju.tss.tssandroidclient.service.LoginRestService;
import edu.nju.tss.tssandroidclient.sharedPref.LoginPref_;

/**
 * A login screen that offers login via email/password.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity{

    @ViewById(R.id.loginText)
    TextView loginView;

    @ViewById(R.id.passwordText)
    TextView passwordView;

    @RestService
    LoginRestService loginRestService;

    @Pref
    LoginPref_ loginPref;

    @Bean
    BasicAuthInterceptor basicAuthInterceptor;

    @AfterViews
    void afterViews(){
        if(loginPref.userJson().exists() && loginPref.loginJson().exists()){
            ObjectMapper mapper = new ObjectMapper();
            try {
                UserJson userJson =
                        mapper.readValue(loginPref.userJson().get(),UserJson.class);
                LoginJson loginJson =
                        mapper.readValue(loginPref.loginJson().get(),LoginJson.class);

                loginSuccess(loginJson,userJson);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Click(R.id.login_in_button)
    void loginClick(View view){


        String login =  loginView.getText().toString();
        String password = passwordView.getText().toString();

        if(StringUtils.isEmpty(login) || StringUtils.isEmpty(password)){
            return;
        }
        LoginJson loginJson = new LoginJson();
        loginJson.setUsername(login);
        loginJson.setPassword(password);
        loginAsync(loginJson);

    }

    @Background
    void loginAsync(LoginJson loginJson){
        try {
            UserJson userJson = loginRestService.login(loginJson);
            if(userJson!=null){
               loginSuccess(loginJson,userJson);
            }else {
                showErrorMessage();
            }
        } catch (Exception e){
            showErrorMessage();
            e.printStackTrace();
        }
    }

    void loginSuccess(LoginJson loginJson, UserJson userJson){
        basicAuthInterceptor.init(loginJson.getUsername(),loginJson.getPassword());
        ObjectMapper mapper = new ObjectMapper();
        try {
            loginPref.edit()
                    .loginJson().put(mapper.writeValueAsString(loginJson))
                    .userJson().put(mapper.writeValueAsString(userJson))
            .apply();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        showMainActivity(userJson);

    }

    @UiThread
    void showMainActivity(UserJson userJson){
        switch (userJson.getType()){
            case student:
                StudentMainActivity_.intent(getApplication()).start();
                break;
            case teacher:
                TeacherMainActivity_.intent(getApplication()).start();
        }
    }

    @UiThread
    void showErrorMessage(){
        Toast.makeText
                (getApplicationContext(), "账号或密码错误",  Toast.LENGTH_LONG).show();
    }

}


