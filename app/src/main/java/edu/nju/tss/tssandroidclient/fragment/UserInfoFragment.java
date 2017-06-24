package edu.nju.tss.tssandroidclient.fragment;

import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.io.IOException;

import edu.nju.tss.tssandroidclient.Json.UserJson;
import edu.nju.tss.tssandroidclient.LoginActivity_;
import edu.nju.tss.tssandroidclient.R;
import edu.nju.tss.tssandroidclient.service.BasicAuthInterceptor;
import edu.nju.tss.tssandroidclient.sharedPref.LoginPref_;

@EFragment(R.layout.fragment_user_info)
public class UserInfoFragment extends Fragment {

    @Pref
    LoginPref_ loginPref;

    @ViewById(R.id.user_info_avatar)
    ImageView avatarView;

    @ViewById(R.id.user_info_username)
    TextView usernameText;

    @ViewById(R.id.user_info_email)
    TextView emailText;

    @ViewById(R.id.user_info_name)
    TextView nameText;

    @ViewById(R.id.user_info_type)
    TextView userTypeText;

    @Bean
    BasicAuthInterceptor basicAuthInterceptor;

    @Click(R.id.logoutButton)
    void logout(){
        System.out.println(loginPref.userJson().get());
        basicAuthInterceptor.clear();
        loginPref.clear();
        LoginActivity_.intent(this).start();
    }

    @AfterViews
    void afterViews(){

        ObjectMapper mapper = new ObjectMapper();
        UserJson userJson = null;

        if(!loginPref.userJson().exists()){
            return;
        }

        try {
            userJson = mapper.readValue(loginPref.userJson().get(),UserJson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(userJson==null){
            return;
        }


        if(userJson.getAvatar()!=null){
            ImageLoader.getInstance()
                    .displayImage(userJson.getAvatar(), avatarView);
        }

        usernameText.setText(userJson.getUsername());
        emailText.setText(userJson.getEmail());
        nameText.setText(userJson.getName());
        userTypeText.setText(userJson.getType().getText());

    }

}
