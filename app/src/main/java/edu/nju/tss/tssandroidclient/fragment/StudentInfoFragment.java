package edu.nju.tss.tssandroidclient.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import edu.nju.tss.tssandroidclient.R;
import edu.nju.tss.tssandroidclient.sharedPref.LoginPref_;

@EFragment(R.layout.fragment_student_info)
public class StudentInfoFragment extends Fragment {

    @Pref
    LoginPref_ loginPref;

//    @ViewById
//    TextView

}
