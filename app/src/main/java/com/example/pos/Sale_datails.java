package com.example.pos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Sale_datails extends AppCompatActivity {

    TextView acc_name,s_id,inv_id,total_product;
    RecyclerView recyclerView;
    ImageView btn_back;
    int sale_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_datails);
        s_id=findViewById(R.id.sale_details_sale_id);
        inv_id=findViewById(R.id.sale_details_inv_no);
        total_product=findViewById(R.id.sale_details_total_pro);
        recyclerView=findViewById(R.id.sale_datail_rec);
        btn_back=findViewById(R.id.back_details);


        Common_database common_database=new Common_database(this);
        Intent it=getIntent();
        sale_id=it.getIntExtra("sale_id",-1);
        Cursor c=common_database.get_for_sale_detail_rec(sale_id);
        Sale_datail_adapter sale_datail_adapter=new Sale_datail_adapter(this,c);
        recyclerView.setAdapter(sale_datail_adapter);
        RecyclerView.LayoutManager layoutManager=new  LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        Cursor cursor_inv_id=common_database.get_inv_id(sale_id);
        Cursor cursor_qty=common_database.get_qty(sale_id);


        s_id.setText(String.valueOf(sale_id));
        cursor_inv_id.moveToFirst();
        inv_id.setText(String.valueOf(cursor_inv_id.getInt(0)));
        cursor_qty.moveToFirst();
        total_product.setText(String.valueOf(cursor_qty.getInt(0)));


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Sale_datails.this,Sale_Main.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent it=new Intent(Sale_datails.this,Sale_Main.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(it);
    }
}
