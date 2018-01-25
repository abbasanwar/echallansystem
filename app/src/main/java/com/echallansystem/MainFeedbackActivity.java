package com.echallansystem;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.echallansystem.Models.Feedback;
import com.echallansystem.utils.FirebaseUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class MainFeedbackActivity extends AppCompatActivity {
    EditText feedname,feedmobile,feedmessage;
    Spinner ftype;
    Button fBtn;
    String name,mobile,message,feedbacktype;
    ProgressDialog mProgress;
    Feedback mfeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feedback);
        feedname = (EditText) findViewById(R.id.fedname);
        feedmobile = (EditText) findViewById(R.id.fedmob);
        feedmessage = (EditText) findViewById(R.id.fedmessage);
        ftype = (Spinner) findViewById(R.id.spinnerftype);

        fBtn = (Button) findViewById(R.id.submitfeedback);
        mProgress = new ProgressDialog(this);
        mfeedback = new Feedback();
        fBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendPost();
            }
        });


    }

    private void sendPost() {
        mProgress.setMessage("Sending post...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);
        mProgress.show();

        name = feedname.getText().toString();
        mobile= feedmobile.getText().toString();
        message =feedmessage.getText().toString();
        feedbacktype = ftype.getSelectedItem().toString();

       mfeedback.setFirstname(name);
        mfeedback.setMobileNumber(mobile);
        mfeedback.setMessage(message);
        mfeedback.setFeedbackType(feedbacktype);

        FirebaseUtils.getFeedbackRef().child(name)
                .setValue(mfeedback).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                mProgress.dismiss();
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(mobile, null, "Dear " +name +" you are being help out issued "+ feedbacktype+ " on the following Location "+message+" \nYou are stasified from Overall process Reply with (Y/N) \n Thank You", null, null);
                    Toast.makeText(getApplicationContext(), "SMS Sent!",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }


            }
                //Toast.makeText(MainFeedbackActivity.this,"Thank You For FeedBack",Toast.LENGTH_SHORT).show();

        });


    }



}
