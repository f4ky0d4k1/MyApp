package ru.sfedu.schedule.activities.requestActivity;

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

public class RequestDateItemAdapter extends RecyclerView.Adapter<RequestDateItemAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<RequestDateItem> requestDateItems;
    private final Context context;
    private final View.OnClickListener dateButtonClickListener;


    RequestDateItemAdapter(Context context, List<RequestDateItem> requestDateItems, View.OnClickListener dateButtonClickListener) {
        this.requestDateItems = new ArrayList<>(requestDateItems);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.dateButtonClickListener = dateButtonClickListener;
        RequestDateInternalItemAdapter.resetCount();
    }

    @NonNull
    @Override
    public RequestDateItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.request_date_list_item, parent, false);
        return new RequestDateItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RequestDateItemAdapter.ViewHolder holder, int position) {
        RequestDateItem requestDateItem = requestDateItems.get(position);
        holder.monthView.setText(requestDateItem.getMonth());
        holder.internalItemView.setAdapter(new RequestDateInternalItemAdapter(context, requestDateItem.getInternalItems(), dateButtonClickListener));
    }

    @Override
    public int getItemCount() {
        return requestDateItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView monthView;
        final RecyclerView internalItemView;

        ViewHolder(View view) {
            super(view);
            monthView = view.findViewById(R.id.text_month_title);
            internalItemView = view.findViewById(R.id.request_date_internal_list);
        }
    }
}

