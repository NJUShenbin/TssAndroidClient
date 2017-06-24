package edu.nju.tss.tssandroidclient.fragment;

import android.support.v4.app.Fragment;
import android.widget.ListView;

import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.List;

import edu.nju.tss.tssandroidclient.Json.AssignmentJson;
import edu.nju.tss.tssandroidclient.Json.CourseJson;
import edu.nju.tss.tssandroidclient.R;
import edu.nju.tss.tssandroidclient.StudentListActivity_;
import edu.nju.tss.tssandroidclient.service.TssRestService;

/**
 * Created by admin on 2017/6/24.
 */
@EFragment(R.layout.fragment_course_list)
public class CourseListFragment extends Fragment {

    @ViewById(R.id.courseList)
    ListView listView;

    QuickAdapter<CourseJson> quickAdapter;

    @RestService
    TssRestService tssRestService;

    @AfterViews
    void afterViews(){
        quickAdapter = new QuickAdapter<CourseJson>(getContext(),R.layout.course_list_item) {
            @Override
            protected void convert(BaseAdapterHelper helper, CourseJson item) {
                helper.setText(R.id.courseName,item.getName());
            }
        };

        listView.setAdapter(quickAdapter);
        fetchCourse();
    }

    @Background
    void fetchCourse(){
        List<CourseJson> courseJsons = tssRestService.getGroup();
        if(courseJsons!=null){
            updateList(courseJsons);
        }
    }

    @UiThread
    void updateList(List<CourseJson> courseJsons){
        quickAdapter.replaceAll(courseJsons);
    }

    @ItemClick(R.id.courseList)
    void itemClick(CourseJson courseJson){
        StudentListActivity_.intent(getContext()).courseJson(courseJson).start();
    }

}
