package com.rajendra.onlineproductsapp;

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

public class MainActivity extends AppCompatActivity {

    ProductCategoryAdapter productCategoryAdapter;
    RecyclerView productCatRecycler, prodItemRecycler;
    ProductAdapter productAdapter;

    @Override

    //The recycler controllers
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(new ProductCategory(1, "Trending"));
        productCategoryList.add(new ProductCategory(2, "Most Popular"));
        productCategoryList.add(new ProductCategory(3, "All Products"));
        productCategoryList.add(new ProductCategory(4, "Limited Editions"));
        productCategoryList.add(new ProductCategory(5, "Fitness"));
        productCategoryList.add(new ProductCategory(6, "Smart Watches"));
        productCategoryList.add(new ProductCategory(7, "Smart Phones"));

        setProductRecycler(productCategoryList);
// each recycler
        List<Products> productsList = new ArrayList<>();
        productsList.add(new Products(1, "Japanese LMTD EDITION Smart Watch", "35 GB", "$ 267.00", R.drawable.prod2));
        productsList.add(new Products(2, "New Special Edition Watch", "40 GB", "$ 250.00", R.drawable.prod1));
        productsList.add(new Products(3, "Fitness Watch", "8 GB", "$ 170.00", R.drawable.prod3));
        productsList.add(new Products(2, "New Special Edition Watch", "40 GB", "$ 250.00", R.drawable.prod1));
        productsList.add(new Products(3, "Japanese LMTD EDITION Smart Watch", "35 GB", "$ 267.00", R.drawable.prod2));
        productsList.add(new Products(2, "Fitness Watch", "8 GB", "$ 170.00", R.drawable.prod3));

        setProdItemRecycler(productsList);
    }

    private void setProductRecycler(List<ProductCategory> productCategoryList) {

        productCatRecycler = findViewById(R.id.cat_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        productCatRecycler.setLayoutManager(layoutManager);
        productCategoryAdapter = new ProductCategoryAdapter(this, productCategoryList);
        productCatRecycler.setAdapter(productCategoryAdapter);
    }

    private void setProdItemRecycler(List<Products> productsList) {

        prodItemRecycler = findViewById(R.id.product_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        prodItemRecycler.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(this, productsList);
        prodItemRecycler.setAdapter(productAdapter);
    }

    //Button to redirect user to pages with button declaration
}