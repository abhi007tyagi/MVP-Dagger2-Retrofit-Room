package com.tyagiabhinav.udacitycourseviewer.ui.courseList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tyagiabhinav.udacitycourseviewer.R;
import com.tyagiabhinav.udacitycourseviewer.model.pojo.Courses;
import com.tyagiabhinav.udacitycourseviewer.utils.uiUtil.DividerLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;


public class CourseDetailFragment extends DaggerFragment implements CourseListContract.View {

    public static final String TAG = CourseListFragment.class.getSimpleName();

    private boolean mUseSavedState = false;
    private Courses mSelectedCourse;

    @Inject
    CourseListContract.Presenter mPresenter;

    @Inject
    DividerLine mDividerLine;

    @Inject
    public CourseDetailFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.summary)
    TextView summary;

    @BindView(R.id.level)
    TextView level;

    @BindView(R.id.duration)
    TextView duration;

    @BindView(R.id.instructorsList)
    RecyclerView mRecyclerView;

    private InstructorInfoRecyclerViewAdapter mRecyclerAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerAdapter = new InstructorInfoRecyclerViewAdapter(new ArrayList<>(0));

        if (getArguments() != null) {
            mSelectedCourse = getArguments().getParcelable(CourseListActivity.SELECTED_COURSE);
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course_details, container, false);

        ButterKnife.bind(this, view);

        title.setText(mSelectedCourse.getTitle());
        level.setText(mSelectedCourse.getLevel());
        duration.setText(String.format(Locale.getDefault(),"%d %s",
                mSelectedCourse.getExpected_duration(), mSelectedCourse.getExpected_duration_unit()));

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            summary.setText(Html.fromHtml(mSelectedCourse.getSummary(),Html.FROM_HTML_MODE_LEGACY));
        } else {
            summary.setText(Html.fromHtml(mSelectedCourse.getSummary()));
        }
        summary.setMovementMethod(LinkMovementMethod.getInstance());

        // to improve performance as changes in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use linear layout manager for positioning of items in the list in list formation
        final RecyclerView.LayoutManager instructorLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(instructorLayoutManager);
        mRecyclerView.addItemDecoration(mDividerLine);

        if (mRecyclerView != null) {
            Log.d(TAG, "setupRecyclerView: ");
            mRecyclerAdapter = new InstructorInfoRecyclerViewAdapter(mSelectedCourse.getInstructors());
            mRecyclerView.setAdapter(mRecyclerAdapter);
            mRecyclerAdapter.notifyDataSetChanged();
        }
        if (mSelectedCourse.getInstructors().size() < 1) {
//            Snackbar.make(mRecyclerView, getString(R.string.no_course_found), Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }

        return view;

    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CourseListActivity.SELECTED_COURSE, mSelectedCourse);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mSelectedCourse = savedInstanceState.getParcelable(CourseListActivity.SELECTED_COURSE);
        }
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

}
