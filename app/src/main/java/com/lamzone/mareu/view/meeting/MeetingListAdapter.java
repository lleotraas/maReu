package com.lamzone.mareu.view.meeting;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.recyclerview.widget.RecyclerView;

import com.lamzone.mareu.R;
import com.lamzone.mareu.injector.DependencyInjector;
import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.service.MeetingApiService;
import com.lamzone.mareu.view.detail.MeetingDetailActivity;

import java.util.List;

/**
 * Created by lleotraas on 04.
 */
public class MeetingListAdapter extends RecyclerView.Adapter<MeetingViewHolder> implements Filterable {

    private List<Meeting> mMeetings;
    private MeetingApiService mApiService;

    public MeetingListAdapter(List<Meeting> meetings) {
        mMeetings = meetings;
    }


    @Override
    public MeetingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_meeting_list, parent, false);
        mApiService = DependencyInjector.getMeetingApiService();
        return new MeetingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MeetingViewHolder holder, int position) {
        holder.bind(mMeetings.get(position));
        holder.getDelete().setOnClickListener(v -> {
            mApiService.removeMeetingListFiltered(mMeetings.get(position));
            mApiService.getMeeting().remove(mMeetings.get(position));
            notifyDataSetChanged();
        });

        holder.itemView.setOnClickListener(v -> {
            Meeting extraMeeting = mMeetings.get(holder.getAdapterPosition());
            Intent detailActivity = new Intent(v.getContext(), MeetingDetailActivity.class);
            detailActivity.putExtra("reunion", extraMeeting);
            v.getContext().startActivity(detailActivity);
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private Filter mFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            results.values = mApiService.meetingFilter(constraint);
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mApiService.getMeeting().clear();
            mApiService.getMeeting().addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
