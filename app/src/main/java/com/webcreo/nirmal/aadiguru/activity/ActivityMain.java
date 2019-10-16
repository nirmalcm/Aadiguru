package com.webcreo.nirmal.aadiguru.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.webcreo.nirmal.aadiguru.R;
import com.webcreo.nirmal.aadiguru.adapter.AdapterVideo;
import com.webcreo.nirmal.aadiguru.adapter.AdapterAutoSlider;
import com.webcreo.nirmal.aadiguru.model.Content;

import java.util.List;

public class ActivityMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initComponent();
    }

    private Toolbar mToolbar;
    private void initToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    // YouTube player view
    private YouTubePlayerView youTubeView;
    private void initComponent() {
        initNavigationalDrawer();
        initAutoSlider();
        initVideos();

        //youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);

        // Initializing video player with developer key
        //youTubeView.initialize(Content.DEVELOPER_KEY, this);
    }

    private void initNavigationalDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()){
            case R.id.action_contactus:
                startActivity(new Intent(ActivityMain.this,ActivityContactUs.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        if (id == R.id.nav_videos) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_newspaper) {
            startActivity(new Intent(ActivityMain.this,ActivityNewsPapers.class));
        } else if (id == R.id.nav_images) {
            startActivity(new Intent(ActivityMain.this,ActivityImages.class));
        } else if (id == R.id.nav_events) {
            startActivity(new Intent(ActivityMain.this,ActivityEvents.class));
        } else if (id == R.id.nav_contactus) {
            startActivity(new Intent(ActivityMain.this,ActivityContactUs.class));
        }
        return true;
    }

    RecyclerView mVideosRecycler;
    private AdapterVideo mVideosAdapter;
    List<Content> mVideos;
    private void initVideos() {

        mVideosRecycler = (RecyclerView) findViewById(R.id.videos);

        mVideos = new Content().getVideos();

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mVideosRecycler.setLayoutManager(linearLayoutManager);

        mVideosAdapter = new AdapterVideo(this, mVideos);
        mVideosAdapter.notifyDataSetChanged();

        mVideosRecycler.setAdapter(mVideosAdapter);
        mVideosRecycler.setItemAnimator(new DefaultItemAnimator());
        mVideosRecycler.setNestedScrollingEnabled(false);
    }

    private AdapterAutoSlider myViewPagerAdapter;
    private ViewPager mViewPager;
    private void initAutoSlider() {

        mViewPager = (ViewPager) findViewById(R.id.view_pager_banner);

        myViewPagerAdapter = new AdapterAutoSlider(this, new Content().getSliders());
        mViewPager.setAdapter(myViewPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(final int position) {
                bottomProgressDots(position);
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }
            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        mViewPager.setPageMargin(-100);
        mViewPager.setOffscreenPageLimit(new Content().getSliders().size());

        // adding bottom dots
        bottomProgressDots(0);
        startAutoSlider(new Content().getSliders().size());
    }

    private void bottomProgressDots(int current_index) {
        LinearLayout dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        ImageView[] dots = new ImageView[new Content().getSliders().size()];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_IN);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current_index].setImageResource(R.drawable.shape_circle);
            dots[current_index].setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        }
    }

    private Runnable mRunnable = null;
    private Handler mHandler = new Handler();

    private void startAutoSlider(final int count) {
        mRunnable = new Runnable() {
            @Override
            public void run() {
                int pos = mViewPager.getCurrentItem();
                pos = pos + 1;
                if (pos >= count) pos = 0;
                mViewPager.setCurrentItem(pos);
                mHandler.postDelayed(mRunnable, 3000);
            }
        };
        mHandler.postDelayed(mRunnable, 3000);
    }


    /*private static final int RECOVERY_DIALOG_REQUEST = 1;
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    "player error", errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            player.loadVideo(Content.YOUTUBE_VIDEO_CODE);

            // Hiding player controls
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Content.DEVELOPER_KEY, this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        //YouTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        return null;
    }*/
}
