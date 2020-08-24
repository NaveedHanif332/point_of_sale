package com.example.pos;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListAdpter_pro extends RecyclerView.Adapter<ListAdpter_pro.sale_holder> {
    List<String> p_name =new ArrayList<>();

    List<String> p_price=new ArrayList<>(),p_quantity=new ArrayList<>(),p_sub_total=new ArrayList<>();;

    Context context;
    ListAdpter_pro(Context c,List<String> ls_name,List<String> ls_price,List<String> ls_quantity,List<String> sub_total) {
        p_name.addAll(ls_name);
        p_price.addAll(ls_price);
        p_quantity.addAll(ls_quantity);
        p_sub_total.addAll(sub_total);

        this.context = c;
    }
    class sale_holder extends RecyclerView.ViewHolder{
        TextView name, price,qty,sub;
        CardView cardView;
        public sale_holder(@NonNull View itemView) {
            super(itemView);
            price=itemView.findViewById(R.id.list_adp_price);
            qty=itemView.findViewById(R.id.list_adp_qty);
            sub=itemView.findViewById(R.id.list_adp_sub);
            name=itemView.findViewById(R.id.list_adp_name);
            cardView=itemView.findViewById(R.id.list_adp_card);
        }
    }
    @NonNull
    @Override
    public sale_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View v=layoutInflater.inflate(R.layout.listadpter_lay,parent,false);
        return new ListAdpter_pro.sale_holder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull final sale_holder holder, int position) {



        holder.name.setText(String.valueOf(p_name.get(position)));
        holder.price.setText(String.valueOf(p_price.get(position)));
        holder.qty.setText(String.valueOf(p_quantity.get(position)));
        holder.sub.setText(String.valueOf(p_sub_total.get(position)));
        holder.cardView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, final View v, final ContextMenu.ContextMenuInfo menuInfo) {
                menu.add("Remove").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        //do what u want
                        return true;
                    }
                });

            }
        });
    }
    @Override
    public int getItemCount() {
        return p_name.size();
    }
}

