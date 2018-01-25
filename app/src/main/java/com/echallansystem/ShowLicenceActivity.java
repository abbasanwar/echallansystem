package com.echallansystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.echallansystem.Models.LicenceVerification;
import com.echallansystem.utils.FirebaseUtils;
import com.firebase.ui.database.FirebaseRecyclerAdapter;


public class ShowLicenceActivity extends AppCompatActivity {
    private FirebaseRecyclerAdapter<LicenceVerification, PostHolder> mPostAdapter;
    private RecyclerView mPostRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_licence);
        init();
    }
    private void init() {
        mPostRecyclerView = (RecyclerView)findViewById(R.id.postlist);
        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        setupAdapter();
        mPostRecyclerView.setAdapter(mPostAdapter);
    }
    private void setupAdapter() {
        mPostAdapter = new FirebaseRecyclerAdapter<LicenceVerification, PostHolder>(
                LicenceVerification.class,
                R.layout.allpost_row,
                PostHolder.class,
                FirebaseUtils.getPostRef()
        ) {
            @Override
            protected void populateViewHolder(PostHolder viewHolder, final LicenceVerification model, int position) {


                viewHolder.setPostTv("Name                    \t\t"+model.getLicenceHolderName()
                        +"\n"+"Father Name       \t\t"+model.getLicenceHolderfName()+"\n"
                        +"District Name      \t\t"+model.getLicenceHolderDistrictName()
                        +"\n"+"Licence Number \t\t"+model.getDrivingLicense()
                        + "\n"+"Licence Type      \t\t"+model.getlicencetype()
                        + "\n"+"Issue Date        \t\t"+model.getDate_issue()
                        + "\n"+"Expire Date      \t\t"+model.getDate_expiry()
                        + "\n______________________________________");

            }
        };
    }
    public static class PostHolder extends RecyclerView.ViewHolder {

        TextView postTv;

        public PostHolder(View itemView) {
            super(itemView);

            postTv = (TextView) itemView.findViewById(R.id.tvAllPost);


        }

        public void setPostTv(String post) {
            postTv.setText(post);
        }



    }

}
