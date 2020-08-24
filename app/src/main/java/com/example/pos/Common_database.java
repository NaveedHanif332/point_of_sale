package com.example.pos;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Common_database extends SQLiteOpenHelper {
    String pro_cl0="P_id";
    String pro_cl1="P_name";
    String pro_cl2="P_cost";
    String pro_cl3="sale_price";
    String pro_cl4="units";
    String pro_cl5="p_category";
    String pro_cl6="Exp_date";
    String pro_cl7="Discount";

    //Account table
    String acc_cl0="Acc_id";
    String acc_cl1="Acc_name";
    String acc_cl2="Acc_type";
    String acc_cl3="phone_no";
    String acc_cl4="address";
    String acc_cl5="debit";
    String acc_cl6="credit";

    //sale product
    String sale_pro_cl0="sale_id";
    String sale_pro_cl1="pro_id";
    String sale_pro_cl2="pro_qty";
    //SALE INVOICE
    String sale_inv_cl0="inv_id";
    String sale_inv_cl1="sale_id";
    //INVOICE
    String inv_cl0="inv_id";
    String inv_cl1="inv_date";
    String inv_cl2="total_product";
    String inv_cl3="discount";
    String inv_cl4="total_amount";
    //ACCOUNT-SALE
    String sale_account_cl0="acc_id";
    String sale_account_cl1="sale_id";

    //payment
    String pay_cl0="pay_id";
    String pay_cl1="pay_date";
    String pay_cl2="pay_type";
    String pay_cl3="amount";
    String pay_cl4="account_name";

    //sign_login
    String sign_login_cl0="user_id";
    String sign_login_cl1="name";
    String sign_login_cl2="email";
    String sign_login_cl3="password";
    String sign_login_cl4="cnic";

    public Context context;
    public Common_database(Context context ) {
        super(context, "Point_of_sale.db", null, 1);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE PRODUCT (P_id INTEGER PRIMARY KEY AUTOINCREMENT,P_name VARCHAR(20) , P_cost INT," +
                "sale_price INT ,units INT ,p_category VARCHAR(20),Exp_date DATE ,Discount DECIMAL(4,2))");
        sqLiteDatabase.execSQL("CREATE TABLE ACCOUNT(Acc_id INTEGER PRIMARY KEY AUTOINCREMENT,Acc_name VARCHAR(20) , Acc_type varchar(20)," +
                "phone_no varchar(20) ,address varchar(100) ,debit int,credit int )");

        sqLiteDatabase.execSQL("CREATE TABLE SALE_PRODUCT(sale_id int not null,pro_id ,pro_qty int not null " +
                                  ",foreign key(pro_id) References  PRODUCT(P_id) ON UPDATE CASCADE ON DELETE NO ACTION, " +
                                    "primary key(sale_id,pro_id) )");
        sqLiteDatabase.execSQL("CREATE TABLE SALE_INVOICE(inv_id INTEGER PRIMARY KEY AUTOINCREMENT,sale_id int not null ," +
                                                            "foreign key(sale_id) references SALE_PRODUCT(sale_id) ON UPDATE CASCADE ON DELETE NO ACTION)");
        sqLiteDatabase.execSQL("CREATE TABLE INVOICE(inv_id INTEGER NOT NULL PRIMARY KEY,inv_date DATE NOT NULL ," +
                                                            "total_product INT,discount INT ,total_amount INT ," +
                                                              "FOREIGN KEY(inv_id) REFERENCES SALE_INVOICE(inv_id) ON UPDATE CASCADE ON DELETE NO ACTION)");
        sqLiteDatabase.execSQL("CREATE TABLE SALE_ACC(acc_id INTEGER PRIMARY KEY,sale_id int not null," +
                                                            "foreign key (acc_id) REFERENCES ACCOUNT(Acc_id) ON UPDATE CASCADE ON DELETE NO ACTION," +
                                                            " FOREIGN KEY  (sale_id)REFERENCES SALE_PRODUCT(sale_id)ON UPDATE CASCADE ON DELETE NO ACTION)");

       sqLiteDatabase.execSQL("CREATE TABLE PAYMENT(pay_id INTEGER PRIMARY KEY AUTOINCREMENT ,pay_date DATE NOT NULL," +
                                            "pay_type varchar(20),amount int ,account_name varchar(20),foreign key(account_name) references ACCOUNT(Acc_name)ON UPDATE CASCADE ON DELETE NO ACTION)");

       sqLiteDatabase.execSQL(" CREATE TABLE User(user_id INTEGER PRIMARY KEY AUTOINCREMENT,name varchar(20) not null," +
                                    "email varchar(40) not null,password varchar not null,cnic varchar(30) not null) ");
        Toast.makeText(context, "table created ", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table  if exists PRODUCT");
        sqLiteDatabase.execSQL("drop table  if exists ACCOUNT");
        sqLiteDatabase.execSQL("drop table  if exists SALE_PRODUCT");
        sqLiteDatabase.execSQL("drop table  if exists SALE_INVOICE");
        sqLiteDatabase.execSQL("drop table  if exists INVOICE");
        sqLiteDatabase.execSQL("drop table  if exists SALE_ACC");
        sqLiteDatabase.execSQL("drop table  if exists PAYMENT ");
        sqLiteDatabase.execSQL("drop table  if exists User ");
        onCreate(sqLiteDatabase);
    }
    public  void insert_into_user(Editable name,Editable email,Editable passward,Editable cnic)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(sign_login_cl1, String.valueOf(name));
        contentValues.put(sign_login_cl2, String.valueOf(email));
        contentValues.put(sign_login_cl3, String.valueOf(passward));
        contentValues.put(sign_login_cl4, String.valueOf(cnic));
        sqLiteDatabase.insert("User",null,contentValues);
    }
    public Cursor get_for_sign_in()
    {
        Cursor cursor;
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        cursor=sqLiteDatabase.rawQuery("SELECT name,password,cnic from User",null);
        return cursor;
    }
    public   void insert_pay(int acc_id, int amount, String type)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(pay_cl1, String.valueOf(getdate()));
        contentValues.put(pay_cl2, String.valueOf(type));
        contentValues.put(pay_cl3, String.valueOf(amount));
        contentValues.put(pay_cl4, String.valueOf(acc_id));
        long test=sqLiteDatabase.insert("PAYMENT",null,contentValues);
        if(test==-1) {
            Toast.makeText(context, "PAYMENT  is not  Added", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
        }
    }
    public  Cursor get_for_pay_main()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT pay_id,pay_date,account_name,amount from PAYMENT" ,null);
        return cursor;
    }

    public  Cursor getfor_sale_main()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(" select sale_id ,inv_date,INVOICE.inv_id,total_amount FROM INVOICE ,SALE_INVOICE\n" +
                "    WHERE INVOICE.inv_id=SALE_INVOICE.inv_id" ,null);

        return cursor;
    }
    public  Cursor get_for_sale_detail_rec(int sale_id)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(" select PRODUCT.P_name,SALE_PRODUCT.pro_qty FROM PRODUCT ,SALE_PRODUCT " +
                                                "WHERE PRODUCT.P_id=SALE_PRODUCT.pro_id AND SALE_PRODUCT.sale_id="+sale_id+"" ,null);
        return cursor;

    }

    public Cursor get_sale_id()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select *  from SALE_PRODUCT" ,null);
        return  cursor;
    }
    public  Cursor getInvoiceNo(int sale_id)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select inv_id  from SALE_INVOICE where sale_id="+sale_id+"" ,null);

        return cursor;
    }
