package com.syntexerror.chatter.chatOperation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.syntexerror.chatter.R;
import com.syntexerror.chatter.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class chatPage extends AppCompatActivity {
    DatabaseReference mref ;
    RecyclerView recyclerView ;
    LinearLayoutManager llm ;
    List<TestModel> loadedChat;
    chatAdapter chatAdapter ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);
        recyclerView = findViewById(R.id.list) ;

        llm = new LinearLayoutManager(getApplicationContext());
        llm.setStackFromEnd(true);


        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(llm) ;



        mref = FirebaseDatabase.getInstance().getReference("chat_repo");












        createUser();

        downloadMsg() ;
    }

    private void downloadMsg() {

        loadedChat  = new ArrayList<>();

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    loadedChat.clear();

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    TestModel chat = ds.getValue(TestModel.class) ;

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


    private void writeToDb(String uid ) {


        String key = mref.push().getKey() ;

        TestModel  model = new TestModel(uid , "hey !!! ME CO-VID 19" , "13213ff21313") ;

        mref.child(key).setValue(model) ;






    }

    private  void createUser() {

        DatabaseReference  mreff = FirebaseDatabase.getInstance().getReference("users");

        String key = mref.push().getKey() ;
       // String username  , name , uid , profileLink , joinTimeStamp , isOnline   ;
        UserModel model = new UserModel("test" , "test user" , key,"https://picsum.photos/200/300"   , System.currentTimeMillis()/1000 , "false") ;

        mreff.child(key).setValue(model) ;

    }
}
