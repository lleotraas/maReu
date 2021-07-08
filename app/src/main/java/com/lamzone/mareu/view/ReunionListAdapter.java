package com.lamzone.mareu.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.lamzone.mareu.R;
import com.lamzone.mareu.injector.DependencyInjector;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.service.ReunionApiService;

import java.util.List;

/**
 * Created by lleotraas on 04.
 */
public class ReunionListAdapter extends RecyclerView.Adapter<ReunionViewHolder> {

    private List<Reunion> mReunions;
    private ReunionApiService mApiService;

    public ReunionListAdapter(List<Reunion> reunions) {
        mReunions = reunions;
    }

    @Override
    public ReunionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_list, parent, false);
        mApiService = DependencyInjector.getReunionApiService();
        return new ReunionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReunionViewHolder holder, int position) {
        holder.bind(mReunions.get(position));

        holder.getDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Reunion.class.getSimpleName(), "onClick: deleted reunion with name : " + mReunions.get(position).getName());
                mApiService.removeReunion(mReunions.get(position));
                notifyDataSetChanged();

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ReunionListAdapter.class.getSimpleName(), "onClick: detail launch with name : " + mReunions.get(position).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mReunions.size();
    }
}
