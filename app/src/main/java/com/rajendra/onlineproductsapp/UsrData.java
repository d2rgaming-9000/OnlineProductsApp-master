package com.rajendra.onlineproductsapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rajendra.onlineproductsapp.adapter.ProductAdapter;
import com.rajendra.onlineproductsapp.adapter.ProductCategoryAdapter;
import com.rajendra.onlineproductsapp.model.ProductCategory;
import com.rajendra.onlineproductsapp.model.Products;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;



public class UsrData extends AppCompatActivity {

    EditText usrname, email, pass;
    Button update, delete, view, insert;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);

        usrname = findViewById(R.id.Usrname);
        email = findViewById(R.id.Email);

        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usrnameTXT = usrname.getText().toString();
                String emailTXT = email.getText().toString();
                String passTXT = pass.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(usrnameTXT, emailTXT, passTXT);
                if (checkinsertdata == true) {
                    Toast.makeText(UsrData.this, "Account Registered", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(UsrData.this, "Entry Not Registered", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usrnameTXT = usrname.getText().toString();
                String emailTXT = email.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(usrnameTXT, emailTXT);
                if (checkupdatedata == true) {
                    Toast.makeText(UsrData.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UsrData.this, "Entry Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public  void onClick(View view){
                String usrnameTXT = usrname.getText().toString();
                Boolean deleteuserdata = DB.deleteuserdata(usrnameTXT);
                if(deleteuserdata == true)
                {Toast.makeText(UsrData.this, "Entry Deleted", Toast.LENGTH_SHORT).show();}
                else{Toast.makeText(UsrData.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();}
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0) {
                    Toast.makeText (UsrData.this, "No Entry", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :" +res.getString(0)+"d");
                    buffer.append("Email :" +res.getString(1)+"/dd");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(UsrData.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });

    }
}