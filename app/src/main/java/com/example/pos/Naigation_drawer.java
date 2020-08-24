package com.example.pos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

public class Naigation_drawer extends AppCompatActivity {
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naigation_drawer);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout=findViewById(R.id.dr_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout, toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //changing the color
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        NavigationView navigationView=findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_profile:

                        Toast.makeText(Naigation_drawer.this,"profile ",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.logout:

                        Toast.makeText(Naigation_drawer.this,"logout",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_backup:

                        Toast.makeText(Naigation_drawer.this,"Back up",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_feedback:

                        Toast.makeText(Naigation_drawer.this,"feedback",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_contact:
                        Toast.makeText(Naigation_drawer.this,"contact us ",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_more_apps:
                        Toast.makeText(Naigation_drawer.this," More apps ",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(Naigation_drawer.this," wrong choise ",Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });







        ImageView img_pay=findViewById(R.id.im_payment_n);
        img_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it_pro=new Intent(Naigation_drawer.this,Payment_main.class);
                it_pro.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it_pro);

            }
        });
        CardView cardView_pay=findViewById(R.id.card_pay_n);
        cardView_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it_pro=new Intent(Naigation_drawer.this,Payment_main.class);
                it_pro.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it_pro);

            }
        });
        ImageView img_inv=findViewById(R.id.im_invc_n);
        img_inv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it_pro=new Intent(Naigation_drawer.this,Invoice_Main.class);
                it_pro.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it_pro);

            }
        });
        CardView cardView_inc=findViewById(R.id.card_inv_n);
        cardView_inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it_pro=new Intent(Naigation_drawer.this,Invoice_Main.class);
                it_pro.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it_pro);

            }
        });





        ImageView img_pro=findViewById(R.id.image_pro_n);
        img_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it_pro=new Intent(Naigation_drawer.this,Product_Main.class);
                it_pro.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it_pro);

            }
        });
        CardView cardView_pro=findViewById(R.id.card_pro_n);
        cardView_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it_pro=new Intent(Naigation_drawer.this,Product_Main.class);
                it_pro.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it_pro);

            }
        });

        ImageView img_acc=findViewById(R.id.im_acc_n);
        img_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it_pro=new Intent(Naigation_drawer.this,Account_main.class);
                it_pro.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it_pro);

            }
        });
        CardView cardView_acc=findViewById(R.id.card_acc_n);
        cardView_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it_pro=new Intent(Naigation_drawer.this,Account_main.class);
                it_pro.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it_pro);
            }
        });

        ImageView img_purchase=findViewById(R.id.im_pur_n);
        img_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it_pro=new Intent(Naigation_drawer.this,Add_product.class);
                it_pro.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it_pro);

            }
        });
        CardView cardView_purchase=findViewById(R.id.card_pur_n);
        cardView_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it_pro=new Intent(Naigation_drawer.this,Add_product.class);
                it_pro.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it_pro);
            }
        });

        ImageView img_sale=findViewById(R.id.im_sale);
        img_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it_pro=new Intent(Naigation_drawer.this,Sale_Main.class);
                it_pro.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it_pro);

            }
        });
        CardView cardView_sale=findViewById(R.id.card_sale_n);
        cardView_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it_pro=new Intent(Naigation_drawer.this,Sale_Main.class);
                it_pro.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(it_pro);

            }
        });
    }
    @Override
    public void onBackPressed() {
        Naigation_drawer.this.finish();
    }
}
