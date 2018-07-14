package com.tyagiabhinav.udacitycourseviewer.ui.courseDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tyagiabhinav.udacitycourseviewer.R;
import com.tyagiabhinav.udacitycourseviewer.model.pojo.Courses;
import com.tyagiabhinav.udacitycourseviewer.utils.uiUtil.Constants;
import com.tyagiabhinav.udacitycourseviewer.utils.uiUtil.DividerLine;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;


public class CourseDetailFragment extends DaggerFragment implements ViewPager.OnPageChangeListener {

    public static final String TAG = CourseDetailFragment.class.getSimpleName();

    @Inject
    DividerLine mDividerLine;

    @Inject
    public CourseDetailFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.summary)
    TextView mSummary;

    @BindView(R.id.level)
    TextView mLevel;

    @BindView(R.id.duration)
    TextView mDuration;

    @BindView(R.id.instructorHeading)
    TextView mInstructorHeading;

    @BindView(R.id.pageIndicator)
    LinearLayout mPagerIndicator;

//    @BindView(R.id.instructorsListRecyclerView)
//    RecyclerView mRecyclerView;

    @BindView(R.id.instructorsPager)
    ViewPager mInstructorsPager;

    private Courses mSelectedCourse;
    //    private boolean mTwoPane;
    private ImageView[] dots;
    private int dotsCount;
    private InstructorViewPagerAdapter mAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mSelectedCourse = savedInstanceState.getParcelable(Constants.SELECTED_COURSE);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        // get arguments passed from activity
        if (getArguments().containsKey(Constants.SELECTED_COURSE)) {
            mSelectedCourse = getArguments().getParcelable(Constants.SELECTED_COURSE);
//            mTwoPane = getArguments().getBoolean(Constants.TWO_PANE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course_details, container, false);

        ButterKnife.bind(this, view);

        mTitle.setText((mSelectedCourse.getTitle().trim().isEmpty()) ? getString(R.string.no_title_msg) : mSelectedCourse.getTitle());
        mLevel.setText((mSelectedCourse.getLevel().trim().isEmpty()) ? getString(R.string.no_level_msg) : mSelectedCourse.getLevel());
        mDuration.setText((mSelectedCourse.getExpected_duration() == 0 && mSelectedCourse.getExpected_duration_unit().trim().isEmpty()) ? getString(R.string.no_duration_msg) : String.format(Locale.getDefault(), "%d %s",
                mSelectedCourse.getExpected_duration(), mSelectedCourse.getExpected_duration_unit()));

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            mSummary.setText((mSelectedCourse.getSummary().trim().isEmpty()) ? getString(R.string.no_summary_msg) : Html.fromHtml(mSelectedCourse.getSummary(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            mSummary.setText((mSelectedCourse.getSummary().trim().isEmpty()) ? getString(R.string.no_summary_msg) : Html.fromHtml(mSelectedCourse.getSummary()));
        }
        mSummary.setMovementMethod(LinkMovementMethod.getInstance());

        int instructorsSize = mSelectedCourse.getInstructors().size();
        if (instructorsSize > 0) {
            mAdapter = new InstructorViewPagerAdapter(getActivity(), mSelectedCourse.getInstructors());
            mInstructorsPager.setAdapter(mAdapter);
            mInstructorsPager.setCurrentItem(0);
            mInstructorsPager.setOnPageChangeListener(this);
            if (instructorsSize == 1) {
                mInstructorHeading.setText(getString(R.string.one_course_instructors_msg));
                mPagerIndicator.setVisibility(View.GONE);
            } else {
                mPagerIndicator.setVisibility(View.VISIBLE);
                setUiPageViewController();
            }
        } else {
            mInstructorHeading.setText(getString(R.string.no_course_instructors_msg));
        }
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.white_dot));
        }
        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.grey_dot));
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.white_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(11, 7, 11, 7);
            mPagerIndicator.addView(dots[i], params);
        }
        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.grey_dot));
    }
}
