package com.echallansystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;

import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.echallansystem.Models.VehicleVerification;

import com.echallansystem.utils.FirebaseUtils;


public class ShowVehicleActivity extends AppCompatActivity {
    private FirebaseRecyclerAdapter<VehicleVerification, PostHolder> mPostAdapter;
    private RecyclerView mPostRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_vehicle);
        init();
    }
    private void init() {
        mPostRecyclerView = (RecyclerView)findViewById(R.id.alluser);
        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        setupAdapter();
        mPostRecyclerView.setAdapter(mPostAdapter);
    }
    private void setupAdapter() {
        mPostAdapter = new FirebaseRecyclerAdapter<VehicleVerification, PostHolder>(
                VehicleVerification.class,
                R.layout.adapter_listing_vehicle,
                PostHolder.class,
                FirebaseUtils.getVehicleRef()
        ) {
            @Override
            protected void populateViewHolder(PostHolder viewHolder, final VehicleVerification model, int position) {

                System.out.print(" ");
                viewHolder.setPostTv("Owner Name                 " +model.getVehcileOwnerName()
                        +"\nFather Name                 " + model.getVehcileOwnerFName()
                        +"\nMake Number               "+model.getMake_name()
                        +"\nVehicle Price                "+model.getVehicle_price()
                        +"\nVehicle Color                 "+model.getColor()
                        +"\nVehcile Number            " +model.getVehicleNumber()
                        +"\nChasis Number               "+model.getChasis_num()
                        +"\nEngine Number               "+model.getEngine_number()
                        +"\nRegistration Date            "+model.getRegisteration_date()
                        +"\nRegistration Year            "+model.getRegisterationYear()
                        +"\nToken Paid Upto             "+model.getTokenpaidupto()
                        +"\nYear of Manufacture      "+model.getYearofmanufacture()
                        +"\nRegisteration Number    "+model.getReg_num()

                        +"\n______________________________________");

            }

        };
    }
    public static class PostHolder extends RecyclerView.ViewHolder {

        TextView postTv;

        public PostHolder(View itemView) {
            super(itemView);

            postTv = (TextView) itemView.findViewById(R.id.vehcileshowpost);


        }

        public void setPostTv(String post) {
            postTv.setText(post);
        }



    }

}
