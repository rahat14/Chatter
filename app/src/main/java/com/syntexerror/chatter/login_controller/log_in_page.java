package com.syntexerror.chatter.login_controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.syntexerror.chatter.BaseActivity;
import com.syntexerror.chatter.R;
import com.syntexerror.chatter.models.UserModel;

import java.util.HashMap;

public class log_in_page extends AppCompatActivity {
    EditText mail , pass ;
    FloatingActionButton signInBtn  ;
    FirebaseAuth mauth  ;
    ProgressBar pabr ;
    TextView signUptxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setBackgroundDrawableResource(R.drawable.background_image_one_signin);
        setContentView(R.layout.sign_in);
        getSupportActionBar().hide();
        mauth = FirebaseAuth.getInstance() ;

        mail = findViewById(R.id.emailEt);
        pass = findViewById(R.id.passEt);
        signInBtn = findViewById(R.id.signInBtn) ;
        pabr = findViewById(R.id.pbar) ;
        signUptxt = findViewById(R.id.signUpBtn) ;

        pabr.setVisibility(View.INVISIBLE);



        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String MAIL = mail.getText().toString() ;
                String PASS = pass.getText().toString();




                if( PASS.isEmpty() || MAIL.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Fill The Data Properly!! " ,
                            Toast.LENGTH_LONG).show();

                }
                else

                {
                    signUser(MAIL , PASS) ;

                }



            }
        });


        signUptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext() , register_page.class);
                startActivity(i);


            }
        });




    }

    private void signUser(String mail, String pass) {
         pabr.setVisibility(View.VISIBLE);
      mauth.signInWithEmailAndPassword(mail, pass)
              .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {

                      if(task.isSuccessful())
                      {
                          pabr.setVisibility(View.INVISIBLE);
                            sentUserTOHome() ;

                      }
                      else {
                          pabr.setVisibility(View.INVISIBLE);
                          Toast.makeText(getApplicationContext(), "Authentication failed. "+task.getException() ,
                                  Toast.LENGTH_LONG).show();
                      }



                  }
              }) ;

    }

    private void sentUserTOHome() {

//        DatabaseReference createRef = FirebaseDatabase.getInstance().getReference("users");


//       // String username  , name , uid , profileLink , joinTimeStamp , isOnline   ;
//        UserModel model  = new UserModel( "user1", "USER NAME 1" , mauth.getUid() ,"https://picsum.photos/id/237/200/300"
//        , System.currentTimeMillis()/1000 + "", "false") ;
//
//        createRef.child(mauth.getUid())
//                .setValue(model) ;

        Intent i = new Intent(getApplicationContext() , BaseActivity.class);
        startActivity(i);



        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser muser = mauth.getCurrentUser();

        if(muser != null){
            sentUserTOHome();
        }

        else {
            // do nothing
        }

    }
}
