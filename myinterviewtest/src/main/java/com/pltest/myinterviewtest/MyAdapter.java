package com.pltest.myinterviewtest;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YueShuai
 * @date 2020/4/3
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    private List<String> mData = new ArrayList<>();
    private OnChange mChange;

    public MyAdapter(List<String> mData, OnChange change) {
        this.mData = mData;
        mChange = change;
    }

    private boolean isFirst = true;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView inflate = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        ((TextView) holder.itemView).setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        public MyHolder(final TextView itemView) {
            super(itemView);
            itemView.post(new Runnable() {
                @Override
                public void run() {
                    if (isFirst) {
                        isFirst = false;
                        int width = itemView.getWidth();
                        mChange.chagne(width);
                    }
                }
            });
        }
    }

    interface OnChange {
        void chagne(int value);
    }
}
