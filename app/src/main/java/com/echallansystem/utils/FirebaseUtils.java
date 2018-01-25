package com.echallansystem.utils;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils {
    //I'm creating this class for similar reasons as the Constants class, and to make my code a bit
    //cleaner and more well managed.


    public static DatabaseReference getPostRef(){
        return FirebaseDatabase.getInstance()
                .getReference(Constants.POST_KEY);

    }

    public static DatabaseReference getVehicleRef(){
        return FirebaseDatabase.getInstance()
                .getReference(Constants.Vehicle_Key);
    }

    public static DatabaseReference getFeedbackRef(){
        return FirebaseDatabase.getInstance()
                .getReference(Constants.Feedback_key);
    }
}
