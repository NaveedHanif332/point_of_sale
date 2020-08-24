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
public class Sale_adapter extends RecyclerView.Adapter<Sale_adapter.sale_holder> {
    Cursor  cursor_for_main;
    Context context;
    Sale_adapter(Context c, Cursor cr_for_main_pro) {
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
        return new Sale_adapter.sale_holder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull final sale_holder holder, int position) {
        cursor_for_main.moveToPosition(position);
        holder.pro_id.setText(String.valueOf(cursor_for_main.getInt(0)));
        holder.pro_name.setText(String.valueOf(cursor_for_main.getString(1)));
        holder.price.setText(String.valueOf(cursor_for_main.getInt(2)));
        holder.stock.setText(String.valueOf(cursor_for_main.getInt(3)));
       holder.cardView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, final View v, final ContextMenu.ContextMenuInfo menuInfo) {
                menu.add("select Account").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        //do what u want
                        Intent intent = new Intent("custom-message");
                        intent.putExtra("name",holder.pro_name.getText());
                        intent.putExtra("acc_id",holder.pro_id.getText());
                        intent.putExtra("check",true);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                        return true;
                    }
                });
                menu.add("select product").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        //do what u want
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Enter the Quantity");
                        final EditText input = new EditText(context);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                        input.setInputType(InputType.TYPE_CLASS_NUMBER );
                        builder.setView(input);
// Set up the buttons
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent it1=new Intent("pro_selection");
                                it1.putExtra("p_id",holder.pro_id.getText().toString());
                                it1.putExtra("p_name",holder.pro_name.getText().toString());
                                it1.putExtra("price",holder.price.getText().toString());
                                it1.putExtra("quantity",input.getText().toString());
                                LocalBroadcastManager.getInstance(context).sendBroadcast(it1);

                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();
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
