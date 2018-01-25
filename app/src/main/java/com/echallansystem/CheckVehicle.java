package com.echallansystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.echallansystem.Models.VehicleVerification;
import com.echallansystem.utils.FirebaseUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class CheckVehicle extends AppCompatActivity implements View.OnClickListener {

    Button  bntSpecificVehicle;
    TextView tvSpecifdetail;
    EditText mSearch;

    private ValueEventListener mUserValueEventListener;
    CharSequence query;
    private DatabaseReference mUserRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_vehicle);
        //Adding Tool Bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//For ActionBar Back Butoon
        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        mSearch = (EditText) findViewById(R.id.searchView);
        bntSpecificVehicle = (Button) findViewById(R.id.btnSpecificVehicle);
        tvSpecifdetail = (TextView) findViewById(R.id.tvshowvehcile);

        bntSpecificVehicle.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSpecificVehicle:
                searchQueryText();
                break;

        }
    }



    private void searchQueryText() {
        query = mSearch.getText();
        mUserRef = FirebaseUtils.getVehicleRef().child(query.toString());
        mUserValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    VehicleVerification users = dataSnapshot.getValue(VehicleVerification.class);
                    tvSpecifdetail.setText("Owner Name          "+users.getVehcileOwnerName()
                            +"\nFather Name         "+users.getVehcileOwnerFName()
                            +"\nMake Number         "+users.getMake_name()
                            +"\nVehcile Number      "+users.getRegisterationYear()
                            +"\nChasis Number       "+users.getChasis_num()
                            +"\nEngine Number       "+users.getEngine_number()
                            +"\nVehicle Price       "+users.getVehicle_price()
                            +"\nVehicle Color       "+users.getColor()
                            +"\nRegistration Date   "+users.getRegisteration_date()
                            +"\nRegistration Year      "+ users.getVehicleNumber()
                            +"\nToken Paid Upto        "+users.getTokenpaidupto()
                            +"\nYear of Manufacture    "+users.getYearofmanufacture()
                            +"\nRegisteration Number   "+users.getReg_num()
                    );


                } else {
                    Toast.makeText(CheckVehicle.this, "Vehcile Not Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        if (mUserRef != null) {
            mUserRef.addValueEventListener(mUserValueEventListener);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mUserRef != null)
            mUserRef.removeEventListener(mUserValueEventListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mUserRef != null) {
            mUserRef.addValueEventListener(mUserValueEventListener);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_navigation, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return super.onOptionsItemSelected(item);
        }


        if (item.getItemId() == R.id.action_logout) {
            logout();
            startActivity(new Intent(this, LoginActivity.class)); //Go back to home page
            finish();

        }
        return super.onOptionsItemSelected(item);
    }

    /*For Menu logout*/
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}