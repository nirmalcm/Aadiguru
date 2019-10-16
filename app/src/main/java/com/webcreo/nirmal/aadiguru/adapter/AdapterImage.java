package com.webcreo.nirmal.aadiguru.adapter;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.webcreo.nirmal.aadiguru.R;
import com.webcreo.nirmal.aadiguru.model.Content;
import com.webcreo.nirmal.aadiguru.utils.ItemAnimation;
import com.webcreo.nirmal.aadiguru.utils.Tools;

import java.util.List;

public class AdapterImage extends RecyclerView.Adapter
{
    private List<Content> contents;
    private Context context;

    public AdapterImage(Context context, List<Content> contents)
    {
        this.contents = contents;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_image, parent, false);
        return new ViewHolderSub(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
    {
        final ViewHolderSub viewHolderSub = (ViewHolderSub) holder;


        //Picasso.with(context)
          //      .load(contents.get(position).getImage())
            //    .into(viewHolderSub.image);

        viewHolderSub.image.setTag(viewHolderSub.image.getId(), contents.get(position).getId());
        Tools.displayImageOriginal(context,viewHolderSub.image, contents.get(position).getImage());

        //viewHolderSub.name.setTag(viewHolderSub.image.getId(), contents.get(position).getId());
        //viewHolderSub.name.setText(contents.get(position).getName());

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
        ImageView image;
        TextView name;
        TextView description;

        public ViewHolderSub(View itemView)
        {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            //name = (TextView) itemView.findViewById(R.id.id_name);
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