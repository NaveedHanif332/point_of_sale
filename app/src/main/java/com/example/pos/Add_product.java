package com.example.pos;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Arrays;

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

public class Add_product extends AppCompatActivity {
ImageView btn_back;
Button btn_clear,btn_add;
    EditText pro_name,pro_cost,sale_price,units,category,discount,exp_date;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        btn_back=findViewById(R.id.back_add_product);
        btn_clear=findViewById(R.id.add_pro_btn_clr);
        btn_add=findViewById(R.id.add_pro_btn_add);
        btn_back=findViewById(R.id.back_add_product);
        //edit texts
        pro_name=findViewById(R.id.add_pro_p_name);
        pro_cost=findViewById(R.id.add_pro_p_cost);
        sale_price=findViewById(R.id.add_pro_p_sale_price);
        units=findViewById(R.id.add_pro_p_unit);
        category=findViewById(R.id.add_pro_p_cat);
        discount=findViewById(R.id.add_pro_p_discount);
        exp_date=findViewById(R.id.add_pro_p_expire_date);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView_pro);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        final AlertDialog.Builder alert=new AlertDialog.Builder(Add_product.this);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
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
                else{
                    Common_database database=new Common_database(Add_product.this) ;
                    database.getWritableDatabase();
                    database.insert_pro(pro_name.getText(),pro_cost.getText(),sale_price.getText(),units.getText(),category.getText(),
                            discount.getText(),exp_date.getText());
                    Toast.makeText(Add_product.this,"added",Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Add_product.this,"cleared",Toast.LENGTH_SHORT).show();

                pro_name.setText("");
                sale_price.setText("");
                pro_cost.setText("");
                units.setText("");
                category.setText("");
                exp_date.setText("");
                discount.setText("");
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Add_product.this,Product_Main.class);
                it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent it=new Intent(Add_product.this,Product_Main.class);
        it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
        this.finish();
        startActivity(it);
    }
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
