package edu.nju.tss.tssandroidclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;


import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.springframework.util.StringUtils;

import edu.nju.tss.tssandroidclient.Json.AssignmentJson;
import edu.nju.tss.tssandroidclient.Json.Question;

@EActivity(R.layout.activity_assignment)
public class AssignmentActivity extends AppCompatActivity {

    @Extra
    AssignmentJson assignmentJson;

    @ViewById(R.id.courseTitle)
    TextView title;

    @ViewById(R.id.courseDescription)
    TextView description;

    @ViewById(R.id.courseStart)
    TextView start;

    @ViewById(R.id.courseEnd)
    TextView end;

    @ViewById(R.id.questionList)
    ListView courseList;

    QuickAdapter<Question> adapter;

    @AfterViews
    void afterViews(){

        title.setText(assignmentJson.getTitle());
        if(!StringUtils.isEmpty(assignmentJson.getDescription())){
            description.setText(assignmentJson.getDescription());
        }

        start.setText(assignmentJson.getStartAt());
        end.setText(assignmentJson.getEndAt());

        adapter = new QuickAdapter<Question>(this,R.layout.question_list_item) {
            @Override
            protected void convert(BaseAdapterHelper helper, Question item) {
                helper.setText(R.id.questionTitle,item.getTitle());
                helper.setText(R.id.questionDescription,item.getDescription());
                helper.setText(R.id.questionDifficulty,"难度"+item.getDifficulty());
                helper.setText(R.id.questionUrl,item.getGitUrl());
            }
        };

        courseList.setAdapter(adapter);
        adapter.replaceAll(assignmentJson.getQuestions());
    }

}
