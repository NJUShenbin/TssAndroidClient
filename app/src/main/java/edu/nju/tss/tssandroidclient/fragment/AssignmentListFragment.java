package edu.nju.tss.tssandroidclient.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.widget.ListView;

import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import edu.nju.tss.tssandroidclient.AssignmentActivity_;
import edu.nju.tss.tssandroidclient.Json.AssignmentJson;
import edu.nju.tss.tssandroidclient.R;
import edu.nju.tss.tssandroidclient.service.TssRestService;

@EFragment(R.layout.fragment_assignment_list)
public class AssignmentListFragment extends Fragment {

    @ViewById(R.id.assignmentTab)
    TabLayout tabLayout;

    @ViewById(R.id.assignmentList)
    ListView listView;

    @RestService
    TssRestService tssRestService;

    QuickAdapter<AssignmentJson> quickAdapter;

    @AfterViews
    void afterViews(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fetchData(tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tabLayout.addTab(tabLayout.newTab().setText("作业"));
        tabLayout.addTab(tabLayout.newTab().setText("考试"));
        tabLayout.addTab(tabLayout.newTab().setText("练习"));

        quickAdapter = new QuickAdapter<AssignmentJson>
                (getContext(),R.layout.assignment_list_item) {
            @Override
            protected void convert(BaseAdapterHelper helper, AssignmentJson item) {
                helper.setText(R.id.courseTitle,item.getTitle());

                if(!StringUtils.isEmpty(item.getDescription())){
                    helper.setText(R.id.courseDescription,item.getDescription());
                }else {
                    helper.setText(R.id.courseDescription,"无描述");
                }

                helper.setText(R.id.courseStart,item.getStartAt());
                helper.setText(R.id.courseEnd,item.getEndAt());
            }
        };

        listView.setAdapter(quickAdapter);

    }

    @Background
    void fetchData(String text){
        List<AssignmentJson> assignmentJsons = null;
        switch (text){
            case "作业":
                assignmentJsons = tssRestService.getHomework(2);
                break;
            case "考试":
                assignmentJsons = tssRestService.getExam(2);
                break;
            case "练习":
                assignmentJsons = tssRestService.getExercise(2);
                break;
        }

        if(assignmentJsons==null){
            assignmentJsons = new ArrayList<>();
        }
        updateList(assignmentJsons);
    }

    @UiThread
    void updateList(List<AssignmentJson> assignmentJsons){
        quickAdapter.replaceAll(assignmentJsons);
    }

    @ItemClick(R.id.assignmentList)
    void itemClick(AssignmentJson assignment){
        AssignmentActivity_.intent(this).assignmentJson(assignment).start();
    }

}
