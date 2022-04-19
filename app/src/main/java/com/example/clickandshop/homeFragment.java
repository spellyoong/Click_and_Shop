package com.example.clickandshop;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homeFragment extends Fragment implements RecyclerViewInterface{

    // Initiate variables
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private Toast prototypeToast;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public homeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static homeFragment newInstance(String param1, String param2) {
        homeFragment fragment = new homeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        // Category page navigation
        View cat1Container = v.findViewById(R.id.cat1Container);
        View cat2Container = v.findViewById(R.id.cat2Container);
        View cat3Container = v.findViewById(R.id.cat3Container);
        View cat4Container = v.findViewById(R.id.cat4Container);
        View cat5Container = v.findViewById(R.id.cat5Container);
        View cat6Container = v.findViewById(R.id.cat6Container);
        View cat7Container = v.findViewById(R.id.cat7Container);
        View cat8Container = v.findViewById(R.id.cat8Container);
        View cat9Container = v.findViewById(R.id.cat9Container);
        View cat10Container = v.findViewById(R.id.cat10Container);

        cat1Container.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoriesActivity.class).putExtra("categoryOption", 0);
                startActivity(intent);
            }
        });

        cat2Container.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoriesActivity.class).putExtra("categoryOption", 1);
                startActivity(intent);
            }
        });

        cat3Container.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoriesActivity.class).putExtra("categoryOption", 2);
                startActivity(intent);
            }
        });

        cat4Container.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoriesActivity.class).putExtra("categoryOption", 3);
                startActivity(intent);
            }
        });

        cat5Container.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoriesActivity.class).putExtra("categoryOption", 4);
                startActivity(intent);
            }
        });

        cat6Container.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoriesActivity.class).putExtra("categoryOption", 5);
                startActivity(intent);
            }
        });

        cat7Container.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoriesActivity.class).putExtra("categoryOption", 6);
                startActivity(intent);
            }
        });

        cat8Container.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoriesActivity.class).putExtra("categoryOption", 7);
                startActivity(intent);
            }
        });

        cat9Container.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoriesActivity.class).putExtra("categoryOption", 8);
                startActivity(intent);
            }
        });

        cat10Container.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoriesActivity.class).putExtra("categoryOption", 9);
                startActivity(intent);
            }
        });

        // Search
        EditText searchEditText = v.findViewById(R.id.searchEditText);

        searchEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    displayPrototypeMessage();
                    searchEditText.setText("");
                    return true;
                }
                return false;
            }
        });

        // Cart page navigation
        ImageView cartIcon = v.findViewById(R.id.cartIcon);

        cartIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CartActivity.class);
                startActivity(intent);
            }
        });

        // Chat page navigation
        ImageView chatIcon = v.findViewById(R.id.chatIcon);

        chatIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChatActivity1.class);
                startActivity(intent);
            }
        });

        // Retrieve products list from Products.java
        List<Products> topProdList = new ArrayList<Products>();
        topProdList = new Products().getProdList();

        // Sort top products list by soldQty in descending order
        Collections.sort(topProdList, new Comparator<Products>() {
            @Override
            public int compare(Products products, Products t1) {
                if (products.getSoldQty() > t1.getSoldQty()){
                    return -1;
                }
                else if (products.getSoldQty() < t1.getSoldQty()){
                    return 1;
                }
                else {
                    return 0;
                }
            }
        });

        recyclerView = v.findViewById(R.id.recycler_view);

        // RecyclerView layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getActivity(), LinearLayoutManager.HORIZONTAL, false
        );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // RecyclerView Adapter
        recyclerAdapter = new RecyclerAdapter(getActivity(), topProdList, this);
        recyclerView.setAdapter(recyclerAdapter);

        // Retrieve products list from Products.java
        List<Products> list = new ArrayList<Products>();
        list = new Products().getProdList();

        // Get list length
        int listSize = list.size();

        // Initiate productPhoto
        int[] productPhoto = new int[listSize];
        for (int i=0; i<productPhoto.length;i++){
            productPhoto[i] = list.get(i).productPhoto;
        }

        // Initiate productName
        String[] productName = new String[listSize];
        for (int i=0; i<productName.length;i++){
            productName[i] = list.get(i).productName;
        }

        // Initiate productPrice
        double[] productPrice = new double[listSize];
        for (int i=0; i<productPrice.length;i++){
            productPrice[i] = list.get(i).productPrice;
        }

        // Initiate soldQty
        double[] soldQty = new double[listSize];
        for (int i=0; i<soldQty.length;i++){
            soldQty[i] = list.get(i).soldQty;
        }

        ExpandableHeightGridView gridView = (ExpandableHeightGridView) v.findViewById(R.id.gridView);

        // GridView Adapter
        GridAdapter gridAdapter = new GridAdapter(productName, productPhoto, productPrice, soldQty, getActivity());
        gridView.setAdapter(gridAdapter);
        gridView.setExpanded(true);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(), ProductActivity.class).putExtra("position", i));
            }
        });

        return v;
    }

    // Product page navigation
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), ProductActivity.class).putExtra("position", position).putExtra("sortTopProd", true);
        startActivity(intent);
    }

    // Prototype Message
    private void displayPrototypeMessage(){
        if (prototypeToast != null)
            prototypeToast.cancel();
        prototypeToast = Toast.makeText(getActivity(), "Function not implemented in current prototype version", Toast.LENGTH_SHORT);
        prototypeToast.show();
    }
}