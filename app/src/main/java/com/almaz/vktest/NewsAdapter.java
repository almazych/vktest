package com.almaz.vktest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.almaz.vktest.newsfeeddb.Group;
import com.almaz.vktest.newsfeeddb.Item;
import com.almaz.vktest.newsfeeddb.Profile;
import com.almaz.vktest.newsfeeddb.Response;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    Context context;

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }


    private OnItemClickListener listener;
    private Response mResponse;
    private List<Item> mItems;
    private List<Profile> mProfiles;
    private List<Group> mGroups;

    public NewsAdapter(Response mResponse,  OnItemClickListener listener) {
        this.mResponse = mResponse;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mItems.get(position), listener);
    }

    @Override
    public int getItemCount() {
        if (mItems == null){
            return 0;
        }
        return mItems.size();
    }

    //метод для изменения списка
    public void changeDataSet(Response response){
        mResponse = null;
        mResponse = response;
        mItems = mResponse.getItems();
        mProfiles = mResponse.getProfiles();
        mGroups = mResponse.getGroups();

        notifyDataSetChanged();
    }

    public void addDataSet(Response response, int i){
        mItems.addAll(i,response.getItems());
        mProfiles.addAll(response.getProfiles());
        mGroups.addAll(response.getGroups());
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView avatar;
        TextView name, date, text,  attachments;

        String urlAvatar;

        public ViewHolder(View itemView) {
            super(itemView);

            avatar = (ImageView)itemView.findViewById(R.id.image_avatar);
            name = (TextView)itemView.findViewById(R.id.name_text);
            date= (TextView)itemView.findViewById(R.id.date_text);
            text = (TextView)itemView.findViewById(R.id.item_text);
            attachments = (TextView) itemView.findViewById(R.id.item_attachments);
        }

        public void bind(final Item item, final OnItemClickListener listener) {

            if(item.getCopyHistory()==null){
                getInfo(name,avatar,date,text,item.getSourceId(),item.getDate(),item.getText());
                if(item.getAttachments() != null){
                    attachments.setText("Показать вложения");
                }

            }else{
                getInfo(name,avatar,date,text,item.getCopyHistory().get(0).getOwnerId(),item.getCopyHistory().get(0).getDate(),item.getCopyHistory().get(0).getText());
                if(item.getCopyHistory().get(0).getAttachments() != null){
                    attachments.setText("Показать вложения");
                }
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

        }

        public void getInfo( TextView name, ImageView avatar, TextView date, TextView text, int id,int datum, String forText){

            if(forText!="") {
                text.setText(Html.fromHtml(forText));
            }else {
                text.setVisibility(View.GONE);
            }

            if(id>0){
                for(int i=0;i<mProfiles.size();i++){
                    if(id == mProfiles.get(i).getId()){
                        name.setText(mProfiles.get(i).getFirstName()+" "+mProfiles.get(i).getLastName());
                        urlAvatar = mProfiles.get(i).getPhoto100();
                        break;
                    }
                }
            } else{
                for(int i=0;i<mGroups.size();i++){
                    if(-id == mGroups.get(i).getId()){
                        name.setText(mGroups.get(i).getName());
                        urlAvatar = mGroups.get(i).getPhoto200();
                        break;
                    }
                }
            }

            Picasso.with(context)
                    .load(urlAvatar)
                    .resize(100,100)
                    .centerCrop()
                    .placeholder(R.drawable.ava_fon)
                    .into(avatar);

            date.setText(new SimpleDateFormat("dd.MM.yy 'в' HH:mm").format(new Date((long)datum*1000)).toString());
        }


    }



}