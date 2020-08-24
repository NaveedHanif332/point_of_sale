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

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;


public class Pro_Adpter extends RecyclerView.Adapter<Pro_Adpter.Pro_holder> {

     Cursor  cursor_for_main;
    Context context;


    Pro_Adpter(Context c, Cursor cr_for_main_pro) {
        this.context=c;
        cursor_for_main=cr_for_main_pro;


    }
    class Pro_holder extends RecyclerView.ViewHolder{
        TextView t_id;
        TextView t_name;TextView t_price;TextView t_stock;
        CardView cardView;

        public Pro_holder(@NonNull View itemView) {
            super(itemView);
            t_id=itemView.findViewById(R.id.pro_lay_id);
            t_name=itemView.findViewById(R.id.pro_lay_name);
            t_price=itemView.findViewById(R.id.pro_lay_price);
            t_stock=itemView.findViewById(R.id.pro_lay_stock);
            cardView=itemView.findViewById(R.id.pro_card);
        }

    }

    @NonNull
    @Override
    public Pro_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View v=layoutInflater.inflate(R.layout.pro_main_layout,parent,false);

        return new Pro_Adpter.Pro_holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Pro_holder holder, int position) {

        cursor_for_main.moveToPosition(position);
        holder.t_id.setText(String.valueOf(cursor_for_main.getInt(0)));
        holder.t_name.setText(cursor_for_main.getString(1));
        holder.t_price.setText(String.valueOf(cursor_for_main.getInt(2)));
        holder.t_stock.setText(String.valueOf(cursor_for_main.getInt(3)));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(context,Account_Details.class);
                it.putExtra("id", String.valueOf(holder.t_id.getText()));
                it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                context.startActivity(it);
            }
        });
        holder.cardView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, final View v, final ContextMenu.ContextMenuInfo menuInfo) {
                menu.add("delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        //do what u want
                        Toast.makeText(context,"deleted ",Toast.LENGTH_SHORT).show();
                        Common_database database=new Common_database(context);
                        database.getWritableDatabase();
                        database.delete_pro(Integer.parseInt((String) holder.t_id.getText()));
                        cursor_for_main =database.getfor_pro_main();
                        notifyDataSetChanged();
                        return true;
                    }
                });
                menu.add("update").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {

                                Intent it=new Intent(context,Update_product.class);
                                it.putExtra("id", String.valueOf(holder.t_id.getText()));
                                it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                                context.startActivity(it);
                                return true;
                            }
                        });
            }
        });
    }
    @Override
    public int getItemCount() {
        return cursor_for_main.getCount();
    }
}
