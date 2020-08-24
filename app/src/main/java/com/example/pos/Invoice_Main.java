package com.example.pos;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

public class Invoice_Main extends AppCompatActivity {
    ImageView btnback,search,inv_all;
    FloatingActionButton add_inv;
    EditText get_inv;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice__main);

        btnback=findViewById(R.id.back_main_invoice);
        search=findViewById(R.id.inv_go);
        inv_all=findViewById(R.id.inv_all);
        get_inv=findViewById(R.id.ser_invoice);
        recyclerView=findViewById(R.id.inv_rec);
         Common_database common_database=new Common_database(this);
         common_database.getWritableDatabase();
         final Cursor cursor=common_database.get_for_invoice_main();
        final Invoice_main_adapter[] invoice_main_adapters = {new Invoice_main_adapter(this, cursor)};

         recyclerView.setAdapter(invoice_main_adapters[0]);
         RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(Invoice_Main.this);
         recyclerView.setLayoutManager(layoutManager);

         btnback.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent it=new Intent(Invoice_Main.this,Naigation_drawer.class);
                 it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                 finish();
                 startActivity(it);
             }
         });
         //searching the data
        final Editable str=get_inv.getText();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c1;
                Common_database database = new Common_database(Invoice_Main.this);
                database.getWritableDatabase();
                c1 = database.search_inv(str);
                invoice_main_adapters[0] = new Invoice_main_adapter(Invoice_Main.this, c1);
                recyclerView.setAdapter(invoice_main_adapters[0]);
                RecyclerView.LayoutManager   layoutManage = new LinearLayoutManager(Invoice_Main.this);
                recyclerView.setLayoutManager(layoutManage);
            }
        });
        inv_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common_database common_database=new Common_database(Invoice_Main.this);
                common_database.getWritableDatabase();
                final Cursor cursor=common_database.get_for_invoice_main();
                invoice_main_adapters[0] = new Invoice_main_adapter(Invoice_Main.this, cursor);
                recyclerView.setAdapter(invoice_main_adapters[0]);
                RecyclerView.LayoutManager layoutManage = new LinearLayoutManager(Invoice_Main.this);
                recyclerView.setLayoutManager(layoutManage);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent it=new Intent(Invoice_Main.this,Naigation_drawer.class);
        it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
        this.finish();
        startActivity(it);
    }
}
