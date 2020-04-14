package com.syntexerror.chatter.viewHolders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.syntexerror.chatter.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class chatListingViewholdeers  extends RecyclerView.ViewHolder {

    View mView;
    public TextView nameTv , lastMsg ;
    public CircleImageView pp ;
     public  ConstraintLayout  container ;




    public chatListingViewholdeers(@NonNull View itemView) {
        super(itemView);
        mView = itemView;



        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });

    }

    public  void setNameTv( String name )
    {
        nameTv  = (TextView) mView.findViewById(R.id.display_name);
        nameTv.setText(name);
    }
    public  void setPp(String link , Context context )
    {
        pp =  mView.findViewById(R.id.profile_image);

        Glide.with(context).load(link).diskCacheStrategy(DiskCacheStrategy.ALL).into(pp) ;
    }



    public  void setDetails(Context context , String name , String pplInk )
    {
        // views
        // containerLayout = itemView.findViewById(R.id.container);
        nameTv  = (TextView) mView.findViewById(R.id.display_name);
        pp =  mView.findViewById(R.id.profile_image);
        lastMsg = (TextView) mView.findViewById(R.id.lastMsg) ;
        lastMsg.setText("Example of last Msg");
        container = mView.findViewById(R.id.full_layout) ;

        nameTv.setText(name);
        Glide.with(context).load(pplInk).diskCacheStrategy(DiskCacheStrategy.ALL).into(pp) ;




    }


    private static chatListingViewholdeers.ClickListener mClickListener;


    public interface ClickListener {
        void onItemClick(View view, int position);

    }


    public  void setOnClickListener(chatListingViewholdeers.ClickListener clickListener) {

        mClickListener = clickListener;

    }
}