package com.webcreo.nirmal.aadiguru.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.webcreo.nirmal.aadiguru.R;
import com.webcreo.nirmal.aadiguru.adapter.AdapterImage;
import com.webcreo.nirmal.aadiguru.model.Content;

import java.util.List;

public class ActivityContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        initToolbar();
        initComponent();
    }

    private Toolbar mToolbar;
    private void initToolbar() {
        getSupportActionBar().setTitle("Contact Us");
    }

    private void initComponent() {
    }
}
