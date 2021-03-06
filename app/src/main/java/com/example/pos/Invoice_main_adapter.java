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
public class Invoice_main_adapter extends RecyclerView.Adapter<Invoice_main_adapter.sale_holder> {
    Cursor  cursor_for_main;
    Context context;
    Invoice_main_adapter(Context c, Cursor cr_for_main_pro) {
        this.context = c;
        cursor_for_main = cr_for_main_pro;
    }
    class sale_holder extends RecyclerView.ViewHolder{
        TextView pro_id, pro_name,price,stock;
        CardView cardView;
        public sale_holder(@NonNull View itemView) {
            super(itemView);
            pro_name=itemView.findViewById(R.id.lay_add_sale_name);
            price=itemView.findViewById(R.id.lay_add_sale_price);
            stock=itemView.findViewById(R.id.lay_add_sale_stack);
            pro_id=itemView.findViewById(R.id.lay_add_sale_p_id);
            cardView=itemView.findViewById(R.id.lay_add_sale_card);
        }
    }
    @NonNull
    @Override
    public sale_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View v=layoutInflater.inflate(R.layout.lay_for_add_sale,parent,false);
        return new Invoice_main_adapter.sale_holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final sale_holder holder, int position) {
        cursor_for_main.moveToPosition(position);
        holder.pro_id.setText(String.valueOf(cursor_for_main.getInt(0)));
        holder.pro_name.setText(String.valueOf(cursor_for_main.getString(1)));
        holder.price.setText(String.valueOf(cursor_for_main.getString(2)));
        holder.stock.setText(String.valueOf(cursor_for_main.getInt(3)));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(context,Sale_datails.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                Common_database common_database=new Common_database(context);
            Cursor cursor=common_database.getSale_id(Integer.parseInt((String) holder.pro_id.getText()));
            cursor.moveToFirst();
            int sale_id_=cursor.getInt(0);
                it.putExtra("sale_id",sale_id_);
                context.startActivity(it);
            }
        });
    }
    @Override
    public int getItemCount() {
        return cursor_for_main.getCount();
    }
}

