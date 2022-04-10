package com.example.clickandshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity implements RecyclerViewInterface, RecyclerViewInterface2{

    // Initiate variables for recycler view
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    CategoryRecyclerAdapter categoryRecyclerAdapter;
    ProductRecyclerAdapter productRecyclerAdapter;
    int categoryOption;
    int categoryPosition;
    private View backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // Initiate position of selected category (intend from category icon in homepage) - part 1
        Intent intent = getIntent();

        if (intent.getExtras() != null) {
            categoryOption = intent.getIntExtra("categoryOption", 0);
        }

        // Initiate RecyclerView variables (Categories Name)
        recyclerView = findViewById(R.id.recycler_view);
        String[] categoryName = {"Hot Deals", "Essential", "Sport", "Beauty", "Gaming", "Electronics", "Fashion", "Toys, Kids & Baby", "Home & Living", "Health"};

        // RecyclerView layout (Categories Name)
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                CategoriesActivity.this,LinearLayoutManager.HORIZONTAL, false
        );

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // RecyclerView Adapter (Categories Name)
        categoryRecyclerAdapter = new CategoryRecyclerAdapter(CategoriesActivity.this, categoryName, this);
        recyclerView.setAdapter(categoryRecyclerAdapter);

        // Initiate position of selected category (intend from category icon in homepage) - part 2
        recyclerView.scrollToPosition(categoryOption);
        recyclerView.postDelayed(new Runnable(){
            @Override
            public void run(){
                recyclerView.findViewHolderForAdapterPosition(categoryOption).itemView.performClick();
            }

        }, 1);

        // ======================================
        // Initiate RecyclerView variables (Categories Products)
        recyclerView2 = findViewById(R.id.recycler_view_2);

        // Retrieve products list from Categories - Hot Deals
        List<Products> list = new ArrayList<Products>();
        list = new Products().getCategoryHotList();

        // RecyclerView layout (Categories Products)
        GridLayoutManager layoutManager2 = new GridLayoutManager(CategoriesActivity.this, 2);

        recyclerView2.setNestedScrollingEnabled(false);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());

        // RecyclerView Adapter (Categories Products)
        productRecyclerAdapter = new ProductRecyclerAdapter(CategoriesActivity.this, list, this);
        recyclerView2.setAdapter(productRecyclerAdapter);

        // Back key navigation
        backArrow = findViewById(R.id.backArrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onItemCategoryClick(int position) {

        // Retrieve products list from selected categories (determined by position)
        categoryPosition = position;
        List<Products> catProdList = new ArrayList<Products>();

        if (position == 0) {
            catProdList = new Products().getCategoryHotList();
        }
        else if (position == 1) {
            catProdList = new Products().getCategoryEssentialList();
        }
        else if (position == 2) {
            catProdList = new Products().getCategorySportList();
        }
        else if (position == 3) {
            catProdList = new Products().getCategoryBeautyList();
        }
        else if (position == 4) {
            catProdList = new Products().getCategoryGamingList();
        }
        else if (position == 5) {
            catProdList = new Products().getCategoryElectronicsList();
        }
        else if (position == 6) {
            catProdList = new Products().getCategoryFashionList();
        }
        else if (position == 7) {
            catProdList = new Products().getCategoryToyList();
        }
        else if (position == 8) {
            catProdList = new Products().getCategoryHomeList();
        }
        else if (position == 9) {
            catProdList = new Products().getCategoryHealthList();
        }

        productRecyclerAdapter = new ProductRecyclerAdapter(CategoriesActivity.this, catProdList, this);
        recyclerView2.setAdapter(productRecyclerAdapter);

    }

    public void onItemClick(int position) {
        Intent intent = new Intent(CategoriesActivity.this, ProductActivity.class).putExtra("position", position).putExtra("categoryPosition", categoryPosition);
        startActivity(intent);
    }
}