package com.example.pos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

public class Update_account extends AppCompatActivity {
    ImageView btn_back;
    EditText acc_name,type,phone,address,debit,credit;
    Button btn_update,btn_clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);

        Intent it=getIntent();
        final String id=it.getStringExtra("id");
        Common_database database=new Common_database(this);
        database.getWritableDatabase();
        Cursor cursor=database.view_acc(id);






        btn_back=findViewById(R.id.back_update_acc);
        btn_update=findViewById(R.id.update_btn_acc);
        btn_clear=findViewById(R.id.update_btn_clr);
        //edit text
        acc_name=findViewById(R.id.update_name);
        type=findViewById(R.id.update_type);
        phone=findViewById(R.id.update_phone);
        address=findViewById(R.id.update_address);
        debit=findViewById(R.id.update_debit);
        credit=findViewById(R.id.update_credit);

        cursor.moveToFirst();
        //setting the data
        acc_name.setText(String.valueOf(cursor.getString(0)));
        type.setText(String.valueOf(cursor.getString(1)));
        phone.setText(String.valueOf(cursor.getString(2)));
        address.setText(String.valueOf(cursor.getString(3)));
        debit.setText(String.valueOf(cursor.getInt(4)));
        credit.setText(String.valueOf(cursor.getInt(5)));



        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Update_account.this,Account_main.class);
                it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it);
                finish();
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(acc_name.getText().toString().isEmpty())
                {
                    acc_name.setError("Name is required field");
                }
                else if(type.getText().toString().trim().isEmpty())
                {
                    type.setError("Account is required field");
                }
                else if(phone.getText().toString().trim().isEmpty())
                {
                    phone.setError("phone no  is required field");
                }
                else
                {
                    Common_database database=new Common_database(Update_account.this);
                    database.getWritableDatabase();
                    database.Update_acc(acc_name.getText(),type.getText(),phone.getText(),address.getText(),debit.getText(),credit.getText(),id);
                    Toast.makeText(Update_account.this,"updated",Toast.LENGTH_SHORT).show();
                }


            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                acc_name.setText("");
                type.setText("");
                phone.setText("");
                address.setText("");
                debit.setText("");
                credit.setText("");
                Toast.makeText(Update_account.this,"cleared",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent it=new Intent(Update_account.this,Account_main.class);
        it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
        this.finish();
        startActivity(it);
    }
}
