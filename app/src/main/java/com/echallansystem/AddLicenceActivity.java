package com.echallansystem;


import android.app.ProgressDialog;
import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import  com.echallansystem.Models.LicenceVerification;
import  com.echallansystem.utils.FirebaseUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Date;
import java.util.Random;


public class AddLicenceActivity extends AppCompatActivity {


    EditText mDistrictName, mName, mSurname,mTypelicence,mDateExpiry,mDateIssue;
    TextView mLicense;
    Button mBtn;
    String Dist_Name, Name, Father_Name, Licence_Number,Type_Licence,Date_Expiry,Date_Issue;
    ProgressDialog mProgress;
    LicenceVerification mPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_licence);

        mDistrictName = (EditText) findViewById(R.id.distName);
        mName = (EditText) findViewById(R.id.edName);
        mSurname = (EditText) findViewById(R.id.fstName);
        mTypelicence= (EditText) findViewById(R.id.typeoflicence);
        mDateExpiry = (EditText) findViewById(R.id.dateofexpiry);
        mDateIssue = (EditText) findViewById(R.id.dateofissue);



       mLicense = (TextView) findViewById(R.id.tvLicense);

        mBtn = (Button) findViewById(R.id.btnDetails);
        mProgress = new ProgressDialog(this);
        mPost = new LicenceVerification();
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendPost();
            }
        });
       /* mLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeLicense();

            }
        });


    */
    }
    private void sendPost() {
        mProgress.setMessage("Sending post...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);
        mProgress.show();

        Random rand = new Random();
        int licensNum = rand.nextInt(1000);
        String licensNumber = String.valueOf(licensNum);
        mLicense.setText(licensNumber);

        Dist_Name = mDistrictName.getText().toString();
        Name = mName.getText().toString();
        Father_Name = mSurname.getText().toString();
        Licence_Number = mLicense.getText().toString();
        Type_Licence = mTypelicence.getText().toString();
        Date_Expiry = mDateExpiry.getText().toString();
        Date_Issue = mDateIssue.getText().toString();

        mPost.setLicenceHolderDistrictName(Dist_Name);
        mPost.setDrivingLicense(Licence_Number);
        mPost.setLicenceHolderName(Name);
        mPost.setLicenceHolderfName(Father_Name);
        mPost.setlicencetype(Type_Licence);
        mPost.setDate_expiry(Date_Expiry);
        mPost.setDate_issue(Date_Issue);
        FirebaseUtils.getPostRef().child(Licence_Number)
                .setValue(mPost).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                mProgress.dismiss();
                Toast.makeText(AddLicenceActivity.this, "Posted Successfully", Toast.LENGTH_SHORT).show();
            }
        });


    }




}
