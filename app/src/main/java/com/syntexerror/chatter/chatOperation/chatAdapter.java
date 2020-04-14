package com.syntexerror.chatter.chatOperation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.syntexerror.chatter.R;

import java.util.List;

public class chatAdapter extends RecyclerView.Adapter<chatAdapter.myHolder> {

    List <chatMsgModel> chatList ;
    Context context ;
    private  static  final  int MSG_TYPE_RIGHT = 1 ;
    private  static  final  int MSG_TYPE_LEFT = 0 ;
    String uid = FirebaseAuth.getInstance().getUid();


    public  chatAdapter (Context context , List <chatMsgModel> chatList  )
    {
        this.context = context ;
        this.chatList =  chatList ;

    }
    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = null  ;

        if(viewType == MSG_TYPE_LEFT)
        {
             view  = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.row_for_left_chat ,  parent, false);
        }
        else
        {
             view  = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.row_for_right_chat ,  parent, false);
        }






        return  new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {

        String msg = chatList.get(position).getMsg() ;



        holder.msgView.setText(msg);




    }

    @Override
    public int getItemViewType(int position) {



       if( chatList.get(position).getUid().equals(uid))
       {

           return MSG_TYPE_RIGHT ;

       }
       else
        return  MSG_TYPE_LEFT ;

    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }





    class myHolder extends RecyclerView.ViewHolder
    {

      public  TextView msgView , date ;


        public myHolder(@NonNull View itemView) {
            super(itemView);


            msgView = itemView.findViewById(R.id.msgTv);
            date = itemView.findViewById(R.id.dateview) ;




        }



    }

}





