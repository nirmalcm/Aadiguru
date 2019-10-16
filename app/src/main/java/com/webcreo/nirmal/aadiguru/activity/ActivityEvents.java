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

public class ActivityEvents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        initToolbar();
        initComponent();
    }

    private Toolbar mToolbar;
    private void initToolbar() {
        getSupportActionBar().setTitle("Events");
    }

    private void initComponent() {
        initImages();
    }

    private RecyclerView mImagesRecycler;
    private AdapterImage mImagesAdapter;
    List<Content> mImages;
    private void initImages() {

        mImagesRecycler = (RecyclerView) findViewById(R.id.videos);

        mImages = new Content().getEvents();

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mImagesRecycler.setLayoutManager(linearLayoutManager);

        mImagesAdapter = new AdapterImage(this, mImages);
        mImagesAdapter.notifyDataSetChanged();

        mImagesRecycler.setAdapter(mImagesAdapter);
        mImagesRecycler.setItemAnimator(new DefaultItemAnimator());
        mImagesRecycler.setNestedScrollingEnabled(false);
    }
}
