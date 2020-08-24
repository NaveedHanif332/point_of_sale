package com.example.pos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

public class Add_sale extends AppCompatActivity {
    ImageView pro_search,acc_search,btn_back;
    Button btn_save,btn_finish;
    RecyclerView recyclerView,rec_for_demo;
    TextView acc_name,total_ammount,final_amount ,acc_name_heading;
    Spinner spinner;
    EditText gettxt_pro,gettxt_acc,discount;
    List<String> p_name;
    List<String> p_price;
    List<String> p_quantity;
    List<String > p_sub_total;
    List<String > p_id;
    LinearLayout linearLayout,linearLayout_cal;
    int total_sum;
    int acc_id_for_acc_sale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sale);

        btn_save=findViewById(R.id.btn_sale_save);
        spinner=findViewById(R.id.sale_spinner);
        recyclerView=findViewById(R.id.rec_sale_pro);
        pro_search=findViewById(R.id.pro_go_for_sale);
        gettxt_pro=findViewById(R.id.ser_pro_for_sale);
        acc_name=findViewById(R.id.add_acc_sale);
        acc_search=findViewById(R.id.acc_go_for_sale);
        gettxt_acc=findViewById(R.id.ser_account_for_sale);
        rec_for_demo=findViewById(R.id.rec_for_demo);
        btn_finish=findViewById(R.id.btn_sale_finish);
        linearLayout=findViewById(R.id.heading);
        linearLayout_cal=findViewById(R.id.linear_lay_cal);
        total_ammount=findViewById(R.id.total_amount);
        final_amount=findViewById(R.id.final_amount);
        btn_finish.setVisibility(View.INVISIBLE);
        discount=findViewById(R.id.discount);
        btn_back=findViewById(R.id.back_new_sale);
        acc_name_heading=findViewById(R.id.account_name_heading);

        p_name=new ArrayList<>();
        p_price=new ArrayList<String>();
        p_quantity=new ArrayList<String>();
        p_sub_total=new ArrayList<>();
        p_id=new ArrayList<>();

        acc_name.setVisibility(View.GONE);
        linearLayout.setVisibility(View.INVISIBLE);
        btn_save.setVisibility(View.INVISIBLE);
        linearLayout_cal.setVisibility(View.INVISIBLE);

        String [] str ={"","Counter","Account"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(Add_sale.this,R.layout.support_simple_spinner_dropdown_item,str);
        spinner.setAdapter(arrayAdapter);

       if(spinner.getSelectedItem().toString().equals(str[1]))
       {
           gettxt_acc.setVisibility(View.GONE);
           acc_name.setVisibility(View.GONE);
           acc_name_heading.setVisibility(View.GONE);
       }
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Add_sale.this,Sale_Main.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it);
            }
        });
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_finish.setVisibility(View.GONE);
                 total_sum = 0;
                for(int i=0;i<p_sub_total.size();i++)
                {
                    total_sum=total_sum+Integer.parseInt(p_sub_total.get(i));
                }
                total_ammount.setText(String.valueOf(total_sum));
                linearLayout_cal.setVisibility(View.VISIBLE);
                btn_save.setVisibility(View.VISIBLE);
            }
        });
        BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // Get extra data included in the Intent
                String name = intent.getStringExtra("name");
                String id=intent.getStringExtra("acc_id");
                acc_id_for_acc_sale= Integer.parseInt(id);
                boolean var = intent.getBooleanExtra("check",false);
                if(var) {
                    acc_name.setVisibility(View.VISIBLE);
                    acc_name.setText(name);
                    acc_search.setVisibility(View.GONE);
                    gettxt_acc.setVisibility(View.GONE);
                    rec_for_demo.setVisibility(View.GONE);
                }
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,new IntentFilter("custom-message"));
        BroadcastReceiver pro_receiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
             String quantity=intent.getStringExtra("quantity");
             String price=intent.getStringExtra("price");
             String  sub_total=String.valueOf(Integer.parseInt(price)*Integer.parseInt(quantity));
             String name=intent.getStringExtra("p_name");
                String id=intent.getStringExtra("p_id");
              linearLayout.setVisibility(View.VISIBLE);
              btn_finish.setVisibility(View.VISIBLE);
             p_name.add(name);
             p_id.add(id);
             p_price.add(price);
             p_quantity.add(quantity);
             p_sub_total.add(sub_total);

             ListAdpter_pro listAdpter_pro=new ListAdpter_pro(context,p_name,p_price,p_quantity,p_sub_total);
             recyclerView.setAdapter(listAdpter_pro);
             RecyclerView.LayoutManager layoutManage = new LinearLayoutManager(Add_sale.this);
             recyclerView.setLayoutManager(layoutManage);
             rec_for_demo.setVisibility(View.GONE);
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(pro_receiver,new IntentFilter("pro_selection"));
        acc_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rec_for_demo.setVisibility(View.VISIBLE);
                Common_database common_database=new Common_database(Add_sale.this);
                common_database.getWritableDatabase();
                Cursor cursor=common_database.get_acc_name(gettxt_acc.getText());
                Sale_adapter sale_adapter=new Sale_adapter(Add_sale.this,cursor);
                rec_for_demo.setAdapter(sale_adapter);
                RecyclerView.LayoutManager layoutManage = new LinearLayoutManager(Add_sale.this);
                rec_for_demo.setLayoutManager(layoutManage);
            }
        });
        pro_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Common_database common_database=new Common_database(Add_sale.this);
                common_database.getWritableDatabase();
                Editable str= gettxt_pro.getText();
                Cursor cursor=common_database.Search(str);
                Sale_adapter sale_adapter=new Sale_adapter(Add_sale.this,cursor);
                rec_for_demo.setVisibility(View.VISIBLE);
                rec_for_demo.setAdapter(sale_adapter);
                RecyclerView.LayoutManager layoutManage = new LinearLayoutManager(Add_sale.this);
                rec_for_demo.setLayoutManager(layoutManage);
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final_amount.setText(String.valueOf(total_sum - Integer.parseInt(String.valueOf(discount.getText()))));
                Toast.makeText(Add_sale.this, "saved", Toast.LENGTH_SHORT).show();
                    Common_database common_database = new Common_database(Add_sale.this);
                    common_database.getWritableDatabase();
                    Cursor cursor = common_database.get_sale_id();
                    cursor.moveToFirst();
                    int sale_id = cursor.getCount();
                    sale_id++;
                    for (int i = 0; i < p_id.size(); i++) {
                        common_database.insert_into_sale_pro(sale_id, Integer.parseInt(p_id.get(i)), Integer.parseInt(p_quantity.get(i)));
                    }
                    //entry in sale_invoice
                    common_database.insert_into_sale_invoice(sale_id);
                    //entry in invoice
                    Cursor cur_for_inv_no = common_database.getInvoiceNo(sale_id);
                    cur_for_inv_no.moveToFirst();
                    int inv_id = cur_for_inv_no.getInt(0);
                    common_database.insert_into_invoice(inv_id, p_name.size(), Integer.parseInt(String.valueOf(discount.getText())),
                            Integer.parseInt(String.valueOf(final_amount.getText())));
                    //entry into account_sale
                    //updating  the  credit
                    if (spinner.getSelectedItem().toString().equals("Account")) {
                        common_database.insert_into_sale_account(acc_id_for_acc_sale, sale_id);
                        common_database.update_acc_credit(acc_id_for_acc_sale, Integer.parseInt(String.valueOf(final_amount.getText())));
                    }
                    //updating the stack
                    for (int i = 0; i < p_name.size(); i++) {
                        common_database.updateproduct(Integer.parseInt(p_id.get(i)), Integer.parseInt(p_quantity.get(i)));
                    }
                    //the moving to the sale main activity
                    Intent it = new Intent(Add_sale.this, Sale_Main.class);
                    it.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(it);
                }
        });
    }

    @Override
    public void onBackPressed() {
        Intent it=new Intent(Add_sale.this,Sale_Main.class);
        it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
        this.finish();
        startActivity(it);
    }
}
