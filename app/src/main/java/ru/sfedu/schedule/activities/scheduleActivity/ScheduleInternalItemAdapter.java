package ru.sfedu.schedule.activities.scheduleActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.sfedu.schedule.R;

import java.util.ArrayList;
import java.util.List;

public class ScheduleInternalItemAdapter extends RecyclerView.Adapter<ScheduleInternalItemAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<ScheduleInternalItem> scheduleInternalItems;

    ScheduleInternalItemAdapter(Context context, List<ScheduleInternalItem> scheduleInternalItems) {
        this.scheduleInternalItems = new ArrayList<>(scheduleInternalItems);
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ScheduleInternalItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.schedule_internal_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScheduleInternalItemAdapter.ViewHolder holder, int position) {
        ScheduleInternalItem scheduleInternalItem = scheduleInternalItems.get(position);
        holder.timeView.setText(scheduleInternalItem.getTime());
        holder.objectView.setText(scheduleInternalItem.getObject());
        holder.placeView.setText(scheduleInternalItem.getPlace());
        holder.professorView.setText(scheduleInternalItem.getProfessor());
    }

    @Override
    public int getItemCount() {
        return scheduleInternalItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView timeView, objectView, placeView, professorView;
        ViewHolder(View view){
            super(view);
            timeView = (TextView)view.findViewById(R.id.text_time);
            objectView = (TextView)view.findViewById(R.id.text_object);
            placeView = (TextView)view.findViewById(R.id.text_place);
            professorView = (TextView)view.findViewById(R.id.text_professor);

        }
    }
}