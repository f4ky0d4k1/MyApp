package com.example.myapp3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InternalItemAdapter extends RecyclerView.Adapter<InternalItemAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<InternalItem> internalItems;

    InternalItemAdapter(Context context, List<InternalItem> internalItems) {
        this.internalItems = new ArrayList<>(internalItems);
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public InternalItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_internal_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InternalItemAdapter.ViewHolder holder, int position) {
        InternalItem internalItem = internalItems.get(position);
        holder.timeView.setText(internalItem.getTime());
        holder.objectView.setText(internalItem.getObject());
        holder.placeView.setText(internalItem.getPlace());
        holder.professorView.setText(internalItem.getProfessor());
    }

    @Override
    public int getItemCount() {
        return internalItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView timeView, objectView, placeView, professorView;
        ViewHolder(View view){
            super(view);
            timeView = (TextView)view.findViewById(R.id.time);
            objectView = (TextView)view.findViewById(R.id.object);
            placeView = (TextView)view.findViewById(R.id.place);
            professorView = (TextView)view.findViewById(R.id.professor);

        }
    }
}
