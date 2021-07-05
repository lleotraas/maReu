package com.lamzone.mareu.view;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lamzone.mareu.R;
import com.lamzone.mareu.model.Reunion;

/**
 * Created by lleotraas on 02.
 */
public class ReunionViewHolder extends RecyclerView.ViewHolder {
    private ImageView mImage;
    private TextView mRoom;
    private TextView mHour;
    private TextView mName;
    private TextView mMember;
    private ImageButton mDelete;

    public ReunionViewHolder(View itemView) {
        super(itemView);
        mImage = itemView.findViewById(R.id.item_list_img);
        mRoom = itemView.findViewById(R.id.item_list_room_txt);
        mHour = itemView.findViewById(R.id.item_list_hour_txt);
        mName = itemView.findViewById(R.id.item_list_name_txt);
        mMember = itemView.findViewById(R.id.item_list_members_txt);
        mDelete = itemView.findViewById(R.id.item_list_delete_btn);
    }

    public void bind(Reunion reunion){
        mRoom.setText(reunion.getRoom());
        mHour.setText(" - " + reunion.getHour() + " - ");
        mName.setText(reunion.getName());
    }
}
