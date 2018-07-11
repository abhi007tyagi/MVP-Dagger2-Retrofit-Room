package com.tyagiabhinav.udacitycourseviewer.ui.courseList;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.tyagiabhinav.udacitycourseviewer.R;
import com.tyagiabhinav.udacitycourseviewer.model.pojo.Courses;
import com.tyagiabhinav.udacitycourseviewer.ui.courseDetails.CourseDetailActivity;
import com.tyagiabhinav.udacitycourseviewer.ui.courseDetails.CourseDetailFragment;
import com.tyagiabhinav.udacitycourseviewer.utils.uiUtil.ActivityUtils;
import com.tyagiabhinav.udacitycourseviewer.utils.uiUtil.Constants;
import com.tyagiabhinav.udacitycourseviewer.utils.uiUtil.DividerLine;

import java.util.ArrayList;
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
    Toolbar toolbar;

    @BindView(R.id.courseListRecyclerView)
    RecyclerView mRecyclerView;

    private boolean mTwoPane = false;
    private List<Courses> mStateSavedList;
    private CourseListRecyclerViewAdapter mRecyclerAdapter;


    private CourseListActivity.OnCourseSelectedListener mCourseClickListener = new CourseListActivity.OnCourseSelectedListener() {
        @Override
        public void onCouseSelected(Courses selectedCourse, int position) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(Constants.SELECTED_COURSE, selectedCourse);
            if (mTwoPane) {
//                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.course_detail_container);
//                if (fragment == null) {
//                    // Get the fragment from dagger
//                    fragment = mCourseDetailFragmentProvider.get();
//                }
                // Get the fragment from dagger
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
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        if (findViewById(R.id.course_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w820dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
            Log.d(TAG, "onCreate: This is TWO PANE *********************");
        }

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        // to improve performance as changes in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use linear layout manager for positioning of items in the list in list formation
        final RecyclerView.LayoutManager courseLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(courseLayoutManager);
        mRecyclerView.addItemDecoration(mDividerLine);

        if (savedInstanceState != null) {
            Log.d(TAG, "onCreate: using saved state list");
            mStateSavedList = savedInstanceState.getParcelableArrayList(Constants.COURSE_LIST);
            showCourses(mStateSavedList);
        } else {
            Log.d(TAG, "onCreate: fresh load");
            mCourseListPresenter.loadCourses();
        }

//        mfragment = getSupportFragmentManager().findFragmentById(R.id.contentFrame);
//        if (savedInstanceState != null) {
//            //Restore the fragment's instance
//            mfragment = getSupportFragmentManager().getFragment(savedInstanceState, "savedFragment");
//        }
//        if (mfragment == null) {
//            if (isDetailScreen) {
//                mfragment = mCourseDetailFragmentProvider.get();
//            } else {
//                mfragment = mCourseListFragmentProvider.get();
//            }
//        } else if (mfragment instanceof CourseListFragment) {
//            // Get the fragment from dagger
//            mfragment = mCourseListFragmentProvider.get();
//        } else if (mfragment instanceof CourseDetailFragment) {
//            // Get the fragment from dagger
//            mfragment = mCourseDetailFragmentProvider.get();
//        }
//
//        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mfragment, R.id.contentFrame);
    }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//
//        // Checks the orientation of the screen
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
//        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
//            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        mCourseListPresenter.takeView(this);
    }


    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        mCourseListPresenter.dropView();  //prevent leaking activity in
        // case presenter is orchestrating a long running task
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Constants.COURSE_LIST, new ArrayList<>(mStateSavedList));
    }

//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        mStateSavedList = savedInstanceState.getParcelableArrayList(Constants.COURSE_LIST);
//    }

    //
//    @Override
//    public void onFragmentInteraction(Courses selectedCourse) {
//        Log.d(TAG, "onFragmentInteraction");
//        // Get the fragment from dagger
//        CourseDetailFragment courseDetailFragment = mCourseDetailFragmentProvider.get();
//
//        Bundle bundle = new Bundle();
//        bundle.putParcelable(SELECTED_COURSE, selectedCourse);
//        courseDetailFragment.setArguments(bundle);
//
//        isDetailScreen = true;
//        ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(), courseDetailFragment, R.id.contentFrame);
//    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showCourses(List<Courses> courseList) {
        Log.d(TAG, "showCourses");
        if (courseList.size() < 1) {
            Snackbar.make(mRecyclerView, getString(R.string.no_course_found), Snackbar.LENGTH_LONG).setAction("Action", null).show();
        } else if (mRecyclerView != null) {
            Log.d(TAG, "setupRecyclerView: ");
            mStateSavedList = courseList;
            mRecyclerAdapter = new CourseListRecyclerViewAdapter(courseList, mCourseClickListener);
            mRecyclerView.setAdapter(mRecyclerAdapter);
            mRecyclerAdapter.notifyDataSetChanged();
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
}
