package com.example.pos;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
public class Payment_main_adpter extends RecyclerView.Adapter<Payment_main_adpter.sale_holder> {
    Cursor  cursor_for_main;
    Context context;
    Payment_main_adpter(Context c, Cursor cr_for_main_pro) {
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
        return new Payment_main_adpter.sale_holder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull final sale_holder holder, int position) {
        cursor_for_main.moveToPosition(position);
        holder.pro_id.setText(String.valueOf(cursor_for_main.getInt(0)));
        holder.pro_name.setText(String.valueOf(cursor_for_main.getString(1)));
        holder.price.setText(String.valueOf(cursor_for_main.getString(2)));
        holder.stock.setText(String.valueOf(cursor_for_main.getInt(3)));
    }
    @Override
    public int getItemCount() {
        return cursor_for_main.getCount();
    }
}
