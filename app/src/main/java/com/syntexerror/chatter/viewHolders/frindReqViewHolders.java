package com.syntexerror.chatter.viewHolders;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.syntexerror.chatter.R;

public class frindReqViewHolders extends RecyclerView.ViewHolder {
    View mview  ;
    public  Button accptBTn  , DeclineBtn;



    public frindReqViewHolders(@NonNull View itemView) {


        super(itemView);
        mview = itemView ;


    }
    public  void setDetails(Context context , String name , String pplInk)
    {
        // views
        // containerLayout = itemView.findViewById(R.id.container);
        TextView nameTv  = (TextView) mview.findViewById(R.id.nameOnFriendReq);
        ImageView pp = (ImageView) mview.findViewById(R.id.pponFriendreq);
        accptBTn = mview.findViewById(R.id.acptBtn);
        DeclineBtn = mview.findViewById(R.id.declineBtn) ;



        nameTv.setText(name);
        Glide.with(context).load(pplInk).diskCacheStrategy(DiskCacheStrategy.ALL).into(pp) ;




    }








}
