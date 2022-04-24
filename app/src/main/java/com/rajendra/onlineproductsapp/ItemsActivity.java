package com.rajendra.onlineproductsapp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class ItemsActivity extends AppCompatActivity {

    EditText type_input, specifier_input, range_input;
    Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemsdata);

        type_input = findViewById(R.id.type_input);
        specifier_input = findViewById(R.id.specifier_input);
        range_input = findViewById(R.id.range_input);
        btnInsert = findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper myDB = new DBHelper(ItemsActivity.this);
                myDB.addItems(type_input.getText().toString().trim(),
                        specifier_input.getText().toString().trim(),
                        Integer.valueOf(range_input.getText().toString().trim()));
            }
        });
    }
}