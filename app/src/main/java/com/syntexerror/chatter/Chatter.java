package com.syntexerror.chatter;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class Chatter extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


    }
}


