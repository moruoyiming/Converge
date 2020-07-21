package com.example.hotfix.note.class19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotfix.R;

import java.util.List;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> {
    private List<Star> stars;

    private Context context;

    public StarAdapter(List<Star> stars, Context context) {
        this.stars = stars;
        this.context = context;
    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false);
        StarViewHolder starViewHolder = new StarViewHolder(view);
        return starViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StarViewHolder holder, int position) {
        holder.tv.setText(stars.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return stars == null ? 0 : stars.size();
    }


    public boolean isGroupHead(int position) {
        if (position == 0) {
            return true;
        } else {
            String groupName = stars.get(position).getGroudName();
            String preGroupName = stars.get(position - 1).getGroudName();
            if (!groupName.equals(preGroupName)) {
                return true;
            }
        }
        return false;
    }

    public void getGroupName(int position) {
        stars.get(position).getGroudName();
    }


    public class StarViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public StarViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_name);

        }
    }
}
