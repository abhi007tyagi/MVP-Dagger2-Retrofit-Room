package com.tyagiabhinav.udacitycourseviewer.ui.courseList;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tyagiabhinav.udacitycourseviewer.R;
import com.tyagiabhinav.udacitycourseviewer.model.pojo.Courses;
import com.tyagiabhinav.udacitycourseviewer.utils.uiUtil.DividerLine;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;


public class CourseListFragment extends DaggerFragment implements CourseListContract.View {

    public static final String TAG = CourseListFragment.class.getSimpleName();

    @Inject
    CourseListContract.Presenter mPresenter;

    @Inject
    DividerLine mDividerLine;

    @BindView(R.id.courseList)
    RecyclerView mRecyclerView;

    private boolean mUseSavedState = false;
    private Courses mSelectedCourse;
    private int mSelectedPosition = 0;

    private RecyclerViewAdapter mRecyclerAdapter;
    private OnFragmentInteractionListener mFragmentInteractionListener;

    private OnCourseSelectedListener mListener = new OnCourseSelectedListener() {
        @Override
        public void onCouseSelected(Courses selectedCourse, int position) {
            mSelectedCourse = selectedCourse;
            mSelectedPosition = position;
            mFragmentInteractionListener.onFragmentInteraction(selectedCourse);
        }
    };

    @Inject
    public CourseListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerAdapter = new RecyclerViewAdapter(new ArrayList<>(0), mListener);
        if (savedInstanceState != null) {
            mUseSavedState = true;
            mSelectedPosition = savedInstanceState.getInt(CourseListActivity.SELECTED_POSITION);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.takeView(this, mUseSavedState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();  //prevent leaking activity in
        // case presenter is orchestrating a long running task
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mFragmentInteractionListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course_list, container, false);

        ButterKnife.bind(this, view);
        // to improve performance as changes in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use linear layout manager for positioning of items in the list in list formation
        final RecyclerView.LayoutManager restaurantLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(restaurantLayoutManager);
        mRecyclerView.addItemDecoration(mDividerLine);


        // TODO scrolling not working
        mRecyclerView.scrollToPosition(mSelectedPosition);
        mPresenter.loadCourses(mUseSavedState);

        return view;

    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CourseListActivity.SELECTED_COURSE, mSelectedCourse);
        outState.putInt(CourseListActivity.SELECTED_POSITION, mSelectedPosition);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mSelectedCourse = savedInstanceState.getParcelable(CourseListActivity.SELECTED_COURSE);
            mSelectedPosition = savedInstanceState.getInt(CourseListActivity.SELECTED_POSITION);
        }
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showCourses(List<Courses> courseList) {
        if (mRecyclerView != null) {
            Log.d(TAG, "setupRecyclerView: ");
            mRecyclerAdapter = new RecyclerViewAdapter(courseList, mListener);
            mRecyclerView.setAdapter(mRecyclerAdapter);
            mRecyclerAdapter.notifyDataSetChanged();
        }
        if (courseList.size() < 1) {
            Snackbar.make(mRecyclerView, getString(R.string.no_course_found), Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }

    @Override
    public void showLoadingCourseError() {

    }

    @Override
    public void showNoCourse() {

    }

    public interface OnCourseSelectedListener {
        void onCouseSelected(Courses course, int position);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Courses selectedCourse);
    }
}
