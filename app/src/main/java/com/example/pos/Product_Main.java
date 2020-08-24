package com.example.pos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.internal.service.Common;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

public class Product_Main extends AppCompatActivity {
    RecyclerView ve;
    FloatingActionButton add_pro;
    ImageView btn_back;
  EditText searchView;
  ImageView go;
ImageView btn_all;
  Cursor cursor_all_main;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__main);


        final Common_database[] database = {new Common_database(this)};
        database[0].getWritableDatabase();
        cursor_all_main= database[0].getfor_pro_main();

        final Pro_Adpter[] pro_adpter = {new Pro_Adpter(this, cursor_all_main)};

        ve=findViewById(R.id.pro_rec);
        add_pro=findViewById(R.id.add_pro);
        btn_back=findViewById(R.id.back_main_product);
        searchView=findViewById(R.id.ser_product);
        go=findViewById(R.id.pro_go);
        btn_all=findViewById(R.id.pro_all);



      final Editable str=searchView.getText();

          go.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  Cursor c1;
                  Common_database database = new Common_database(Product_Main.this);
                  database.getWritableDatabase();
                  c1 = database.Search(str);
                  pro_adpter[0] = new Pro_Adpter(Product_Main.this, c1);
                  ve.setAdapter(pro_adpter[0]);
                  RecyclerView.LayoutManager   layoutManage = new LinearLayoutManager(Product_Main.this);
                  ve.setLayoutManager(layoutManage);


              }
          });

   btn_all.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {

               //do usual work
               pro_adpter[0] = new Pro_Adpter(Product_Main.this, cursor_all_main);
               ve.setAdapter(pro_adpter[0]);
               RecyclerView.LayoutManager layoutManage = new LinearLayoutManager(Product_Main.this);
               ve.setLayoutManager(layoutManage);

       }
   });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Product_Main.this,Naigation_drawer.class);
                it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it);
                finish();
            }
        });
        add_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Product_Main.this,Add_product.class);
                it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it);
            }
        });

        RecyclerView.LayoutManager layoutManage=new LinearLayoutManager(this);
        ve.setLayoutManager(layoutManage);
        ve.setAdapter(pro_adpter[0]);
    }
    @Override
    public void onBackPressed() {
        Intent it=new Intent(Product_Main.this,Naigation_drawer.class);
        it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
        this.finish();
        startActivity(it);
    }

}
