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
import static java.security.AccessController.getContext;
public class Account_main extends AppCompatActivity {
    RecyclerView ve;
    FloatingActionButton add_acc;
    ImageView btn_back;
    EditText searchView;
    ImageView go;
    ImageView btn_all;
    Cursor cursor_all_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_main);
        final Common_database database = new Common_database(Account_main.this);
        database.getWritableDatabase();
       cursor_all_main= database.getfor_acc_main();
       final Account_adapter[] acc_adpter = {new Account_adapter(this, cursor_all_main)};
        ve=findViewById(R.id.acc_rec);
        add_acc=findViewById(R.id.add_acc);
        btn_back=findViewById(R.id.back_main_account);
        searchView=findViewById(R.id.ser_account);
        go=findViewById(R.id.acc_go);
        btn_all=findViewById(R.id.acc_all);
        final Editable str=searchView.getText();
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c1;
                Common_database database = new Common_database(Account_main.this);
                database.getWritableDatabase();
                c1 = database.Search_acc(str);
                acc_adpter[0] = new Account_adapter(Account_main.this, c1);
                ve.setAdapter(acc_adpter[0]);
                RecyclerView.LayoutManager layoutManage = new LinearLayoutManager(Account_main.this);
                ve.setLayoutManager(layoutManage);
            }
        });
        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do usual work
                acc_adpter[0] = new Account_adapter(Account_main.this, cursor_all_main);
                ve.setAdapter(acc_adpter[0]);
                RecyclerView.LayoutManager layoutManage = new LinearLayoutManager(Account_main.this);
                ve.setLayoutManager(layoutManage);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Account_main.this,Naigation_drawer.class);
                it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it);
                finish();
            }
        });
        add_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Account_main.this,Add_Account.class);
                it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it);
            }
        });
        RecyclerView.LayoutManager layoutManage=new LinearLayoutManager(this);
        ve.setLayoutManager(layoutManage);
        ve.setAdapter(acc_adpter[0]);
    }

    @Override
    public void onBackPressed() {
        Intent it=new Intent(Account_main.this,Naigation_drawer.class);
        it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
        this.finish();
        startActivity(it);
    }
}
