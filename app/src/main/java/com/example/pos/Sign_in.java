package com.example.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Sign_in extends AppCompatActivity {

    EditText name,password,cnic;
    Button sign_in;
    TextView  link,for_got;
    boolean var=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        name=findViewById(R.id.sign_in_name);
        password=findViewById(R.id.sign_in_passward);
        cnic=findViewById(R.id.sign_in_cnic);
        for_got=findViewById(R.id.sign_in_forget_password);
        link=findViewById(R.id.link_sign_up);
        sign_in=findViewById(R.id.sign_in_btn);
        cnic.setVisibility(View.INVISIBLE);

       sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(name.getText().toString().trim().isEmpty())
                {
                    name.setError("name is required");
                }
                else if(password.getText().toString().trim().isEmpty() && !var)
                {
                    password.setError("password is required");
                }
                else if(cnic.getText().toString().trim().isEmpty() && var)
                {
                    cnic.setError("cnic is required");
                }
                else
                {
                    Common_database common_database=new Common_database(Sign_in.this);
                    common_database.getWritableDatabase();
                    Cursor cursor=common_database.get_for_sign_in();
                    cursor.moveToFirst();
                    if(var)
                    {
                        if(name.getText().toString().trim().equals(cursor.getString(0)) && cnic.getText().toString().trim().equals(cursor.getString(2)))
                        {
                            //move to n
                            Intent it=new Intent(Sign_in.this,Naigation_drawer.class);
                            it.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            Sign_in.this.finish();
                            startActivity(it);
                        }
                        else
                        {
                            Toast.makeText(Sign_in.this,"Incorrect name or cnic",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(!var)
                    {
                        if(name.getText().toString().trim().equals(cursor.getString(0)) && password.getText().toString().trim().equals(cursor.getString(1)))
                        {//move to n
                            Intent it=new Intent(Sign_in.this,Naigation_drawer.class);
                            it.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            Sign_in.this.finish();
                            startActivity(it);
                        }
                        else
                        {
                            Toast.makeText(Sign_in.this,"Incorrect name or password",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        for_got.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             password.setVisibility(View.GONE);
             cnic.setVisibility(View.VISIBLE);
             for_got.setVisibility(View.GONE);
             var=true;
            }
        });
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Sign_in.this,Sign_Up.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it);
                Sign_in.this.finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Sign_in.this.finish();
    }
}
