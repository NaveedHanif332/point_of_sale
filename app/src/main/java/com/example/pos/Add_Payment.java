package com.example.pos;

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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Add_Payment extends AppCompatActivity {
    TextView acc_name;
    EditText amount,search_acc;
    Spinner mode_spinner,type_spinner;
    ImageView acc_search,btn_back;
    Button btn_save;
    RecyclerView recyclerView;
    int acc_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__payment);

        acc_name=findViewById(R.id.add_acc_pay);
        amount=findViewById(R.id.pay_amount);
        search_acc=findViewById(R.id.ser_account_for_pay);
        mode_spinner=findViewById(R.id.pay_spinner);
        type_spinner=findViewById(R.id.pay_spinner_for_type);
        acc_search=findViewById(R.id.acc_go_for_pay);
        btn_save=findViewById(R.id.btn_save_pay);
        recyclerView=findViewById(R.id.add_pay_rec);
        btn_back=findViewById(R.id.back_add_pay);


        final String [] str ={"","Receive","Pay"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(Add_Payment.this,R.layout.support_simple_spinner_dropdown_item,str);
        mode_spinner.setAdapter(arrayAdapter);

        final String [] str1 ={"","Cheque","Cash"};
        ArrayAdapter<String> arrayAdapter_type=new ArrayAdapter<String>(Add_Payment.this,R.layout.support_simple_spinner_dropdown_item,str1);
        type_spinner.setAdapter(arrayAdapter_type);
        recyclerView.setVisibility(View.GONE);
        acc_name.setVisibility(View.INVISIBLE);
        acc_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setVisibility(View.VISIBLE);
                Common_database common_database=new Common_database(Add_Payment.this);
                common_database.getWritableDatabase();
                Cursor cursor=common_database.get_acc_name(search_acc.getText());
                Add_pay_adapter sale_adapter=new Add_pay_adapter(Add_Payment.this,cursor);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setAdapter(sale_adapter);
                RecyclerView.LayoutManager layoutManage = new LinearLayoutManager(Add_Payment.this);
                recyclerView.setLayoutManager(layoutManage);
            }
        });
        BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // Get extra data included in the Intent
                String name = intent.getStringExtra("name");
                String id=intent.getStringExtra("acc_id");
                acc_id=Integer.parseInt(id);
                boolean var = intent.getBooleanExtra("check",false);
                if(var) {
                    acc_name.setVisibility(View.VISIBLE);
                    acc_name.setText(name);
                    acc_search.setVisibility(View.GONE);
                    search_acc.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                }
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,new IntentFilter("acc_payment_result"));

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    String type = type_spinner.getSelectedItem().toString();
                    int amount_ = Integer.parseInt(amount.getText().toString());
                    Common_database common_database = new Common_database(Add_Payment.this);
                    common_database.getWritableDatabase();
                    common_database.insert_pay(acc_id, amount_, type);
                    //updating the accounts debit or credit
                    if (mode_spinner.getSelectedItem().toString().equals(str[1])) {
                        //update the debit
                        common_database.update_acc_debit(acc_id, Integer.parseInt(String.valueOf(amount.getText())));
                    } else {
                        common_database.update_acc_credit(acc_id, Integer.parseInt(String.valueOf(amount.getText())));
                    }
                }

        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Add_Payment.this,Payment_main.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent it=new Intent(Add_Payment.this,Payment_main.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(it);
    }
}
