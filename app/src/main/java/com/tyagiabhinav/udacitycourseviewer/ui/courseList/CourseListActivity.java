package com.tyagiabhinav.udacitycourseviewer.ui.courseList;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tyagiabhinav.udacitycourseviewer.R;
import com.tyagiabhinav.udacitycourseviewer.model.pojo.Courses;
import com.tyagiabhinav.udacitycourseviewer.ui.courseDetails.CourseDetailActivity;
import com.tyagiabhinav.udacitycourseviewer.ui.courseDetails.CourseDetailFragment;
import com.tyagiabhinav.udacitycourseviewer.utils.uiUtil.ActivityUtils;
import com.tyagiabhinav.udacitycourseviewer.utils.uiUtil.Constants;
import com.tyagiabhinav.udacitycourseviewer.utils.uiUtil.DividerLine;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class CourseListActivity extends DaggerAppCompatActivity implements CourseListContract.View {

    public static final String TAG = CourseListActivity.class.getSimpleName();

    @Inject
    CourseListPresenter mCourseListPresenter;

    @Inject
    DividerLine mDividerLine;

    @Inject
    Lazy<CourseDetailFragment> mCourseDetailFragmentProvider;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.courseListRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @BindView(R.id.noCourseAvailable)
    TextView mNoCourseFound;

    private boolean mTwoPane = false;

    private boolean isActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        if (findViewById(R.id.course_detail_container) != null) {
            mTwoPane = true;
            Log.d(TAG, "onCreate: This is TWO PANE *********************");
        }

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mToolbar.setTitle(getTitle());

        // to improve performance as changes in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use linear layout manager for positioning of items in the list in list formation
        final RecyclerView.LayoutManager courseLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(courseLayoutManager);
        mRecyclerView.addItemDecoration(mDividerLine);

        mCourseListPresenter.takeView(this);

        if (savedInstanceState != null) {
            mCourseListPresenter.loadCourses(savedInstanceState.getBoolean(Constants.SAVE_COURSE_LIST));
        } else {
            mCourseListPresenter.loadCourses(false);
        }
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        mCourseListPresenter.takeView(this);
        isActive = true; // used by unit test
    }


    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        mCourseListPresenter.dropView();  //prevent leaking activity in case presenter is orchestrating a long running task
        isActive = false; // used by unit test
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putBoolean(Constants.SAVE_COURSE_LIST, true);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showCourses(List<Courses> courseList) {
        Log.d(TAG, "showCourses");
        if (courseList.size() < 1) {
            mNoCourseFound.setVisibility(View.VISIBLE);
            Snackbar.make(mRecyclerView, getString(R.string.no_course_found), Snackbar.LENGTH_LONG).setAction("Action", null).show();
        } else if (mRecyclerView != null) {
            mNoCourseFound.setVisibility(View.INVISIBLE);
            CourseListRecyclerViewAdapter mRecyclerAdapter = new CourseListRecyclerViewAdapter(courseList, mCourseListPresenter);
            mRecyclerView.setAdapter(mRecyclerAdapter);
            mRecyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCourseSelected(Courses selectedCourse, int selectedPosition) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(Constants.SELECTED_COURSE, selectedCourse);
        if (mTwoPane) {
            CourseDetailFragment fragment = new CourseDetailFragment();
            arguments.putBoolean(Constants.TWO_PANE, true);
            fragment.setArguments(arguments);
            ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(), fragment, R.id.course_detail_container);
        } else {
            Intent intent = new Intent(CourseListActivity.this, CourseDetailActivity.class);
            arguments.putBoolean(Constants.TWO_PANE, false);
            intent.putExtras(arguments);
            startActivity(intent);
        }
        Log.d(TAG, "onCouseSelected: " + selectedCourse.getTitle());
    }

    @Override
    public void showNoCourse() {
        mNoCourseFound.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCourseLoadError() {
        mNoCourseFound.setText(R.string.error_fetching_data);
        mNoCourseFound.setVisibility(View.VISIBLE);
    }

    // used in unit test
    @Override
    public boolean isActive() {
        return isActive;
    }

}
