package com.syntexerror.chatter.login_controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.syntexerror.chatter.BaseActivity;
import com.syntexerror.chatter.R;
import com.syntexerror.chatter.models.UserModel;

public class register_page extends AppCompatActivity {

    EditText usenameET , passET , emailET ;
    FloatingActionButton signUpBtn ;
    TextView signIntxt ;
    ProgressBar pbar  ;
    FirebaseAuth mauth  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setBackgroundDrawableResource(R.drawable.background_image_one_signup);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();
        mauth = FirebaseAuth.getInstance() ;


        // views
        usenameET = findViewById(R.id.userNameEt) ;
        passET = findViewById(R.id.passwordEt) ;
        emailET = findViewById(R.id.emailEt) ;
        pbar = findViewById(R.id.pbar);
        signUpBtn = findViewById(R.id.signUpBtn) ;
        signIntxt = findViewById(R.id.signin) ;
        pbar.setVisibility(View.GONE);



        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get the text
                String USERNAME = usenameET.getText().toString() ;
                String PASS = passET.getText().toString() ;
                String MAIL = emailET.getText().toString() ;


                if(USERNAME.isEmpty() || PASS.isEmpty() || MAIL.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Fill The Data Properly!! " ,
                            Toast.LENGTH_LONG).show();

                }
                else

                {
                    SendUserForRegisterWithMail(USERNAME  , PASS , MAIL) ;

                }






            }
        });

        signIntxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });






    }

    private void SendUserForRegisterWithMail(final String username, String pass, final String mail) {
        pbar.setVisibility(View.VISIBLE);

        mauth.createUserWithEmailAndPassword(mail , pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // check if the reg is succeccgul


                        if (task.isSuccessful())
                        {

                            uploadUserData(mail , username);

                        }
                        else
                        {

                            pbar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Registration  failed. "+task.getException() ,
                                    Toast.LENGTH_LONG).show();
                        }




                    }
                }) ;



    }

    private void uploadUserData(String mail, String username) {
        String uid  = " null ";


        try{
            uid = mauth.getUid() ;

        }
        catch (Exception e )
        {
            pbar.setVisibility(View.GONE);
            // user was registered but data was not updated
            // then user should be known that
            Toast.makeText(getApplicationContext(), "Something Went Wrong . Please Update your Profile Manually " ,
                    Toast.LENGTH_LONG).show();


           // sentUserTOHome();

        }

        // now upload the data to the sever
                DatabaseReference createRef = FirebaseDatabase.getInstance().getReference("users");


        if(uid== null)
        {
            pbar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Something Went Wrong . Please Update your Profile Manually " ,
                    Toast.LENGTH_LONG).show();
        }
        else
            {
                // String username  , name , uid , profileLink , joinTimeStamp , isOnline   ;
                UserModel model  = new UserModel( username, "null" , mauth.getUid() ,"https://picsum.photos/id/237/200/300"
                        , System.currentTimeMillis()/1000 , "true") ;

                createRef.child(uid).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pbar.setVisibility(View.GONE);


                        sentUserTOHome();


                    }
                }) ;
            }









    }
    private void sentUserTOHome() {

        Intent i = new Intent(getApplicationContext() , BaseActivity.class);
        startActivity(i);
        finish(); // finish so that user dont come back to this activity

    }
}
