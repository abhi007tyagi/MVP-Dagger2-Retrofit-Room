package com.tyagiabhinav.udacitycourseviewer.ui.courseList;

import android.support.v7.widget.RecyclerView;
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

public class InstructorInfoRecyclerViewAdapter extends RecyclerView.Adapter<InstructorInfoRecyclerViewAdapter.ViewHolder> {

    private final List<Instructors> mInstructorsList;

//    @Inject
//    public Picasso picasso;

    public InstructorInfoRecyclerViewAdapter(List<Instructors> instructors) {
        this.mInstructorsList = instructors;
    }

    @Override
    public InstructorInfoRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.instructor_info_content, parent, false);
        return new InstructorInfoRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final InstructorInfoRecyclerViewAdapter.ViewHolder holder, int position) {

        Instructors instructors = mInstructorsList.get(position);
        holder.mItem = instructors;

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



        holder.name.setText(instructors.getName());
        holder.bio.setText(instructors.getBio());
        String imageUrl = instructors.getImage();

        // bind ui views with data
        if(!imageUrl.trim().isEmpty()) {
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_person_black_24dp)
                    .error(R.drawable.ic_person_black_24dp)
                    .into(holder.pic);
        }
    }

    @Override
    public int getItemCount() {
        return mInstructorsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;

        @BindView(R.id.instructorName)
        TextView name;

        @BindView(R.id.bio)
        TextView bio;

        @BindView(R.id.profilePic)
        ImageView pic;

        private Instructors mItem;

        private ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
        }
    }
}