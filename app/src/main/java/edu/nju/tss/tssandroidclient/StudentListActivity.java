package edu.nju.tss.tssandroidclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ReceiverAction;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.List;

import edu.nju.tss.tssandroidclient.Json.CourseJson;
import edu.nju.tss.tssandroidclient.Json.UserJson;
import edu.nju.tss.tssandroidclient.service.TssRestService;

@EActivity(R.layout.activity_student_list)
public class StudentListActivity extends AppCompatActivity {

    @Extra
    CourseJson courseJson;

    @ViewById(R.id.studentList)
    ListView listView;

    @ViewById(R.id.courseName)
    TextView courseName;

    @RestService
    TssRestService tssRestService;

    QuickAdapter<UserJson> quickAdapter;

    @AfterViews
    void afterViews(){

        courseName.setText(courseJson.getName());

        quickAdapter = new QuickAdapter<UserJson>(this,R.layout.student_list_item) {
            @Override
            protected void convert(BaseAdapterHelper helper, UserJson item) {
                helper.setText(R.id.studentUsername,item.getUsername());
                helper.setText(R.id.studentName,item.getName());
                helper.setText(R.id.studentEmail,item.getEmail());
            }
        };

        listView.setAdapter(quickAdapter);
        fetchData();
    }

    @Background
    void fetchData(){
        List<UserJson> students =
                tssRestService.getGroupStudent(courseJson.getId());
        update(students);
    }

    @UiThread
    void update(List<UserJson> userJsons){
        quickAdapter.replaceAll(userJsons);
    }


}
