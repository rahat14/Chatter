package com.syntexerror.chatter.activty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.syntexerror.chatter.R;
import com.syntexerror.chatter.models.FriendRequestModel;
import com.syntexerror.chatter.models.UserModel;
import com.syntexerror.chatter.viewHolders.viewholderForAllUser;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class AllUserList extends AppCompatActivity {

    RecyclerView recyclerView ;
    LinearLayoutManager llm ;
    DatabaseReference userRef ;
    FirebaseRecyclerAdapter<UserModel, viewholderForAllUser> firebaseRecyclerAdapter ;
    FirebaseRecyclerOptions<UserModel> options ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user_list);

        userRef = FirebaseDatabase.getInstance().getReference("users");
        userRef.keepSynced(true); // cahce enabled

        // views

        recyclerView = findViewById(R.id.list) ;
        llm = new LinearLayoutManager(AllUserList.this);
        recyclerView.setLayoutManager(llm) ;



        loadData() ;
    }

    private void loadData() {


        Query query = userRef.orderByChild("TimeStamp");

        options = new FirebaseRecyclerOptions.Builder<UserModel>()
                .setQuery( query , UserModel.class)
                .build() ;


        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<UserModel, viewholderForAllUser>(options) {
            @Override
            protected void onBindViewHolder(@NonNull viewholderForAllUser viewholderForAllUser, int i, @NonNull UserModel userModel) {

                viewholderForAllUser.setDetails( getApplicationContext() , userModel.getUsername() , userModel.getProfileLink());

                viewholderForAllUser.setOnClickListener(new viewholderForAllUser.ClickListener() {
                    @Override
                    public void onItemClick(View view, final  int position) {


                        String targetuid = getItem(position).getUid() ;

                        sendFriendReq(targetuid) ;



                    }
                });


            }

            @NonNull
            @Override
            public viewholderForAllUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_for_user_list, parent, false);

                 viewholderForAllUser viewholder = new viewholderForAllUser(view) ;




                return viewholder;
            }


        } ;

        recyclerView.setLayoutManager(llm) ;
        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter) ;

    }

    private void sendFriendReq(final String targetuid) {

        // TODO uid  manual
        final String Own_uid = FirebaseAuth.getInstance().getUid() ;

        DatabaseReference  reqRef = FirebaseDatabase.getInstance().getReference("users")
                .child(targetuid)
                .child("reqRepo");

   final String PostKey = reqRef.push().getKey() ;

        //   String  requsetedFriendUsername, requsetedFriendUid, timestamp,  requsetedFriendProfileLink, postId;
        FriendRequestModel model = new FriendRequestModel( "test user" ,Own_uid
        ,  System.currentTimeMillis() /1000 + "" ,
                "https://picsum.photos/200/300" , PostKey  ) ;



        reqRef.child(PostKey).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

//                Toast.makeText(getApplicationContext() ,
//                        "Req Sent !!!" , Toast.LENGTH_SHORT).show();
                DatabaseReference  OwnRef = FirebaseDatabase.getInstance().getReference("users")
                        .child(Own_uid)
                        .child("sentReqRepo");

                HashMap <String, String> map = new HashMap<String, String>();

                map.put("sendReqUID" ,targetuid ) ;
                map.put("status"  , "Pending") ;

                OwnRef.child(PostKey).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Toast.makeText(getApplicationContext() , "Req Sent !!!" , Toast.LENGTH_SHORT).show();
                    }
                }) ;




            }
        }) ;








    }
}
