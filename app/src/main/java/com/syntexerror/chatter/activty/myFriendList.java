package com.syntexerror.chatter.activty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.syntexerror.chatter.R;
import com.syntexerror.chatter.chatOperation.chatPage;
import com.syntexerror.chatter.models.UserModel;
import com.syntexerror.chatter.models.friendModel;
import com.syntexerror.chatter.viewHolders.viewholderForAllUser;

public class myFriendList extends AppCompatActivity {
    RecyclerView recyclerView ;
    LinearLayoutManager llm ;
    DatabaseReference userRef  , fref ;
    FirebaseRecyclerAdapter<friendModel, viewholderForAllUser> firebaseRecyclerAdapter ;
    FirebaseRecyclerOptions<friendModel> options ;
    String friendUserName, friendPP ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friend_list);
        userRef = FirebaseDatabase.getInstance().getReference("users")
                .child(FirebaseAuth.getInstance().getUid())
        .child("friendList");
        userRef.keepSynced(true);


        fref = FirebaseDatabase.getInstance().getReference("users");
        fref.keepSynced(true);

        // views

        recyclerView = findViewById(R.id.listFriend) ;
        llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm) ;



        loadData() ;

    }

    private void loadData() {

        options = new FirebaseRecyclerOptions.Builder<friendModel>()
                .setQuery( userRef , friendModel.class)
                .build() ;


        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<friendModel, viewholderForAllUser>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final viewholderForAllUser viewholderForAllUser, final int i, @NonNull friendModel userModel) {

                viewholderForAllUser.setDetails( getApplicationContext() , " ", " ");

                // load user data
                String frindShipId = getItem(i).getFriendShipID() ;
                final String friendUserID =    getItem(i).getFriendUserID() ;



                fref.child(friendUserID).addListenerForSingleValueEvent(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                     UserModel model = dataSnapshot.getValue(UserModel.class);

                        friendUserName = model.getUsername()   ;
                        friendPP = model.getProfileLink() ;





                     viewholderForAllUser.nameTv.setText(model.getName());
                     viewholderForAllUser.setPp(model.getProfileLink(), myFriendList.this);


                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError databaseError) {

                 }
                });




                viewholderForAllUser.setOnClickListener(new viewholderForAllUser.ClickListener() {
                    @Override
                    public void onItemClick(View view, final  int position) {

                        // load user data
                        String frindShipId = getItem(position).getFriendShipID() ;
                         String friendUserID =    getItem(position).getFriendUserID() ;

                        Intent o  = new Intent(getApplicationContext() , chatPage.class );

                        o.putExtra("frindShipId" , frindShipId) ;
                        o.putExtra("friendUserID" , friendUserID) ;
                        startActivity(o);




                    }
                });


            }

            @NonNull
            @Override
            public viewholderForAllUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_for_user_list, parent, false);

                final viewholderForAllUser viewholder = new viewholderForAllUser(view) ;




                return viewholder;
            }


        } ;

        recyclerView.setLayoutManager(llm) ;
        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter) ;

    }
}
