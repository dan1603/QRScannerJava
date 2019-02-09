package com.kalashnyk.denys.qrscanner.presentation.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import java.util.List;

public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder, M, L> extends RecyclerView.Adapter<VH> {

    protected List<M> mList;
    protected Context mContext;
    protected L mItemClickListener;

    public BaseAdapter(Context context, List<M> list) {
        mList = list;
        mContext = context;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.itemView.setTag(mList.get(position));
    }

    public M getItem(int position) {
        return mList.get(position);
    }

    public int getItemCount() {
        return mList.size();
    }

    public void add(M item) {
        mList.add(item);
    }

    public void addAll(List<M> list) {
        for (M item : list) {
            add(item);
        }
    }

    public void remove(M item) {
        int position = mList.indexOf(item);
        if (position > -1) {
            mList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

    public void setItemClickListener(L listener){
        mItemClickListener = listener;
    }
}
