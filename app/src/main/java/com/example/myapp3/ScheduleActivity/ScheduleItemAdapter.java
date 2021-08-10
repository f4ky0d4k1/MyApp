package com.example.myapp3.ScheduleActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp3.R;

import java.util.ArrayList;
import java.util.List;

public class ScheduleItemAdapter extends RecyclerView.Adapter<ScheduleItemAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<ScheduleItem> scheduleItems;
    private final Context context;

    ScheduleItemAdapter(Context context, List<ScheduleItem> scheduleItems) {
        this.scheduleItems = new ArrayList<>(scheduleItems);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ScheduleItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.schedule_list_item, parent, false);
        return new ScheduleItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScheduleItemAdapter.ViewHolder holder, int position) {
        ScheduleItem scheduleItem = scheduleItems.get(position);
        holder.dayView.setText(scheduleItem.getDay());
        holder.internalItemView.setAdapter(new ScheduleInternalItemAdapter(context, scheduleItem.getInternalItems()));
    }

    @Override
    public int getItemCount() {
        return scheduleItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView dayView;
        final RecyclerView internalItemView;

        ViewHolder(View view) {
            super(view);
            dayView = (TextView) view.findViewById(R.id.text_day_title);
            internalItemView = (RecyclerView) view.findViewById(R.id.schedule_internal_list);

        }
    }
}