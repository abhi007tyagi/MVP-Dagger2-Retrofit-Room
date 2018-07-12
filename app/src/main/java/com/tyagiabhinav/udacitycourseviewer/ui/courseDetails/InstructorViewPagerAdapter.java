package com.tyagiabhinav.udacitycourseviewer.ui.courseDetails;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tyagiabhinav.udacitycourseviewer.R;
import com.tyagiabhinav.udacitycourseviewer.model.pojo.Instructors;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstructorViewPagerAdapter extends PagerAdapter {

    public static final String TAG = InstructorViewPagerAdapter.class.getSimpleName();

    @BindView(R.id.instructorName)
    TextView name;

    @BindView(R.id.bio)
    TextView bio;

    @BindView(R.id.profilePic)
    ImageView pic;

    private Context mContext;
    private List<Instructors> mInstructorsList;

    InstructorViewPagerAdapter(Context mContext, List<Instructors> instructors) {
        this.mContext = mContext;
        this.mInstructorsList = instructors;
    }

    @Override
    public int getCount() {
        return mInstructorsList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.d(TAG, "instantiateItem: "+position);
        View instructorView = LayoutInflater.from(mContext).inflate(R.layout.instructor_info_content, container, false);

        ButterKnife.bind(this, instructorView);

        Instructors instructors = mInstructorsList.get(position);

        // bind ui views with data
        name.setText((instructors.getName().trim().isEmpty()) ? mContext.getString(R.string.no_name_msg) : instructors.getName());
        bio.setText((instructors.getBio().trim().isEmpty()) ? mContext.getString(R.string.no_bio_msg) : instructors.getBio());
        String imageUrl = instructors.getImage();

        if (!imageUrl.trim().isEmpty()) {
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_person_black_24dp)
                    .error(R.drawable.ic_person_black_24dp)
                    .into(pic);
        }
        container.addView(instructorView);
        Log.i(TAG, "instantiateItem() [position: " + position + "]" + " childCount:" + container.getChildCount());
        return instructorView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        Log.i(TAG, "destroyItem() [position: " + position + "]" + " childCount:" + container.getChildCount());
    }
}
