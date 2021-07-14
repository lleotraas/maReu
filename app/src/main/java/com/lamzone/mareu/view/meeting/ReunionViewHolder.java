package com.lamzone.mareu.view.meeting;

import android.graphics.Color;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lamzone.mareu.R;
import com.lamzone.mareu.injector.DependencyInjector;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.service.ReunionApiService;

import java.util.Random;

/**
 * Created by lleotraas on 02.
 */
public class ReunionViewHolder extends RecyclerView.ViewHolder {
    private TextView mRoom;
    private TextView mHour;
    private TextView mMinute;
    private TextView mName;
    private TextView mMember;
    private ImageButton mDelete;
    private ImageView mImage;

    public ReunionViewHolder(View itemView) {
        super(itemView);
        mRoom = itemView.findViewById(R.id.item_list_room_txt);
        mHour = itemView.findViewById(R.id.item_list_hour_txt);
        mMinute = itemView.findViewById(R.id.item_list_minute_txt);
        mName = itemView.findViewById(R.id.item_list_name_txt);
        mMember = itemView.findViewById(R.id.item_list_members_txt);
        mDelete = itemView.findViewById(R.id.item_list_delete_btn);
        mImage = itemView.findViewById(R.id.item_list_img);
    }

    public void bind(Reunion reunion){
        mImage.setColorFilter(reunion.getColor());
        mRoom.setText("Réunion " + reunion.getRoom());
        mHour.setText(" - " + reunion.getHour());
        mMinute.setText(reunion.getMinute() + " - ");
        mName.setText(reunion.getName());
        mMember.setText(reunion.toString());
    }



    public ImageButton getDelete() {
        return mDelete;
    }
}
