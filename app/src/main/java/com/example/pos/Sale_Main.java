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

public class Sale_Main extends AppCompatActivity {
    FloatingActionButton  add_sale;
    ImageView      btn_back,go,all;
    EditText search_sale;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale__main);
        add_sale=findViewById(R.id.add_sale);
        btn_back=findViewById(R.id.back_main_sale);
        go=findViewById(R.id.sale_go);
        all=findViewById(R.id.sale_all);
        search_sale=findViewById(R.id.ser_sale);
        recyclerView=findViewById(R.id.sale_rec);


        Common_database common_database=new Common_database(this);
        common_database.getWritableDatabase();
        Cursor  cursor=common_database.getfor_sale_main();
        Sale_adapter_for_displaying sale_adapter_for_displaying=new Sale_adapter_for_displaying(this,cursor);
        recyclerView.setAdapter(sale_adapter_for_displaying);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(Sale_Main.this);
        recyclerView.setLayoutManager(layoutManager);
        add_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Sale_Main.this,Add_sale.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Sale_Main.this,Naigation_drawer.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it);
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str=search_sale.getText().toString();
                Common_database common_database=new Common_database(Sale_Main.this);
                common_database.getWritableDatabase();
                Cursor  cursor=common_database.search_sale(str);
                Sale_adapter_for_displaying sale_adapter_for_displaying=new Sale_adapter_for_displaying(Sale_Main.this,cursor);
                recyclerView.setAdapter(sale_adapter_for_displaying);
                RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(Sale_Main.this);
                recyclerView.setLayoutManager(layoutManager);
            }
        });
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common_database common_database=new Common_database(Sale_Main.this);
                common_database.getWritableDatabase();
                Cursor  cursor=common_database.getfor_sale_main();
                Sale_adapter_for_displaying sale_adapter_for_displaying=new Sale_adapter_for_displaying(Sale_Main.this,cursor);
                recyclerView.setAdapter(sale_adapter_for_displaying);
                RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(Sale_Main.this);
                recyclerView.setLayoutManager(layoutManager);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent it=new Intent(Sale_Main.this,Naigation_drawer.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        this.finish();
        startActivity(it);
    }
}
