package com.syntexerror.chatter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.syntexerror.chatter.activty.AllUserList;
import com.syntexerror.chatter.activty.myFriendList;
import com.syntexerror.chatter.fragment.FriendsChatFragment;
import com.syntexerror.chatter.fragment.GroupsFragment;
import com.syntexerror.chatter.fragment.NottificationFragment;
import com.syntexerror.chatter.login_controller.log_in_page;

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        //adding  fragment
        sectionsPagerAdapter.AddFragment(new FriendsChatFragment());
        sectionsPagerAdapter.AddFragment(new NottificationFragment());
        sectionsPagerAdapter.AddFragment(new GroupsFragment());


        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
//         int[] image = {
//                R.drawable.ic_arrow_forward_black_24dp,
//                R.drawable.ic_search,
//                R.drawable.ic_arrow_forward_black_24dp };
     tabs.setupWithViewPager(viewPager);


//
//        for (int i = 0; i < tabs.getTabCount(); i++) {
//            tabs.getTabAt(i).setIcon(image[i]);
//        }



        FloatingActionButton fab = findViewById(R.id.fab);




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  startActivity(new Intent(BaseActivity.this , NewMessageActivity.class));
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.add_friend :
                sendUserToTheAllUserAcivity() ;
                return  true  ;

            case  R.id.my_friend :
                sendUserToTheMyFriendAcivity();

                return  true ;
            case  R.id.log_out :
                logout();

                return  true ;

            default:
                return  super.onOptionsItemSelected(item);


        }





    }

    private void sendUserToTheMyFriendAcivity() {
        Intent o = new Intent(getApplicationContext() , myFriendList.class);
        startActivity(o);
    }

    private void sendUserToTheAllUserAcivity() {
        Intent o = new Intent(getApplicationContext() , AllUserList.class);
        startActivity(o);
    }

    private  void logout()
    {

        FirebaseAuth mauth = FirebaseAuth.getInstance();

        mauth.signOut();


        Intent o = new Intent(getApplicationContext() , log_in_page.class);
        startActivity(o);
        finish();
    }
}

