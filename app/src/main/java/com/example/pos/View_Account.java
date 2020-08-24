package com.example.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

public class View_Account extends AppCompatActivity {

    ImageView btn_back;
    TextView view_acc_name,view_acc_type,view_acc_phone,view_acc_address,view_acc_debit,view_acc_credit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__account);
        Intent it=getIntent();
        String id=it.getStringExtra("id");
        Common_database database=new Common_database(this);
        database.getWritableDatabase();
        Cursor cursor=database.view_acc(id);
        //references
        btn_back=findViewById(R.id.back_view_acc);
        //text_view
        view_acc_name=findViewById(R.id.view_acc_name);
        view_acc_type=findViewById(R.id.view_acc_type);
        view_acc_phone=findViewById(R.id.view_acc_phone);
        view_acc_address=findViewById(R.id.view_acc_address);
        view_acc_debit=findViewById(R.id.view_acc_debit);
        view_acc_credit=findViewById(R.id.view_acc_credit);
        //setting data
        cursor.moveToFirst();
        view_acc_name.setText(String.valueOf(cursor.getString(0)));
        view_acc_type.setText(String.valueOf(cursor.getString(1)));
        view_acc_phone.setText(String.valueOf(cursor.getString(2)));
        view_acc_address.setText(String.valueOf(cursor.getString(3)));
        view_acc_debit.setText(String.valueOf(cursor.getInt(4)));
        view_acc_credit.setText(String.valueOf(cursor.getInt(5)));
        //on click
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(View_Account.this,Account_main.class);
                it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent it=new Intent(View_Account.this,Account_main.class);
        it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
        this.finish();
        startActivity(it);
    }
}
