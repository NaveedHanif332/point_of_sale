package com.example.pos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

public class Update_product extends AppCompatActivity {
ImageView btn_back;
EditText pro_name,pro_cost,sale_price,units,category,discount,exp_date;
Button btn_update,btn_clear;
Intent intent;

   // final AlertDialog.Builder alert=new AlertDialog.Builder(Update_product.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        Intent it=getIntent();
        final String id=it.getStringExtra("id");
        Common_database database=new Common_database(this);
        database.getWritableDatabase();
        Cursor cursor=database.view_pro(id);
        btn_back=findViewById(R.id.back_update_product);
        btn_update=findViewById(R.id.update_pro_btn_add);
        btn_clear=findViewById(R.id.update_pro_btn_clr);
        //edit text
        pro_name=findViewById(R.id.update_pro_p_name);
        pro_cost=findViewById(R.id.update_pro_p_cast);
        sale_price=findViewById(R.id.update_pro_p_sale_price);
        units=findViewById(R.id.update_pro_p_unit);
        category=findViewById(R.id.update_pro_p_cat);
        discount=findViewById(R.id.update_pro_p_discount);
        exp_date=findViewById(R.id.update_pro_p_expire_date);



       cursor.moveToFirst();
       //setting the data
        pro_name.setText(String.valueOf(cursor.getString(0)));
        sale_price.setText(String.valueOf(cursor.getInt(1)));
        pro_cost.setText(String.valueOf(cursor.getInt(2)));
        units.setText(String.valueOf(cursor.getInt(3)));
        category.setText(String.valueOf(cursor.getString(4)));
        exp_date.setText(String.valueOf(cursor.getString(6)));
        discount.setText(String.valueOf(cursor.getFloat(5)));


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Update_product.this,Product_Main.class);
                it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it);
                finish();
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pro_name.getText().toString().trim().isEmpty())
                {
                    pro_name.setError("product name is required ");
                }
                else  if(pro_cost.getText().toString().trim().isEmpty())
                {
                    pro_cost.setError("product cast is required");
                }
                else if(sale_price.getText().toString().trim().isEmpty())
                {
                    sale_price.setError("sale price is required");
                }
                else if(units.getText().toString().trim().isEmpty())
                {
                    units.setError("units is required");
                }
                else if(exp_date.getText().toString().trim().isEmpty())
                {
                    exp_date.setError("Exp date is required");
                }
                else
                {
                    Toast.makeText(Update_product.this,"updated",Toast.LENGTH_SHORT).show();
                    Common_database database=new Common_database(Update_product.this);
                    database.Update(pro_name.getText(),pro_cost.getText(),sale_price.getText(),units.getText(),
                            category.getText(),exp_date.getText(),discount.getText(),id);
                    Toast.makeText(Update_product.this,"updated",Toast.LENGTH_SHORT).show();
                }



            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pro_name.setText("");
                sale_price.setText("");
                pro_cost.setText("");
                units.setText("");
                category.setText("");
                exp_date.setText("");
                discount.setText("");

                Toast.makeText(Update_product.this,"cleared",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent it=new Intent(Update_product.this,Product_Main.class);
        it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
        this.finish();
        startActivity(it);
    }
}
