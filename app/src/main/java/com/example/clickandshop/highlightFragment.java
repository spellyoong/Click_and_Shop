package com.example.clickandshop;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link highlightFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class highlightFragment extends Fragment {

    // Initiate variables
    private Toast prototypeToast;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public highlightFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment highlightFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static highlightFragment newInstance(String param1, String param2) {
        highlightFragment fragment = new highlightFragment();
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
        View v = inflater.inflate(R.layout.fragment_highlight, container, false);

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

        // Seller page navigation
        TextView viewShop1Txt = v.findViewById(R.id.viewShop1Txt);
        TextView viewShop2Txt = v.findViewById(R.id.viewShop2Txt);
        TextView viewShop3Txt = v.findViewById(R.id.viewShop3Txt);

        viewShop1Txt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SellerActivity.class);
                startActivity(intent);
            }
        });

        viewShop2Txt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SellerActivity.class);
                startActivity(intent);
            }
        });

        viewShop3Txt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SellerActivity.class);
                startActivity(intent);
            }
        });

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

    // Prototype Message
    private void displayPrototypeMessage(){
        if (prototypeToast != null)
            prototypeToast.cancel();
        prototypeToast = Toast.makeText(getActivity(), "Function not implemented in current prototype version", Toast.LENGTH_SHORT);
        prototypeToast.show();
    }
}