package com.echallansystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;

public class MainVehicleActivity extends AppCompatActivity implements View.OnClickListener {
        Button mBtnShowAllDetails,mBntPlaceDetails,mbuttoncheck;

     //   private ValueEventListener mUserValueEventListener;
      //  private DatabaseReference mUserRef;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main_vehicle);
                //Adding Tool Bar
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);

                //For ActionBar Back Butoon
                if(getSupportActionBar()!=null){

                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        getSupportActionBar().setDisplayShowHomeEnabled(true);
                }

                mBntPlaceDetails = (Button)findViewById(R.id.regveh);
                mBtnShowAllDetails = (Button)findViewById(R.id.showvehicle);
                mbuttoncheck = (Button)findViewById(R.id.checkvehicle);



                mBntPlaceDetails.setOnClickListener(this);
                mBtnShowAllDetails.setOnClickListener(this);
                mbuttoncheck.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {
                switch (v.getId()) {
                         case R.id.checkvehicle:
                                Intent check = new Intent(MainVehicleActivity.this, CheckVehicle.class);
                                startActivity(check);
                                break;
                        case R.id.regveh:
                                Intent intent = new Intent(MainVehicleActivity.this, AddVehicleActivity.class);
                                startActivity(intent);
                                break;
                        case R.id.showvehicle:
                                Intent intent2 = new Intent(MainVehicleActivity.this, ShowVehicleActivity.class);
                                startActivity(intent2);
                                break;

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
                if(item.getItemId()==android.R.id.home)
                {
                        finish();
                        return super.onOptionsItemSelected(item);
                }


                if (item.getItemId()== R.id.action_logout) {
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
