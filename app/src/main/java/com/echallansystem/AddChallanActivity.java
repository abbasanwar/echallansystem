package com.echallansystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.echallansystem.Models.Challan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;



public class AddChallanActivity extends AppCompatActivity{

    EditText fname,lname,cnicno,licenceno,location,mobilenumber,fineviolater;
    Spinner vtype;
    Button save;

    java.util.Calendar calander;
    SimpleDateFormat simpledateformat;
    String Date;
    TextView DisplayDateTime;
    Button BTN;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_challan);

        calander = java.util.Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date = simpledateformat.format(calander.getTime());

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();



        BTN = (Button)findViewById(R.id.button1);


        fname=(EditText)findViewById(R.id.fname);
        lname=(EditText)findViewById(R.id.lname);
        cnicno=(EditText)findViewById(R.id.cnicnum);
        licenceno=(EditText)findViewById(R.id.licencenum);
        location=(EditText)findViewById(R.id.locationviolation);
        mobilenumber=(EditText)findViewById(R.id.mobilenumber);
        fineviolater=(EditText)findViewById(R.id.fine);
        vtype=(Spinner)findViewById(R.id.spinnervtype);

        DisplayDateTime=(TextView) findViewById(R.id.textView1);



        save=(Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateForm())
                {

                    String frstname=fname.getText().toString().trim();
                    String lstname=lname.getText().toString().trim();
                    String cnic=cnicno.getText().toString().trim();
                    String licence=licenceno.getText().toString().trim();
                    String locat=location.getText().toString().trim();
                    String mobnum=mobilenumber.getText().toString().trim();
                    String fine=fineviolater.getText().toString().trim();
                    String violationtype=vtype.getSelectedItem().toString().trim();
                    String dispdate=DisplayDateTime.getText().toString().trim();


                    try {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(mobnum, null, " Dear " +frstname +" "+lstname+ " is fined for traffic violation of Rs." + fine + " by Traffic Police at " + locat + " On " + Date + "Due to Offence " +  violationtype + " \n Thank You", null, null);
                        Toast.makeText(getApplicationContext(), "SMS Sent!",
                                Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),
                                "SMS faild, please try again later!",
                                Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                    // create user object and set all the properties
                    Challan user=new Challan();
                    user.setFirstname(frstname);
                    user.setLastname(lstname);
                    user.setCnic(cnic);
                    user.setLicence(licence);
                    user.setLocation(locat);
                    user.setMobileNumber(mobnum);
                    user.setFine(fine);
                    user.setDateTime(dispdate);
                    user.setViolationtype(violationtype);

                    if(mAuth.getCurrentUser()!=null)
                    {
                        String key=mDatabase.child("Challan").push().getKey();
                        // save the user at UserNode under user UID
                        mDatabase.child("Challan").child(frstname).setValue(user, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                if (databaseError == null) {
                                    Toast.makeText(AddChallanActivity.this, "Data is saved successfully",
                                            Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        });
                    }

                }
            }
        });
    }

    // to check if user filled all the required fieds
    public boolean validateForm()
    {
        boolean alldone=true;
        String frstname=fname.getText().toString().trim();
        String lstname=lname.getText().toString().trim();
        String cnic=cnicno.getText().toString().trim();
        String licence=licenceno.getText().toString().trim();
        String locat=location.getText().toString().trim();
        String mobnum=mobilenumber.getText().toString().trim();
        String fine=fineviolater.getText().toString().trim();
        String violationtype=vtype.getSelectedItem().toString().trim();


        String dispdate=DisplayDateTime.getText().toString().trim();

        if(TextUtils.isEmpty(frstname))
        {
            fname.setError("Enter your first name");
            return false;}
        else {
            alldone=true;
            fname.setError(null);}

        if(TextUtils.isEmpty(lstname))
        {
            lname.setError("Enter your last name");
            return false;
        }
        else {
            alldone=true;
            lname.setError(null);
        }

        if(TextUtils.isEmpty(cnic))
        {
            cnicno.setError("Enter your Mobile number");
            return false;
        }
        else {
            alldone=true;
            cnicno.setError(null);
        }

        if(TextUtils.isEmpty(licence))
        {
            licenceno.setError("Enter your Licence");
            return false;
        }
        else {
            alldone=true;
            licenceno.setError(null);
        }
        if(TextUtils.isEmpty(locat))
        {
            location.setError("Enter your Location");
            return false;
        }
        else {
            alldone=true;
            location.setError(null);
        }
        if(TextUtils.isEmpty(mobnum))
        {
            mobilenumber.setError("Enter your Mobile Number");
            return false;
        }
        else {
            alldone=true;
            location.setError(null);
        }
        if(TextUtils.isEmpty(fine))
        {
            fineviolater.setError("Enter Fine");
            return false;
        }
        else {
            alldone=true;
            location.setError(null);
        }
        if(TextUtils.isEmpty(dispdate))
        {
            DisplayDateTime.setError("Click on Date and Time");
            return false;
        }
        else {
            alldone=true;
            location.setError(null);
        }
        return alldone;

    }

    public void dateandtime(View view) {
        DisplayDateTime.setText(Date);

    }

}
