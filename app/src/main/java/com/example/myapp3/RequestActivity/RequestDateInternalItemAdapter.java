package com.example.myapp3.RequestActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp3.R;

import java.util.ArrayList;
import java.util.List;

public class RequestDateInternalItemAdapter extends RecyclerView.Adapter<RequestDateInternalItemAdapter.ViewHolder> {

    private static int count = 0;

    private final LayoutInflater inflater;
    private final List<RequestDateInternalItem> requestDateInternalItems;
    private final View.OnClickListener dateButtonClickListener;

    RequestDateInternalItemAdapter(Context context, List<RequestDateInternalItem> requestDateInternalItems, View.OnClickListener dateButtonClickListener) {
        this.requestDateInternalItems = new ArrayList<>(requestDateInternalItems);
        this.inflater = LayoutInflater.from(context);
        this.dateButtonClickListener = dateButtonClickListener;
    }

    @NonNull
    @Override
    public RequestDateInternalItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.request_date_internal_list_item, parent, false);
        return new RequestDateInternalItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RequestDateInternalItemAdapter.ViewHolder holder, int position) {
        RequestDateInternalItem requestDateInternalItem = requestDateInternalItems.get(position);
        holder.dateButton.setText(requestDateInternalItem.getDate());
        holder.dateButton.setTag(count++);
        holder.dateButton.setOnClickListener(dateButtonClickListener);
    }

    @Override
    public int getItemCount() {
        return requestDateInternalItems.size();
    }

    public static void resetCount() {
        count = 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final RadioButton dateButton;

        ViewHolder(View view) {
            super(view);
            dateButton = view.findViewById(R.id.radio_button_date);
        }
    }

}
