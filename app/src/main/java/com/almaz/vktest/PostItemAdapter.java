package com.almaz.vktest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.almaz.vktest.wallpostdb.Attachment;
import com.almaz.vktest.wallpostdb.Response;
import com.squareup.picasso.Picasso;

public class PostItemAdapter extends RecyclerView.Adapter<PostItemAdapter.ViewHolder> {

    Context context;
    Response mResponse;

    public PostItemAdapter(Response mResponse) {
        this.mResponse = mResponse;
    }

    @Override
    public PostItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return new PostItemAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PostItemAdapter.ViewHolder holder, int position) {
        holder.bind(mResponse.getAttachments().get(position));
    }

    @Override
    public int getItemCount() {
        if (mResponse.getAttachments() == null){
            return 0;
        }
        return mResponse.getAttachments().size();
    }

    public void changeDataSet(Response response){
        mResponse = response;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView)itemView.findViewById(R.id.images);
        }

        public void bind(final Attachment item) {

            if(item.getPhoto() !=null){
                Picasso.with(context)
                        .load(item.getPhoto().getPhoto604())
                        .resize(400,400)
                        .placeholder(R.drawable.post_fon)
                        .into(mImageView);
            }
            if (item.getVideo()!=null){
                Picasso.with(context)
                        .load(item.getVideo().getPhoto320())
                        .resize(400,400)
                        .placeholder(R.drawable.post_fon)
                        .into(mImageView);
            }

        }
    }
}
