package com.syntexerror.chatter.chatOperation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.syntexerror.chatter.R;
import com.syntexerror.chatter.activty.myFriendList;
import com.syntexerror.chatter.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class chatPage extends AppCompatActivity {
    DatabaseReference mref  , histroyref;
    RecyclerView recyclerView ;
    LinearLayoutManager llm ;
    List<chatMsgModel> loadedChat;
    chatAdapter chatAdapter ;
    EditText chatINput ;
    ImageView sendBtn  ;
    String frindShipId  , friendUserID ,friendUserName  , friendPP ;
    String uid ;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);
        recyclerView = findViewById(R.id.list) ;
        chatINput = findViewById(R.id.message_input);
        sendBtn = findViewById(R.id.message_send_btn) ;


        uid = FirebaseAuth.getInstance().getUid()  ;




//        o.putExtra("frindShipId" , frindShipId) ;
//        o.putExtra("friendUserID" , friendUserID) ;
//        o.putExtra("Firend_Username" , friendUserName) ;
//        o.putExtra("Firend_pp" , friendPP) ;

        Intent intent = getIntent();

        friendUserID =  intent.getStringExtra("friendUserID") ;
        frindShipId =  intent.getStringExtra("frindShipId") ;

        getSupportActionBar().setTitle(friendUserName);

        llm = new LinearLayoutManager(getApplicationContext());
        llm.setStackFromEnd(true);


        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(llm) ;

        mref = FirebaseDatabase.getInstance().getReference("chat_repo") ;
        histroyref = FirebaseDatabase.getInstance().getReference("chatHistory");

        if(frindShipId.isEmpty())
        {


            AlertDialog dialog =  new AlertDialog.Builder(getApplicationContext()).create();
            dialog.setTitle("Something went Wrong !!");
            dialog.show();

        }
        else
        {
            mref = FirebaseDatabase.getInstance().getReference("chat_repo").child(frindShipId);

        }








        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             String msg =    chatINput.getText().toString() ;


             if(msg.isEmpty())
             {

             }
             else {

                 sendTheMsg(msg) ;



             }

            }
        });






        downloadMsg() ;
    }

    private void sendTheMsg(String msg) {


        String msg_ID  = mref.push().getKey() ;
       // String msg , msg_id , uid , time   ;

        chatMsgModel msgModel = new chatMsgModel( msg  , msg_ID , uid ,System.currentTimeMillis()/1000 ) ;

        mref.child(msg_ID).setValue(msgModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                chatINput.setText("");
                writeChatHistory(uid  , friendUserID , frindShipId);
               // chatINput.getText().clear();

            }
        }) ;


    }

    @Override
    protected void onStart() {
        super.onStart();
        downloadTheFriendData() ;

    }

    private void downloadTheFriendData() {

        DatabaseReference fref = FirebaseDatabase.getInstance().getReference("users");
        fref.keepSynced(true);

        fref.child(friendUserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                UserModel model = dataSnapshot.getValue(UserModel.class);

                friendUserName = model.getUsername()   ;

                getSupportActionBar().setTitle("Chatting With " + friendUserName);

                friendPP = model.getProfileLink() ;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void downloadMsg() {

        loadedChat  = new ArrayList<>();

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    loadedChat.clear();

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    chatMsgModel chat = ds.getValue(chatMsgModel.class) ;

                   // Log.d("MSGESDSS" , chat.getMsg()  );
                    loadedChat.add(chat) ;
                }
                //
                chatAdapter = new chatAdapter( getApplicationContext() ,loadedChat ) ;
                chatAdapter.notifyDataSetChanged();

                // set adapter
                recyclerView.setAdapter(chatAdapter) ;



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {



            }
        }) ;


    }

    private void   writeChatHistory(String  uid  , String  friendUserID  ,  String FrienshipID)
    {
        // user1  , user2 = uid   , friendUserID
        // FrienshipID  = FrienshipID
        // long  timestamp = lastMsg of  the friends

        chatHistoryModel historyModel = new chatHistoryModel(uid , friendUserID ,  FrienshipID , System.currentTimeMillis()/1000) ;

        histroyref.child(FrienshipID).setValue(historyModel) ;




    }





}
