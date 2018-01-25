package com.echallansystem;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.echallansystem.Models.Challan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ShowViolatersActivity extends AppCompatActivity {

    ListView allusers;
    ProgressDialog mProgressDialog;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    ListingAdapter adapter;
    ArrayList<Challan> users=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_violaters);
        allusers=(ListView)findViewById(R.id.allusers);
        adapter=new ListingAdapter(ShowViolatersActivity.this,users);
        allusers.setAdapter(adapter);
        getDataFromServer();
    }


    // getting the data from UserNode at Firebase and then adding the users in Arraylist and setting it to Listview
    public void getDataFromServer()
    {
        showProgressDialog();
        databaseReference.child("Challan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {

                    for(DataSnapshot postSnapShot:dataSnapshot.getChildren())
                    {
                        Challan user=postSnapShot.getValue(Challan.class);
                        users.add(user);
                        adapter.notifyDataSetChanged();
                    }


                }
                hideProgressDialog();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                hideProgressDialog();
            }
        });
    }
    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(ShowViolatersActivity.this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


    private class ListingAdapter extends BaseAdapter {

        Context context;
        LayoutInflater layoutInflater;
        ArrayList<Challan> users;

        public ListingAdapter(Context con,ArrayList<Challan> users)
        {
            context=con;
            layoutInflater = LayoutInflater.from(context);
            this.users=users;
        }

        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.adapter_listing, null, false);
                holder = new ViewHolder();

                holder.fullname = (TextView) convertView.findViewById(R.id.user_fullname);
                holder.fine = (TextView) convertView.findViewById(R.id.user_fine);
                holder.mobil=(TextView) convertView.findViewById(R.id.user_mobinum);
                holder.location = (TextView) convertView.findViewById(R.id.user_location);
                holder.datetime=(TextView) convertView.findViewById(R.id.user_datetime);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Challan user=users.get(position);

            holder.fullname.setText(user.getFirstname()+user.getLastname());
            holder.fine.setText(user.getFine());
            holder.location.setText(user.getLocation());
            holder.mobil.setText(user.getMobileNumber());
            holder.datetime.setText(user.getDateTime());

            return convertView;
        }


        public class ViewHolder {
            TextView fullname,mobil, fine,location,datetime;

        }
        @Override
        public Object getItem(int position) {
            return users.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }

}
