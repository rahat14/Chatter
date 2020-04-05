package com.syntexerror.chatter.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.syntexerror.chatter.R;
import com.syntexerror.chatter.models.FriendRequestModel;
import com.syntexerror.chatter.models.friendModel;
import com.syntexerror.chatter.viewHolders.frindReqViewHolders;

import java.util.stream.Stream;


public class NottificationFragment extends Fragment {

    public NottificationFragment() {
    }

    View view ;
    RecyclerView recyclerView ;
    LinearLayoutManager llm ;
    DatabaseReference userRef ;
    FirebaseRecyclerAdapter<FriendRequestModel, frindReqViewHolders> firebaseRecyclerAdapter ;
    FirebaseRecyclerOptions<FriendRequestModel> options ;
    Context context ;
    String uid  ;
    FirebaseAuth mauth  ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_nottification, container, false);
        mauth =FirebaseAuth.getInstance() ;
        userRef = FirebaseDatabase.getInstance().getReference("users")
                .child(mauth.getUid())
                .child("reqRepo");
        userRef.keepSynced(true); // cahce enabled
        context = view.getContext();

        // views

        recyclerView = view.findViewById(R.id.list) ;
        llm = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(llm) ;



        loadData() ;

        return view;
    }

    private void loadData() {

        options = new FirebaseRecyclerOptions.Builder<FriendRequestModel>()
                .setQuery( userRef , FriendRequestModel.class)
                .build() ;


        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<FriendRequestModel, frindReqViewHolders>(options) {
            @Override
            protected void onBindViewHolder(@NonNull frindReqViewHolders viewholderForAllUser,final int i, @NonNull FriendRequestModel userModel) {

                viewholderForAllUser.setDetails( context , userModel.getRequsetedFriendUsername() , userModel.getRequsetedFriendProfileLink());


                viewholderForAllUser.accptBTn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                  String  postID =   getItem(i).getPostId() ;

                       if ( !postID.isEmpty())
                       {
                           // we go with accept
                           String requestedFriendUid = getItem(i).getRequsetedFriendUid() ;


                           makeFriend(requestedFriendUid , postID) ;




                       }

                       else {

                           //
                       }


                    }
                });

                viewholderForAllUser.DeclineBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String  postID =   getItem(i).getPostId() ;

                        if ( !postID.isEmpty())
                        {
                            // we go with accept
                            userRef.child(postID).removeValue() ;


                        }

                        else {

                            //
                        }



                    }
                });


            }

            @NonNull
            @Override
            public frindReqViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_for_friend_request, parent, false);

                 frindReqViewHolders viewholder = new frindReqViewHolders(view) ;




                return viewholder;
            }


        } ;

        recyclerView.setLayoutManager(llm) ;
        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter) ;

    }

    private void makeFriend(final String requestedFriendUid,final String postID) {

      final   String friendShipID = requestedFriendUid + FirebaseAuth.getInstance().getUid() ;

        friendModel friendModel = new friendModel(requestedFriendUid , friendShipID) ;

        DatabaseReference OwnRef = FirebaseDatabase.getInstance().getReference("users")
                .child(mauth.getUid())
                .child("friendList");

        OwnRef.child(friendShipID).setValue(friendModel)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // make myself to that guys friends list

                        friendModel myModel = new friendModel(FirebaseAuth.getInstance().getUid() , friendShipID) ;

                        DatabaseReference friendRef = FirebaseDatabase.getInstance().getReference("users")
                                .child(requestedFriendUid)
                                .child("friendList");

                        friendRef.child(friendShipID).setValue(myModel)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        // delete the friendship
                                        userRef.child(postID).removeValue() ;

                                    }
                                });







                    }
                })  ;


    }
}
