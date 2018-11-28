package com.MY.project1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>{

    private Context context;
    private List<Event>eventList;
    private ItemActionListener listener;

    public EventAdapter(Context context, List<Event> eventList, Fragment fragment) {
        this.context = context;
        this.eventList = eventList;
        listener = (ItemActionListener) fragment;
    }


    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.event_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, final int position) {
        holder.nameTV.setText(eventList.get(position).getEventName());
        holder.budgetTV.setText(eventList.get(position).getEndDate());

        holder.menuTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.person_menu,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        final String id = eventList.get(position).getEventId();
                        switch (item.getItemId()){
                            case R.id.menu_edit:
                                listener.onItemEdit(id);
                                break;
                            case R.id.menu_delete:
                                listener.onItemDelete(id);
                                break;
                        }
                        return false;
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV, budgetTV, menuTV;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.row_event_name);
            budgetTV = itemView.findViewById(R.id.row_event_budget);
            menuTV = itemView.findViewById(R.id.row_event_menu);
        }
    }

    public void updateList(List<Event> events){
        this.eventList = events;
        notifyDataSetChanged();
    }

    public interface ItemActionListener{
        void onItemEdit(String eventID);
        void onItemDelete(String eventID);
    }
}
