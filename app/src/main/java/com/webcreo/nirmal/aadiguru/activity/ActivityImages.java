package com.webcreo.nirmal.aadiguru.activity;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.webcreo.nirmal.aadiguru.R;
import com.webcreo.nirmal.aadiguru.adapter.AdapterAutoSlider;
import com.webcreo.nirmal.aadiguru.adapter.AdapterImage;
import com.webcreo.nirmal.aadiguru.adapter.AdapterVideo;
import com.webcreo.nirmal.aadiguru.model.Content;

import java.util.List;

public class ActivityImages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        initToolbar();
        initComponent();
    }

    private Toolbar mToolbar;
    private void initToolbar() {
        getSupportActionBar().setTitle("Images");
    }

    private void initComponent() {
        initImages();
    }

    private RecyclerView mImagesRecycler;
    private AdapterImage mImagesAdapter;
    List<Content> mImages;
    private void initImages() {

        mImagesRecycler = (RecyclerView) findViewById(R.id.videos);

        mImages = new Content().getImages();

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mImagesRecycler.setLayoutManager(linearLayoutManager);

        mImagesAdapter = new AdapterImage(this, mImages);
        mImagesAdapter.notifyDataSetChanged();

        mImagesRecycler.setAdapter(mImagesAdapter);
        mImagesRecycler.setItemAnimator(new DefaultItemAnimator());
        mImagesRecycler.setNestedScrollingEnabled(false);
    }
}
