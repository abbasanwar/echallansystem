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


import com.echallansystem.Models.VehicleVerification;
import com.echallansystem.utils.FirebaseUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Random;

public class AddVehicleActivity extends AppCompatActivity {
    //Detail's of Owner
    EditText mOwnername,mOwnerfathername,mRegistrationyear;
    //Details Of Vehicle
    EditText mRegNo,mChasisNo,mEngineNo,mMakeName,mRegDate,mVehPrice,mYearOfManufacture,mColor,mTokenPaidUpto;
    TextView mVehicale;
    Button mBtn;
    String ownerName,ownerfatherName,regyear,myVehcile;
    //Details Of Vehicle
    String RegNo,ChasisNo,EngineNo,MakeName,RegDate,VehPrice,YearOfManufacture,Color,TokenPaidUpto;
    ProgressDialog mProgress;
    VehicleVerification  mVehicleVerification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        mOwnername = (EditText)findViewById(R.id.ownername);
        mOwnerfathername = (EditText)findViewById(R.id.ownerfathername);
        mRegistrationyear =(EditText)findViewById(R.id.registrationyear);
        mVehicale= (TextView)findViewById(R.id.generatevehiclenumber);

        //Detail's of Vehicle

        mRegNo = (EditText)findViewById(R.id.regno);
        mChasisNo = (EditText)findViewById(R.id.chasisno);
        mEngineNo = (EditText)findViewById(R.id.engineno);
        mMakeName = (EditText)findViewById(R.id.makename);
        mRegDate = (EditText)findViewById(R.id.registerationdate);
        mVehPrice = (EditText)findViewById(R.id.vehicleprice);
        mYearOfManufacture = (EditText)findViewById(R.id.yearofmanufacture);
        mColor = (EditText)findViewById(R.id.color);
        mTokenPaidUpto= (EditText)findViewById(R.id.tokentaxpaidupto);

        mBtn = (Button)findViewById(R.id.submitvehicledetail);
        mProgress = new ProgressDialog(this);
        mVehicleVerification = new VehicleVerification();
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendPost();
            }
        });
        mVehicale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeLicense();

            }
        });



    }

    private void sendPost() {
        mProgress.setMessage("Sending post...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);
        mProgress.show();

        ownerName = mOwnername.getText().toString();
        ownerfatherName= mOwnerfathername.getText().toString();
        regyear =mRegistrationyear.getText().toString();
        myVehcile = mVehicale.getText().toString();

        // Detail's of vehicle
        RegNo = mRegNo.getText().toString();
        ChasisNo = mChasisNo.getText().toString();
        EngineNo = mEngineNo.getText().toString();
        MakeName =mMakeName.getText().toString();
        RegDate = mRegDate .getText().toString();
        VehPrice= mVehPrice.getText().toString();
        YearOfManufacture =mYearOfManufacture.getText().toString();
        Color = mColor.getText().toString();
        TokenPaidUpto =mTokenPaidUpto.getText().toString();



        mVehicleVerification.setVehcileOwnerName(ownerName);
        mVehicleVerification.setVehcileOwnerFName(ownerfatherName);
        mVehicleVerification.setRegisterationYear(regyear);
        mVehicleVerification.setVehicleNumber(myVehcile);
        mVehicleVerification.setReg_num(RegNo);
        mVehicleVerification.setChasis_num(ChasisNo);
        mVehicleVerification.SetEngine_number(EngineNo);
        mVehicleVerification.SetMake_name(MakeName);
        mVehicleVerification.SetRegisteration_date(RegDate);
        mVehicleVerification.SetVehicle_price(VehPrice);
        mVehicleVerification.SetYearofmanufacture(YearOfManufacture);
        mVehicleVerification.SetColor(Color);
        mVehicleVerification.SetTokenpaidupto(TokenPaidUpto);

        FirebaseUtils.getVehicleRef().child(myVehcile)
                .setValue(mVehicleVerification).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                mProgress.dismiss();
                Toast.makeText(AddVehicleActivity.this,"Posted Successfully",Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void makeLicense() {
        Random rand = new Random();
        int licensNum = rand.nextInt(10000);
        String licensNumber = String.valueOf(licensNum);
        mVehicale.setText(licensNumber);
    }



}
