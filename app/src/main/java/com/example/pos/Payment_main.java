package com.example.pos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

public class Payment_main extends AppCompatActivity {
    FloatingActionButton add_pay;
    ImageView btn_back,go,all;
    EditText search_sale;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_main);

        add_pay=findViewById(R.id.add_pay);
        btn_back=findViewById(R.id.back_main_pay);
        go=findViewById(R.id.pay_go);
        all=findViewById(R.id.pay_all);
        search_sale=findViewById(R.id.ser_pay);
        recyclerView=findViewById(R.id.pay_rec_main);


        Common_database common_database=new Common_database(this);
        common_database.getWritableDatabase();
        Cursor cursor=common_database.get_for_pay_main();
        Payment_main_adpter sale_adapter_for_displaying=new Payment_main_adpter(this,cursor);
        recyclerView.setAdapter(sale_adapter_for_displaying);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(Payment_main.this);
        recyclerView.setLayoutManager(layoutManager);


        add_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Payment_main.this,Add_Payment.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Payment_main.this,Naigation_drawer.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it);
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str=search_sale.getText().toString();
                Common_database common_database=new Common_database(Payment_main.this);
                common_database.getWritableDatabase();
                Cursor  cursor=common_database.search_pay(str);
                Payment_main_adpter sale_adapter_for_displaying=new Payment_main_adpter(Payment_main.this,cursor);
                recyclerView.setAdapter(sale_adapter_for_displaying);
                RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(Payment_main.this);
                recyclerView.setLayoutManager(layoutManager);
            }
        });
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common_database common_database=new Common_database(Payment_main.this);
                common_database.getWritableDatabase();
                Cursor  cursor=common_database.get_for_pay_main();
                Payment_main_adpter sale_adapter_for_displaying=new Payment_main_adpter(Payment_main.this,cursor);
                recyclerView.setAdapter(sale_adapter_for_displaying);
                RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(Payment_main.this);
                recyclerView.setLayoutManager(layoutManager);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent it=new Intent(Payment_main.this,Naigation_drawer.class);
        it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
        this.finish();
        startActivity(it);
    }
}
