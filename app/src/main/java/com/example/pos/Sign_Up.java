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

public class Sign_Up extends AppCompatActivity {
    EditText name,password,cnic,email;
    Button sign_up;
    TextView link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);

        name=findViewById(R.id.sign_up_name);
        password=findViewById(R.id.sign_up_password);
        cnic=findViewById(R.id.sign_up_cnic);
        email=findViewById(R.id.sign_up_email);
        sign_up=findViewById(R.id.sign_up_btn);
        link=findViewById(R.id.sign_in_already_acc);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common_database common_database1 = new Common_database(Sign_Up.this);
                common_database1.getWritableDatabase();
                Cursor cursor_for_check = common_database1.get_total_use();
                if (cursor_for_check.getCount() == 0) {
                    if (name.getText().toString().trim().isEmpty()) {
                        name.setError("name is required");
                    } else if (password.getText().toString().trim().isEmpty()) {
                        password.setError("password is required");
                    } else if (cnic.getText().toString().trim().isEmpty()) {
                        cnic.setError("cnic is required");
                    } else if (email.getText().toString().trim().isEmpty()) {
                        email.setError("email is required");
                    } else {
                        Common_database common_database = new Common_database(Sign_Up.this);
                        common_database.insert_into_user(name.getText(), email.getText(), password.getText(), cnic.getText());
                        Intent it = new Intent(Sign_Up.this, Naigation_drawer.class);
                        it.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        Sign_Up.this.finish();
                        startActivity(it);
                    }
                }
                else {
                    Toast.makeText(Sign_Up.this,"you  have already a account",Toast.LENGTH_SHORT).show();
                }
            }
        });
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Sign_Up.this,Sign_in.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it);
                Sign_Up.this.finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Sign_Up.this.finish();
    }
}
