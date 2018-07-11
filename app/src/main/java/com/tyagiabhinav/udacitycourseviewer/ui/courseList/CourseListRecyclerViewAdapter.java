package com.tyagiabhinav.udacitycourseviewer.ui.courseList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tyagiabhinav.udacitycourseviewer.R;
import com.tyagiabhinav.udacitycourseviewer.model.pojo.Courses;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by abhinavtyagi on 14/02/17.
 */

public class CourseListRecyclerViewAdapter extends RecyclerView.Adapter<CourseListRecyclerViewAdapter.ViewHolder> {

    private final List<Courses> mCourseList;
    private CourseListActivity.OnCourseSelectedListener mListener;

//    @Inject
//    public Picasso picasso;

    public CourseListRecyclerViewAdapter(List<Courses> courses, CourseListActivity.OnCourseSelectedListener listener) {
        this.mCourseList = courses;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Courses courses = mCourseList.get(position);
        holder.mItem = courses;

        // set auto select for first restaurant in case of two pane mode
//        if (mTwoPane && position == 0) {
//            Bundle arguments = new Bundle();
//            arguments.putParcelable(Constants.SELECTED_RESTAURANT, holder.mItem);
//            arguments.putBoolean(Constants.TWO_PANE, false);
//
//            RestaurantDetailFragment fragment = new RestaurantDetailFragment();
//            fragment.setArguments(arguments);
//            ((AppCompatActivity) mContext).getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.restaurant_detail_container, fragment)
//                    .commit();
//        }

//        String imageUrl = courses.getBanner_image();

        // bind ui views with data
//        if(!imageUrl.trim().isEmpty()) {
//            Picasso.get()
//                    .load(imageUrl)
//                    .placeholder(R.drawable.ic_library_books_grey_24dp)
//                    .error(R.drawable.ic_library_books_grey_24dp)
//                    .resize(145,30)
//                    .into(holder.banner);
//        }

        holder.title.setText(courses.getTitle());
        holder.shortSummary.setText(courses.getShort_summary());
        holder.level.setText(courses.getLevel());
        holder.duration.setText(String.format(Locale.getDefault(),"%d %s",
                courses.getExpected_duration(), courses.getExpected_duration_unit()));


        // item click listener
        holder.mView.setOnClickListener((v) -> handleOnClickListener(v, holder, position));
    }

    @Override
    public int getItemCount() {
        return mCourseList.size();
    }

    private void handleOnClickListener(View v, ViewHolder holder, int position) {

        mListener.onCouseSelected(holder.mItem, position);



//        Bundle arguments = new Bundle();
//        arguments.putParcelable(Constants.SELECTED_RESTAURANT, holder.mItem);
//        if (mTwoPane) {
//            RestaurantDetailFragment fragment = new RestaurantDetailFragment();
//            arguments.putBoolean(Constants.TWO_PANE, true);
//            fragment.setArguments(arguments);
//            ((AppCompatActivity) mContext).getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.restaurant_detail_container, fragment)
//                    .commit();
//        } else {
//            Context context = v.getContext();
//            Intent intent = new Intent(context, RestaurantDetailActivity.class);
//            arguments.putBoolean(Constants.TWO_PANE, false);
//            intent.putExtras(arguments);
//            context.startActivity(intent);
//        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.summary)
        TextView shortSummary;

        @BindView(R.id.level)
        TextView level;

        @BindView(R.id.duration)
        TextView duration;

        private Courses mItem;

        private ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
        }
    }
}