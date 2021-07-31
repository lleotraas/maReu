package com.lamzone.mareu.view.meeting;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lamzone.mareu.R;
import com.lamzone.mareu.databinding.ActivityMeetingListBinding;
import com.lamzone.mareu.model.Meeting;

/**
 * Created by lleotraas on 02.
 */
public class MeetingViewHolder extends RecyclerView.ViewHolder {
    private final TextView mRoom;
    private final TextView mMember;
    private final ImageButton mDelete;
    private final ImageView mImage;
    private final TextView mInitial;

    public MeetingViewHolder(View itemView) {
        super(itemView);
        mRoom = itemView.findViewById(R.id.item_list_room_txt);
        mMember = itemView.findViewById(R.id.item_list_members_txt);
        mDelete = itemView.findViewById(R.id.item_list_delete_btn);
        mImage = itemView.findViewById(R.id.item_list_img);
        mInitial = itemView.findViewById(R.id.item_list_initial_txt);
    }

    public void bind(Meeting meeting){
        mImage.setColorFilter(meeting.getColor());
        mRoom.setText(String.format("Réunion %s - %sH%s - %s", meeting.getRoom(), meeting.getHour(), meeting.getMinute(), meeting.getName().toUpperCase()));
        mMember.setText(meeting.toString());
        mInitial.setText(String.format("%s", Character.toUpperCase(meeting.getName().charAt(0))));
    }

    public ImageButton getDelete() {
        return mDelete;
    }
}