public  Cursor get_inv_id(int sale_id)
{
    SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
    Cursor cursor=sqLiteDatabase.rawQuery("select inv_id from SALE_INVOICE WHERE sale_id="+sale_id+"",null);
    return cursor;
}
public  Cursor getSale_id(Integer inv_id)
{
    SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
    Cursor cursor=sqLiteDatabase.rawQuery("select sale_id from SALE_INVOICE WHERE inv_id="+inv_id+"",null);
    return cursor;
}
public Cursor get_qty(int sale_id)
{
    SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
    Cursor cursor=sqLiteDatabase.rawQuery("SELECT Sum(pro_qty) from SALE_PRODUCT where sale_id="+sale_id+"",null);
    return cursor;
}
public  String getdate()
{
    SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
    Date date=new Date();
    return dateFormat.format(date);
}

    public  void insert_into_sale_pro(int sale_id,int p_id,int qty)
    {
         SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
         ContentValues contentValues=new ContentValues();
         contentValues.put(sale_pro_cl0,sale_id);
         contentValues.put(sale_pro_cl1,p_id);
        contentValues.put(sale_pro_cl2,qty);

         sqLiteDatabase.insert("SALE_PRODUCT",null,contentValues);
    }


    public void insert_into_sale_invoice(int sale_id)
    {
         SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
         ContentValues contentValues=new ContentValues();
         contentValues.put(sale_inv_cl1,sale_id);
         sqLiteDatabase.insert("SALE_INVOICE",null,contentValues);
    }
    public void insert_into_invoice(int inv_id,int total_product,int discount,int amount)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(inv_cl0,inv_id);
        contentValues.put(inv_cl1,(getdate()));
        contentValues.put(inv_cl2,total_product);
        contentValues.put(inv_cl3,discount);
        contentValues.put(inv_cl4,amount);
        sqLiteDatabase.insert("INVOICE",null,contentValues);
    }
    public  void insert_into_sale_account(int acc_id,int sale_id)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(sale_account_cl0,acc_id);
        contentValues.put(sale_account_cl1,sale_id);
        sqLiteDatabase.insert("SALE_ACC",null,contentValues);
    }


    public   void insert_pro(Editable name, Editable cost, Editable sale_price,
                             Editable units, Editable category, Editable Exp_date, Editable discount)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(pro_cl1, String.valueOf(name));
        contentValues.put(pro_cl2, String.valueOf(cost));
        contentValues.put(pro_cl3, String.valueOf(sale_price));
        contentValues.put(pro_cl4, String.valueOf(units));
        contentValues.put(pro_cl5, String.valueOf(category));
        contentValues.put(pro_cl6, String.valueOf(Exp_date));
        contentValues.put(pro_cl7, String.valueOf(discount));
        long test=sqLiteDatabase.insert("PRODUCT",null,contentValues);
        if(test==-1) {
            Toast.makeText(context, "Product  is not  Added", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
        }
    }
    public   void insert_acc(Editable name, Editable type, Editable phone,
                             Editable address, Editable debit, Editable credit)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(acc_cl1, String.valueOf(name));
        contentValues.put(acc_cl2, String.valueOf(type));
        contentValues.put(acc_cl3, String.valueOf(phone));
        contentValues.put(acc_cl4, String.valueOf(address));
        contentValues.put(acc_cl5, String.valueOf(debit));
        contentValues.put(acc_cl6, String.valueOf(credit));
        long test=sqLiteDatabase.insert("ACCOUNT",null,contentValues);
        if(test==-1) {
            Toast.makeText(context, "account  is not  Added", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
        }
    }
    public final Cursor getfor_pro_main()
    {
        Cursor cursor;
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        cursor=sqLiteDatabase.rawQuery("select p_id,p_name,sale_price,units from PRODUCT",null);
        return cursor;
    }

    public final Cursor getfor_acc_main()
    {
        Cursor cursor;
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        cursor=sqLiteDatabase.rawQuery("select Acc_id,Acc_name,debit,credit from ACCOUNT",null);
        return cursor;
    }

    void delete_pro(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("Product", "P_id=?", new String[]{String.valueOf(id)});

        Cursor cursor=sqLiteDatabase.rawQuery("select * from PRODUCT",null);
        if(cursor.getCount()==0)
        {
            sqLiteDatabase.execSQL("Delete  from PRODUCT");
            Toast.makeText(context,"ALL Product are deleted",Toast.LENGTH_SHORT).show();
        }
    }


    public Cursor view_pro(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT P_name,sale_price,P_cost,units,p_category,Exp_date,Discount FROM PRODUCT WHERE P_id="+id+"",null);
        return cursor;
    }


    public Cursor view_acc(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT Acc_name,Acc_type,phone_no,address,debit,credit FROM ACCOUNT WHERE Acc_id="+id+"",null);
        return cursor;
    }



    public void Update(Editable name, Editable cost, Editable sale_price, Editable units,
                       Editable category,Editable Exp_date,Editable discount ,String id) {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(pro_cl1, String.valueOf(name));
        contentValues.put(pro_cl2, String.valueOf(cost));
        contentValues.put(pro_cl3, String.valueOf(sale_price));
        contentValues.put(pro_cl4, String.valueOf(units));
        contentValues.put(pro_cl5, String.valueOf(category));
        contentValues.put(pro_cl6, String.valueOf(Exp_date));
        contentValues.put(pro_cl7, String.valueOf(discount));
        sqLiteDatabase.update("PRODUCT",contentValues,"P_id=?",new String[]{id});
    }
    public  Cursor Search(Editable str)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(
                "Select p_id,p_name,sale_price,units from PRODUCT WHERE p_name LIKE '%" + str + "%'",
                null);
        return cursor;
    }



    public  Cursor Search_acc(Editable str)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(
                "Select Acc_id,Acc_name,debit,credit from ACCOUNT WHERE  Acc_name LIKE '%" + str + "%'",
                null);
        return cursor;
    }
    public void Update_acc(Editable name, Editable type, Editable phone, Editable Address, Editable debit,
                           Editable credit, String id) {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(acc_cl1, String.valueOf(name));
        contentValues.put(acc_cl2, String.valueOf(type));
        contentValues.put(acc_cl3, String.valueOf(phone));
        contentValues.put(acc_cl4, String.valueOf(Address));
        contentValues.put(acc_cl5, String.valueOf(debit));
        contentValues.put(acc_cl6, String.valueOf(credit));
        sqLiteDatabase.update("ACCOUNT",contentValues,"Acc_id=?",new String[]{id});
    }

    public void delete_acc(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("Account", "Acc_id=?", new String[]{String.valueOf(id)});

    }

    public Cursor get_acc_name(Editable text) {

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(
                "Select Acc_id,Acc_name,debit,credit from ACCOUNT WHERE  Acc_name LIKE '%" + text + "%'",
                null);
        return cursor;

    }
    public void update_acc_credit(int acc_id, int amount) {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.execSQL("update  ACCOUNT  SET credit=credit+"+amount+" where Acc_id="+acc_id+"");
    }
    public void update_acc_debit(int acc_id, int amount) {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.execSQL("update  ACCOUNT  SET debit=debit+"+amount+" where Acc_id="+acc_id+"");
    }

    public Cursor search_sale(String str) {

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(" select sale_id ,inv_date,INVOICE.inv_id,total_amount FROM INVOICE ,SALE_INVOICE\n" +
                "    WHERE INVOICE.inv_id=SALE_INVOICE.inv_id AND SALE_INVOICE.sale_id LIKE '%"+str+"%'" ,null);
        return cursor;
    }
    public Cursor search_pay(String str) {

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select pay_id,pay_date,account_name,amount from PAYMENT where pay_date  like '%"+str+"%'" ,null);
        return cursor;
    }
    public void updateproduct(int id, int qty) {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.execSQL("update  PRODUCT  SET units=units-"+qty+" where P_id="+id+"");

    }

    public Cursor get_for_invoice_main() {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(
                "Select inv_id,inv_date,total_product,total_amount from INVOICE ",
                null);
        return cursor;
    }

    public Cursor search_inv(Editable str) {

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(
                "Select inv_id,inv_date,total_product,total_amount from INVOICE  where inv_date like '%"+str+"%'",
                null);
        return cursor;

    }


    public Cursor get_total_use() {


        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(
                "Select * from User ",
                null);
        return cursor;

    }
}
