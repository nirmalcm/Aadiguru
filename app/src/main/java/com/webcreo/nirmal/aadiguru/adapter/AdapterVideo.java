package com.webcreo.nirmal.aadiguru.adapter;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.webcreo.nirmal.aadiguru.R;
import com.webcreo.nirmal.aadiguru.model.Content;
import com.webcreo.nirmal.aadiguru.utils.ItemAnimation;

import java.util.List;

public class AdapterVideo extends RecyclerView.Adapter
{
    private List<Content> contents;
    private Context context;

    public AdapterVideo(Context context, List<Content> contents)
    {
        this.contents = contents;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_video, parent, false);
        return new ViewHolderSub(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
    {
        final ViewHolderSub viewHolderSub = (ViewHolderSub) holder;


        //Picasso.with(context)
          //      .load(contents.get(position).getImage())
            //    .into(viewHolderSub.image);

        //viewHolderSub.image.setTag(viewHolderSub.image.getId(), contents.get(position).getId());
        //Tools.displayImageOriginal(context,viewHolderSub.image, contents.get(position).getImage());

        final YouTubeThumbnailLoader.OnThumbnailLoadedListener  onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener(){
            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

            }

            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                youTubeThumbnailView.setVisibility(View.VISIBLE);
                viewHolderSub.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);
            }
        };

        viewHolderSub.youTubeThumbnailView.initialize(Content.DEVELOPER_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

                youTubeThumbnailLoader.setVideo(contents.get(position).getName());
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //write something for failure
            }
        });

        viewHolderSub.playButton.setTag(viewHolderSub.playButton.getId(), contents.get(position).getId());
        viewHolderSub.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) context, Content.DEVELOPER_KEY, contents.get(position).getName(),0,true,true);
                context.startActivity(intent);
            }
        });
        //viewHolderSub.description.setTag(viewHolderSub.description.getId(), contents.get(position).getId());
        //viewHolderSub.description.setText(contents.get(position).getDescription());

        //setAnimation(viewHolderSub.itemView, position);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(
                    viewHolderSub.itemView.getContext(),
                    R.drawable.lift_inward
            );
            //viewHolderSub.itemView.setStateListAnimator(stateListAnimator);
        }
    }

    @Override
    public int getItemCount()
    {
        return contents.size();
    }

    public class ViewHolderSub extends RecyclerView.ViewHolder
    {
        protected RelativeLayout relativeLayoutOverYouTubeThumbnailView;
        YouTubeThumbnailView youTubeThumbnailView;
        protected ImageView playButton;

        public ViewHolderSub(View itemView)
        {
            super(itemView);
            playButton=(ImageView)itemView.findViewById(R.id.btnYoutube_player);
            relativeLayoutOverYouTubeThumbnailView = (RelativeLayout) itemView.findViewById(R.id.relativeLayout_over_youtube_thumbnail);
            youTubeThumbnailView = (YouTubeThumbnailView) itemView.findViewById(R.id.youtube_thumbnail);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                on_attach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        super.onAttachedToRecyclerView(recyclerView);
    }

    private int lastPosition = -1;
    private boolean on_attach = true;

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, on_attach ? position : -1, ItemAnimation.FADE_IN);
            lastPosition = position;
        }
    }
}