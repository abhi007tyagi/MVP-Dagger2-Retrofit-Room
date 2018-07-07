package com.tyagiabhinav.udacitycourseviewer.courseList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tyagiabhinav.udacitycourseviewer.R;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import com.tyagiabhinav.udacitycourseviewer.pojo.Courses;


public class CourseListFragment extends DaggerFragment implements CourseListContract.View{

    @Inject
    CourseListContract.Presenter mPresenter;

    private OnCourseSelectedListener mListener = new OnCourseSelectedListener() {
        @Override
        public void onCouseSelected(Courses selectedCourse) {
            mPresenter.onCourseSelected(selectedCourse);
        }
    };

    @Inject
    public CourseListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();  //prevent leaking activity in
        // case presenter is orchestrating a long running task
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course_list, container, false);

        mPresenter.loadCouses();
        return view;

    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showCourses(List<Courses> courseList) {

    }

    @Override
    public void showLoadingCourseError() {

    }

    @Override
    public void showNoCourse() {

    }


    public interface OnCourseSelectedListener {
        void onCouseSelected(Courses course);
    }
}
