package com.example.a2017511_homework;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by lenovo on 2017/5/12.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> mItemList;
    private Context mContext;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemId;
        TextView itemName;
        TextView itemSummary;
        ImageView itemPhoto;
        public ViewHolder (View view){
            super(view);
            itemId = (TextView) view.findViewById(R.id.item_id);
            itemName = (TextView) view.findViewById(R.id.item_name);
            itemPhoto = (ImageView) view.findViewById(R.id.item_photo);
            itemSummary = (TextView) view.findViewById(R.id.item_summary);
        }
    }
    //fruitAdapter的构造函数
    public ItemAdapter(List<Item> itemList){
        mItemList = itemList;
    }
    //重写onCreateViewHolder函数
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item,parent,false);
        //创建一个实例，将加载的布局传入构造函数
        final ViewHolder holder = new ViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = mItemList.get(position);
        Glide.with(mContext).load(item.getPhoto()).into(holder.itemPhoto);
        holder.itemSummary.setText(item.getSummary());
        holder.itemName.setText(item.getName());
        holder.itemId.setText(item.getId());
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}
