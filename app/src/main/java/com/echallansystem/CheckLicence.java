package com.echallansystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.echallansystem.Models.LicenceVerification;
import com.echallansystem.utils.FirebaseUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class CheckLicence  extends AppCompatActivity implements View.OnClickListener {


    Button bntSpecificLicence;
    TextView tvSpecifdetail;
    EditText mSearch;
    private ValueEventListener mUserValueEventListener;
    CharSequence query;
    private DatabaseReference mUserRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_licence);


        //Adding Tool Bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //For ActionBar Back Butoon
        if(getSupportActionBar()!=null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        mSearch = (EditText) findViewById(R.id.searchView);
        bntSpecificLicence = (Button)findViewById(R.id.btnSpecific);
        tvSpecifdetail =(TextView)findViewById(R.id.tvShowdetail);

        bntSpecificLicence.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSpecific:
                searchQueryText();
                break;

        }
    }

    private void searchQueryText() {
        query=  mSearch.getText();
        mUserRef = FirebaseUtils.getPostRef().child(query.toString());
        mUserValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    LicenceVerification users = dataSnapshot.getValue(LicenceVerification.class);
                    tvSpecifdetail.setText("Name           \t"+users.getLicenceHolderName()
                            +"\n"+"Father Name    \t"+users.getLicenceHolderfName()
                            +"\n"+"District Name  \t"+users.getLicenceHolderDistrictName()
                            +"\n"+"Licence Number \t"+users.getDrivingLicense()
                            +"\n"+"Licence Type   \t"+users.getlicencetype()
                            +"\n"+"Issue Date     \t"+users.getDate_issue()
                            +"\n"+"Expiry Date    \t"+users.getDate_expiry());

                }else {
                    Toast.makeText(CheckLicence.this, "Licence Not Found ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        if(mUserRef!=null){
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
        if(mUserRef!=null){
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
