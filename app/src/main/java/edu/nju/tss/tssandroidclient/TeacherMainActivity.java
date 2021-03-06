package edu.nju.tss.tssandroidclient;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import edu.nju.tss.tssandroidclient.fragment.AssignmentListFragment_;
import edu.nju.tss.tssandroidclient.fragment.CourseListFragment;
import edu.nju.tss.tssandroidclient.fragment.CourseListFragment_;
import edu.nju.tss.tssandroidclient.fragment.UserInfoFragment_;

@EActivity(R.layout.activity_teacher_main)
public class TeacherMainActivity extends AppCompatActivity {

    @ViewById(R.id.teacherViewPager)
    protected ViewPager viewPager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.teacher_nav_course:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.teacher_nav_assignment:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.teacher_nav_info:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }

    };

    @AfterViews
    void afterView(){
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.teacher_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.setAdapter(new ViewPagerAdapter(this));
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {

        @SuppressWarnings("unchecked")
        private Class<? extends Fragment>[] pages = new Class[]
                {CourseListFragment_.class,AssignmentListFragment_.class, UserInfoFragment_.class};

        public ViewPagerAdapter(FragmentActivity context) {
            super(context.getSupportFragmentManager());
        }

        @Override
        public int getCount() {
            return pages.length;
        }

        @Override
        public Fragment getItem(int position) {
            try {
                return pages[position].newInstance();
            } catch (Exception e) {
                return null;
            }
        }
    }

}
