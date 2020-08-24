package com.example.pos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

public class Add_Account extends AppCompatActivity {
    ImageView btn_back;
    Button btn_clear,btn_add;
    EditText acc_name,acc_type,acc_phone,acc_address,acc_debit,acc_credit;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__account);
        btn_back=findViewById(R.id.back_add_acc);
        btn_clear=findViewById(R.id.add_acc_btn_clr);
        btn_add=findViewById(R.id.add_acc_btn_add);
        //edit texts
        acc_name=findViewById(R.id.add_acc_name);
        acc_type=findViewById(R.id.add_acc_type);
        acc_phone=findViewById(R.id.add_acc_phone);
        acc_address=findViewById(R.id.add_acc_address);
        acc_debit=findViewById(R.id.add_acc_debit);
        acc_credit=findViewById(R.id.add_acc_credit);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Toast.makeText(Add_Account.this, "Ad is loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
                Toast.makeText(Add_Account.this, "Ad is not Loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
        final AlertDialog.Builder alert=new AlertDialog.Builder(Add_Account.this);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(acc_name.getText().toString().isEmpty())
                {
                    acc_name.setError("Name is required field");
                }
                else if(acc_type.getText().toString().trim().isEmpty())
                {
                    acc_type.setError("Account is required field");
                }
                else if(acc_phone.getText().toString().trim().isEmpty())
                {
                    acc_phone.setError("phone no  is required field");
                }
               else{

                    Common_database database=new Common_database(Add_Account.this) ;
                    database.insert_acc(acc_name.getText(),acc_type.getText(),acc_phone.getText(),acc_address.getText(),acc_debit.getText(),acc_credit.getText());
                    Toast.makeText(Add_Account.this,"added",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Add_Account.this,"cleared",Toast.LENGTH_SHORT).show();
                acc_name.setText("");
                acc_type.setText("");
                acc_phone.setText("");
                acc_address.setText("");
                acc_debit.setText("");
                acc_credit.setText("");
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Add_Account.this,Account_main.class);
                it.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent it=new Intent(Add_Account.this,Account_main.class);
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
