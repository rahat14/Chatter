package com.syntexerror.chatter.viewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class frindReqViewHolders extends RecyclerView.ViewHolder {
    View mview  ;
    TextView username  ;


    public frindReqViewHolders(@NonNull View itemView) {


        super(itemView);
        mview = itemView ;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mClickListener.onItemClick(view , getAdapterPosition());
            }
        });

    }

    private  static  frindReqViewHolders.ClickListener  mClickListener;


    public  interface  ClickListener
    {
        void onItemClick( View view , int position ) ;

    }


    public  static String setOnClickListener(frindReqViewHolders.ClickListener clickListener)
    {

        mClickListener = clickListener ;
        return null;
    }
}
