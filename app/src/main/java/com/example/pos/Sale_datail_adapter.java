package com.example.pos;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;



public class Sale_datail_adapter extends RecyclerView.Adapter<Sale_datail_adapter.Acc_holder> {

    Cursor  cursor_for_main;
    Context context;


    Sale_datail_adapter(Context c, Cursor cr_for_main_pro) {
        this.context=c;
        cursor_for_main=cr_for_main_pro;


    }
    class Acc_holder extends RecyclerView.ViewHolder{
        TextView name;
        TextView qty;
        CardView cardView;

        public Acc_holder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.sale_detail_name);
            qty=itemView.findViewById(R.id.sale_datail_qty);
            cardView=itemView.findViewById(R.id.sale_detail_card);
        }
    }

    @NonNull
    @Override
    public Acc_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View v=layoutInflater.inflate(R.layout.lay_for_sale_details,parent,false);
        return new Sale_datail_adapter.Acc_holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Acc_holder holder, int position) {

        cursor_for_main.moveToPosition(position);
        holder.name.setText(String.valueOf(cursor_for_main.getString(0)));
        holder.qty.setText(String.valueOf(cursor_for_main.getInt(1)));


    }
    @Override
    public int getItemCount() {
        return cursor_for_main.getCount();
    }
}
