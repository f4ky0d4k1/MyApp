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

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Item> items;
    private final Context context;

    ItemAdapter(Context context, List<Item> items) {
        this.items = new ArrayList<>(items);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.dayView.setText(item.getDay());
        holder.internalItemView.setAdapter(new InternalItemAdapter(context, item.getInternalItems()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView dayView;
        final RecyclerView internalItemView;

        ViewHolder(View view) {
            super(view);
            dayView = (TextView) view.findViewById(R.id.day);
            internalItemView = (RecyclerView) view.findViewById(R.id.internal_list);

        }
    }

}
