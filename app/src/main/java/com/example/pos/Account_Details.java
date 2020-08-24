package com.example.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

public class Account_Details extends AppCompatActivity {

    ImageView btn_back;
TextView view_pro_name,view_pro_cost,view_pro_price,view_pro_unit,view_pro_cat,view_pro_exp,view_pro_discount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account__details);

        Intent it=getIntent();
        String id=it.getStringExtra("id");
        Common_database database=new Common_database(this);
        Cursor cursor=database.view_pro(id);



        //references
        btn_back=findViewById(R.id.back_view_product);
        //text_view
        view_pro_name=findViewById(R.id.view_pro_p_name);
        view_pro_cost=findViewById(R.id.view_pro_p_cast);
        view_pro_price=findViewById(R.id.view_pro_p_sale_price);
        view_pro_unit=findViewById(R.id.view_pro_p_unit);
        view_pro_cat=findViewById(R.id.view_pro_p_cat);
        view_pro_exp=findViewById(R.id.view_pro_p_expire_date);
        view_pro_discount=findViewById(R.id.view_pro_p_discount);

//
        //setting data
      cursor.moveToFirst();
        view_pro_name.setText(String.valueOf(cursor.getString(0)));
         view_pro_price.setText(String.valueOf(cursor.getInt(1)));
        view_pro_cost.setText(String.valueOf(cursor.getInt(2)));

        view_pro_unit.setText(String.valueOf(cursor.getInt(3)));
        view_pro_cat.setText(String.valueOf(cursor.getString(4)));

        view_pro_exp.setText(String.valueOf(cursor.getString(6)));
        view_pro_discount.setText(String.valueOf(cursor.getFloat(5)));





        //on click
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it=new Intent(Account_Details.this,Product_Main.class);
                it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it);
            }
        });



    }
    @Override
    public void onBackPressed() {
        Intent it=new Intent(Account_Details.this,Account_main.class);
        it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
        this.finish();
        startActivity(it);
    }
}
